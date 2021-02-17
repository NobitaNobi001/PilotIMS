package com.pilot.boot.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * json format
 *
 * @author ezuy
 * @date 20/12/22 17:46
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResult<T> {

    /**
     * 约定100为失败,200为成功,300为没有权限
     */
    private Integer code;
    private String message;
    private T data;

    public CommonResult(Integer code, String message) {
        this(code, message, null);
    }

    /**
     * success response
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<>(200, "", data);
    }

    /**
     * fail response
     *
     * @param code
     * @param message
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> fail(Integer code, String message) {
        return new CommonResult<>(code, message);
    }

}
