package com.lopez.mytasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InfoProcessDto {
    private String answer;
    private Long id;
    private String title;
    private String content;
}
