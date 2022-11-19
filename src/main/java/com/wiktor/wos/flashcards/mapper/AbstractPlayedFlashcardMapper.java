package com.wiktor.wos.flashcards.mapper;

import com.wiktor.wos.flashcards.dto.PlayedFlashcardDto;
import com.wiktor.wos.flashcards.entity.PlayedFlashcard;
import com.wiktor.wos.flashcards.entity.UserSetsId;
import com.wiktor.wos.flashcards.exception.EntityNotFoundException;
import com.wiktor.wos.flashcards.repository.FlashcardDefinitionRepository;
import com.wiktor.wos.flashcards.repository.FlashcardRepository;
import com.wiktor.wos.flashcards.repository.UserRepository;
import com.wiktor.wos.flashcards.repository.UserSetRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

@Mapper(componentModel = "spring")
public abstract class AbstractPlayedFlashcardMapper implements GenericMapper<PlayedFlashcard, PlayedFlashcardDto> {
    @Autowired
    private UserSetRepository setRepository;
    @Autowired
    private FlashcardRepository flashcardRepository;
    @Autowired
    private FlashcardDefinitionRepository flashcardDefinitionRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    @Mapping(target="flashcardDefinition.frontDefinition", source="frontDefinition")
    @Mapping(target="flashcardDefinition.backDefinition", source="backDefinition")
    @Mapping(target="flashcardDefinition.flashcard.front", source="front")
    @Mapping(target="flashcardDefinition.flashcard.back", source="back")
    public abstract PlayedFlashcard mapToEntity(PlayedFlashcardDto dto);

    @AfterMapping
    protected void setUserSet(PlayedFlashcardDto dto, @MappingTarget PlayedFlashcard entity) {
//        var set = setRepository.findById(dto.getSetId()).orElseThrow(()-> new EntityNotFoundException("Set not found"));
        var set = setRepository.findById(new UserSetsId(dto.getUserId(), dto.getSetId())).orElseThrow(() -> new EntityNotFoundException("Set not found"));
        entity.setSet(set);

        flashcardRepository.findByBackAndFront(dto.getBack(), dto.getFront())
                .ifPresent(flashcard -> {
                    flashcardDefinitionRepository.findByFrontDefinitionAndBackDefinitionAndFlashcard(dto.getFrontDefinition(), dto.getBackDefinition(), flashcard)
                            .ifPresentOrElse(entity::setFlashcardDefinition, () -> entity.getFlashcardDefinition().setFlashcard(flashcard));
                });

    }

    @Override
    @Mapping(target="setId", source="entity.set.setId")
    @Mapping(target="frontDefinition", source="entity.flashcardDefinition.frontDefinition")
    @Mapping(target="backDefinition", source="entity.flashcardDefinition.backDefinition")
    @Mapping(target="front", source="entity.flashcardDefinition.flashcard.front")
    @Mapping(target="back", source="entity.flashcardDefinition.flashcard.back")
    public abstract PlayedFlashcardDto mapToDto(PlayedFlashcard entity);
}
