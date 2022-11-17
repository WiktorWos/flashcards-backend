package com.wiktor.wos.flashcards.dto.browser;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SharedSetsBrowserRequest {
    GenericBrowserField<String> language;
    GenericBrowserField<String> category;
    DateBrowserField createDate;
    int limit;
    int page;
}
