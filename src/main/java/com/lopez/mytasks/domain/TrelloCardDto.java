package com.lopez.mytasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TrelloCardDto {
    private String name;
    private String desc;
    private String pos;
    private String idList;
}
