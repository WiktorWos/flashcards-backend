package com.wiktor.wos.flashcards.repository;

import com.wiktor.wos.flashcards.entity.Set;
import com.wiktor.wos.flashcards.entity.generic.GenericEntity;

import java.util.List;

public interface GenericBrowserRepository <E extends GenericEntity, R> {
    List<E> browse(R browserRequest);
}
