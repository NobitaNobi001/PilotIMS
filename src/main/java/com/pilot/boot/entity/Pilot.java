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
 * @date 20/12/22 15:43
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("pilot")
public class Pilot implements Serializable {

    @TableId(value = "pilot_id", type = IdType.AUTO)
    @Min(value = 0, message = "飞行员编号数值不合法")
    @Max(value = Long.MAX_VALUE, message = "飞行员编号数值不合法")
    private Long pilotId;

    @NotEmpty
    @NotBlank
    @Length(max = 10, message = "用户姓名格式信息不正确")
    private String pilotName;

    @NotNull
    private Integer sex;

    @NotBlank
    @Length(min = 18, max = 18, message = "身份证格式不合法")
    private String card;

    @NotNull
    @Min(value = 0, message = "部门id数值不合法")
    @Max(value = Long.MAX_VALUE, message = "部门id数值不合法")
    private Long deptId;

    @Length(max = 20, message = "职位名称过长")
    private String position;

    @Length(max = 20, message = "职位名称过长")
    private String jobTitle;

    @Pattern(regexp = "(^((1[0-9][0-9]))\\d{8}$)||(^$)", message = "手机号码格式不正确")
    private String phone;

    @Pattern(regexp = "(^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$)|(^\\s*$)||(^$)",
            message = "邮箱格式不正确")
    private String email;

    private String remark;

    private Dept dept;
}

