package com.taewoo.crudapplication.dto;

import com.taewoo.crudapplication.entity.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardResponseDto {
    private Long id;
    private String title;
    private String content;
    private Long userId;
    private String username;

    public BoardResponseDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.userId = board.getUserId();
        if (this.userId == 0) {
            this.username = "unknown";
        } else {
            try {
                this.username = board.getUser() != null ? board.getUser().getUsername() : "Unknown";
            } catch (Exception e) {
                this.username = "Unknown";
            }
        }
    }
}
