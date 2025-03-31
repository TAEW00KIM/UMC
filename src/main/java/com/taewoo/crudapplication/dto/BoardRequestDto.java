package com.taewoo.crudapplication.dto;

import com.taewoo.crudapplication.entity.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BoardRequestDto {
    private String title;
    private String content;
}
