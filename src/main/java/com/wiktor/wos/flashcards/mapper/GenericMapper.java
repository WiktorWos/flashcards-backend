package com.wiktor.wos.flashcards.mapper;

import java.util.List;

public interface GenericMapper<E, D> {
    D mapToDto(E entity);
    E mapToEntity(D dto);
    List<D> mapToDtoList(List<E> entities);
    List<E> mapToEntityList(List<D> dtos);
}
