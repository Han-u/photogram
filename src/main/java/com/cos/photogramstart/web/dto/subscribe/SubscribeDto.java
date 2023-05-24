package com.cos.photogramstart.web.dto.subscribe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubscribeDto {
    private int id;
    private String username;
    private String profileImageUrl;
    private Integer subscribeState; // DB에서 오는 Int값
    private Integer equalUserState;
}
