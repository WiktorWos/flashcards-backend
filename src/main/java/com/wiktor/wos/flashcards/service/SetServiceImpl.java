package com.wiktor.wos.flashcards.service;

import com.wiktor.wos.flashcards.dto.SetDto;
import com.wiktor.wos.flashcards.entity.Set;
import com.wiktor.wos.flashcards.exception.BadRequestException;
import com.wiktor.wos.flashcards.exception.EntityNotFoundException;
import com.wiktor.wos.flashcards.mapper.SetMapper;
import com.wiktor.wos.flashcards.repository.SetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SetServiceImpl implements SetService {
    private final SetRepository setRepository;
    private final SetMapper setMapper;

    public SetServiceImpl(SetRepository setRepository, SetMapper setMapper) {
        this.setRepository = setRepository;
        this.setMapper = setMapper;
    }

    @Override
    public SetDto createSet(SetDto setDto) {
        if(setDto.getId() != null) throw new BadRequestException("Id to create must be null");
        var savedEntity = setRepository.save(setMapper.mapToEntity(setDto));
        return setMapper.mapToDto(savedEntity);
    }

    @Override
    public SetDto updateSet(SetDto setDto, Long id) {
        if(id == null) throw new BadRequestException("Id to create must not be null");

        var entityById = setRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Set not found"));
        setDto.setId(id);
        setDto.setCreateDate(entityById.getCreateDate());
        var savedEntity = setRepository.save(setMapper.mapToEntity(setDto));
        return setMapper.mapToDto(savedEntity);
    }

    @Override
    public Long deleteSet(Long id) {
        var entityById = setRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Set not found"));
        setRepository.delete(entityById);
        return id;
    }

    @Override
    public List<SetDto> getAllSets() {
        return setMapper.mapToDtoList(setRepository.findAll());
    }

    @Override
    public SetDto getSet(Long id) {
        return setMapper.mapToDto(setRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Set not found")));
    }
}
