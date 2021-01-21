package com.pilot.boot.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * json format
 * @author ezuy
 * @date 20/12/22 17:46
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResult<T> {

    /**
     * 约定200为成功,100为失败
     */
    private Integer code;
    private String message;
    private T data;

    public CommonResult(Integer code, String message) {
        this(code, message, null);
    }


}