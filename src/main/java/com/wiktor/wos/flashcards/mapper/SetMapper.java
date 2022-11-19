package com.wiktor.wos.flashcards.mapper;

import com.wiktor.wos.flashcards.dto.SetDto;
import com.wiktor.wos.flashcards.entity.Set;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SetMapper extends GenericMapper<Set, SetDto>{
}
