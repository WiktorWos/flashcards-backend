package com.wiktor.wos.flashcards.service;

import com.wiktor.wos.flashcards.dto.SetDto;
import com.wiktor.wos.flashcards.entity.Set;
import com.wiktor.wos.flashcards.entity.UserSet;
import com.wiktor.wos.flashcards.exception.BadRequestException;
import com.wiktor.wos.flashcards.exception.EntityNotFoundException;
import com.wiktor.wos.flashcards.exception.SetIsNotPublicException;
import com.wiktor.wos.flashcards.mapper.SetMapper;
import com.wiktor.wos.flashcards.repository.SetRepository;
import com.wiktor.wos.flashcards.repository.UserRepository;
import com.wiktor.wos.flashcards.repository.UserSetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SetServiceImpl implements SetService {
    private final SetRepository setRepository;
    private final SetMapper setMapper;
    private final UserRepository userRepository;

    public SetServiceImpl(SetRepository setRepository, SetMapper setMapper, UserRepository userRepository) {
        this.setRepository = setRepository;
        this.setMapper = setMapper;
        this.userRepository = userRepository;
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

    @Override
    public SetDto addSharedSet(Long id, String email) {
        var set = setRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Set not found"));
        if(!set.isPublic()) throw new SetIsNotPublicException();
        var user = userRepository.findByEmail(email).get(0);
        set.getUsers().add(user);
        setRepository.save(set);
        return setMapper.mapToDto(set);
    }

    @Override
    public Long deleteSharedSet(Long id, String email) {
        var set = setRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Set not found"));
        var user = userRepository.findByEmail(email).get(0);
        set.getUsers().remove(user);
        setRepository.save(set);
        return id;
    }
}
