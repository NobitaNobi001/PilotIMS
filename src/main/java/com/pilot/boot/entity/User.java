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
 * @author ezuy
 * @date 20/12/22 15:44
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("user")
public class User implements Serializable {

    @TableId(value = "user_id", type = IdType.AUTO)
    @Min(value = 0, message = "用户id数值不合法")
    @Max(value = Long.MAX_VALUE, message = "用户id数值不合法")
    private Long userId;

    @NotBlank
    @Length(min = 1, max = 1, message = "用户类型格式不正确")
    private String type;

    @NotEmpty
    @NotBlank
    @Length(max = 10, message = "用户姓名过长")
    private String userName;

    @Pattern(regexp = "(^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$)||(^$)", message = "密码应为8~16个字母和数字组合")
    private String password;

    @NotNull
    private Integer sex;

    @NotBlank
    @Length(min = 18, max = 18, message = "身份证格式不合法")
    private String card;

    @NotNull
    @Min(value = 0, message = "部门id数值不合法")
    @Max(value = Long.MAX_VALUE, message = "部门id数值不合法")
    private Long deptId;

    @Length(max = 20, message = "职务名称过长")
    private String position;

    @Length(max = 20, message = "职称名称过长")
    private String jobTitle;

    @Pattern(regexp = "(^((1[0-9][0-9]))\\d{8}$)||(^$)", message = "手机号码格式不正确")
    private String phone;

    @Pattern(regexp = "(^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$)|(^\\s*$)||(^$)",
            message = "邮箱格式不正确")
    private String email;

    private String remark;

    private Dept dept;

}
