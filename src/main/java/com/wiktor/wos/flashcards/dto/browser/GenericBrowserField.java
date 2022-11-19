package com.wiktor.wos.flashcards.dto.browser;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GenericBrowserField<T> {
    private T search;
    private boolean sort;
    private boolean asc;
}
