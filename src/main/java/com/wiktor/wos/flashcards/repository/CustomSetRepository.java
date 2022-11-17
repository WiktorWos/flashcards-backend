package com.wiktor.wos.flashcards.repository;

import com.wiktor.wos.flashcards.dto.browser.SharedSetsBrowserRequest;
import com.wiktor.wos.flashcards.entity.Set;

public interface CustomSetRepository extends GenericBrowserRepository<Set, SharedSetsBrowserRequest> {
}
