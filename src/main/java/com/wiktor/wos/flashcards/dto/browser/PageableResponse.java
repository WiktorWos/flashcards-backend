package com.wiktor.wos.flashcards.dto.browser;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PageableResponse<T> {
    private int total;
    private int page;
    private List<T> items;
}
