package com.wiktor.wos.flashcards.dto.browser;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class DateBrowserField extends GenericBrowserField<LocalDateTime> {
    private LocalDateTime searchTo;
}
