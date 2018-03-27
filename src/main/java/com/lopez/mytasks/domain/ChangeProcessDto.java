package com.lopez.mytasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ChangeProcessDto {
    private Long id;
    private String answerFrom;
    private String titleBefore;
    private String contentBefore;
    private String answerTo;
    private String titleAfter;
    private String contentAfter;
}
