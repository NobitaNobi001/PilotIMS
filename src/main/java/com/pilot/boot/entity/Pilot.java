package com.pilot.boot.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
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
 * pilot
 *
 * @author ezuy
 * @date 20/12/22 15:43
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("pilot")
public class Pilot implements Serializable {

    @TableId(value = "pilot_id", type = IdType.AUTO)
    private Long pilotId;

    @NotNull
    @NotEmpty
    @NotBlank
    @Length(max = 10, message = "用户姓名格式信息不正确")
    private String pilotName;

    @ExcelIgnore
    private Integer sex;

    @NotNull
    @NotEmpty
    @NotBlank
    @Length(min = 18, max = 18)
    private String card;

    private Long deptId;

    private String position;

    private String jobTitle;

//    @Digits(integer = 11, fraction = 0, message = "电话号码格式不合法")
    private String phone;

//    @Email(message = "邮箱格式不正确")
    private String email;

    private String remark;

    private Dept dept;
}

