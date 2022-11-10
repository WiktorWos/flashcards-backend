package com.wiktor.wos.flashcards.service;

import com.wiktor.wos.flashcards.dto.UserStatsDTO;
import com.wiktor.wos.flashcards.dto.UserStatsResponseDTO;
import com.wiktor.wos.flashcards.entity.TimeLogs;
import com.wiktor.wos.flashcards.entity.UserStats;
import com.wiktor.wos.flashcards.exception.EntityNotFoundException;
import com.wiktor.wos.flashcards.repository.UserStatsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

@Service
public class UserStatsService {
    private UserStatsRepo userStatsRepo;

    @Autowired
    public UserStatsService(UserStatsRepo userStatsRepo) {
        this.userStatsRepo = userStatsRepo;
    }

    public UserStatsResponseDTO getStatsByUserID(Long userID) {
        UserStats userStats = userStatsRepo.findByUserID(userID);
        if(userStats == null) throw new EntityNotFoundException("There is no data with that id");

        UserStatsResponseDTO dto = new UserStatsResponseDTO();

        List<TimeLogs> timeLogs = userStats.getTimeLogs();
        List<TimeLogs> lastWeekLogs = getTimeLogsFromLastWeek(timeLogs);
        OptionalDouble average = calculateAverage(lastWeekLogs);
        TimeLogs maxTimeLog = getMaxTimeLog(lastWeekLogs);

        dto.setAccuracy((userStats.getCorrectFlashcards() * 100) / userStats.getDoneFlashcards());
        dto.setLearned(userStats.getLearnedNum());
        dto.setAverageTimeFromWeek(average.isPresent() ? average.getAsDouble() : 0);
        dto.setBestDayFromWeek(maxTimeLog.getDate());
        dto.setBestTimeFromWeek(maxTimeLog.getMinutes());

        return dto;
    }

    private List<TimeLogs> getTimeLogsFromLastWeek(List<TimeLogs> timeLogs) {
        return timeLogs
                .stream()
                .filter(timeLog -> timeLog.getDate().isAfter(LocalDate.now().minusDays(7)))
                .collect(Collectors.toList());
    }

    private OptionalDouble calculateAverage(List<TimeLogs> timeLogs) {
        return timeLogs
                .stream()
                .mapToInt(TimeLogs::getMinutes)
                .average();
    }

    private TimeLogs getMaxTimeLog(List<TimeLogs> timeLogs) {
        return timeLogs
                .stream()
                .max(Comparator.comparing(TimeLogs::getMinutes))
                .orElseThrow(NoSuchElementException::new);
    }

    public void setUserStats(UserStatsDTO dto) {
        UserStats stats = userStatsRepo.findByUserID(dto.getUserID());
        if(stats == null) {
            stats = new UserStats();
            stats = stats.convertFromDTO(dto);
        } else {
            stats = updateStats(dto, stats);
        }
        userStatsRepo.save(stats);
    }

    private UserStats updateStats(UserStatsDTO dto, UserStats stats) {
        stats.setCorrectFlashcards(stats.getCorrectFlashcards() + dto.getCorrectFlashcards());
        stats.setDoneFlashcards(stats.getDoneFlashcards() + dto.getDoneFlashcards());
        stats.setLearnedNum(stats.getLearnedNum() + dto.getLearned());
        TimeLogs timeLog = new TimeLogs();
        timeLog.setMinutes(dto.getRecordedTime());
        timeLog.setDate(LocalDate.now());
        stats.addTimeLog(timeLog);
        return stats;
    }
}
