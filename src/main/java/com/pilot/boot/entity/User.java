package com.pilot.boot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * user
 *
 * @author ezuy
 * @date 20/12/22 15:44
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("user")
public class User implements Serializable {

    @TableId(value="user_id",type = IdType.AUTO)
    @Min(value = 0, message = "用户id数值不合法")
    @Max(value = Long.MAX_VALUE, message = "用户id数值不合法")
    @NotEmpty
    private Long userId;

    @Size(max = 1, message = "用户类型格式不正确")
    @NotEmpty
    private String type;

    @NotEmpty
    @Length(max = 10, message = "用户格式信息不正确")
    private String name;

    @Pattern(regexp = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$", message = "密码必须为8~16个字母和数字组合")
    private String password;

    private Integer sex;

    @Length(min = 18, max = 18)
    private String card;

    private Long deptId;

    private String position;

    private String jobTitle;

    @Digits(integer = 11, fraction = 0, message = "电话号码格式不正确")
    private String phone;

    @Email(message = "邮箱格式不正确")
    private String email;

    private String remark;
}
