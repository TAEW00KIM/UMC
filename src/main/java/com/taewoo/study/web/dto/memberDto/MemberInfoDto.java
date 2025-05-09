package com.taewoo.study.web.dto.memberDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberInfoDto {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private Integer point;
}
