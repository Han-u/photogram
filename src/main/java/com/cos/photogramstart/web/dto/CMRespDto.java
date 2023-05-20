package com.cos.photogramstart.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NotBlank
@Data
public class CMRespDto<T> {
    private int code; // 1(성공), -1(실패)
    private String message;
    private T data;
}
