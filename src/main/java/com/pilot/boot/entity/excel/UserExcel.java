package com.pilot.boot.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @author ezuy
 * @date 21/1/30 20:48
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserExcel implements Serializable {

    @NotEmpty
    @NotBlank
    @Length(max = 10, message = "用户姓名过长")
    @ExcelProperty("姓名")
    private String userName;

    @NotNull
    @ExcelProperty("性别")
    private String sex;

    @ExcelProperty("身份类型")
    private String type;

    @NotBlank
    @ExcelProperty("身份证号")
    @Length(min = 18, max = 18, message = "身份证格式不合法")
    private String card;

    @ExcelProperty("所属部门")
    @Length(max = 20, message = "部门名称过长")
    private String deptName;

    @ExcelProperty("职务")
    @Length(max = 20, message = "职务名称过长")
    private String position;

    @ExcelProperty("职称")
    @Length(max = 20, message = "职称名称过长")
    private String jobTitle;

    @ExcelProperty("联系电话")
    @Pattern(regexp = "(^((1[0-9][0-9]))\\d{8}$)||(^$)", message = "手机号码格式不正确")
    private String phone;

    @ExcelProperty("邮箱")
    @Pattern(regexp = "(^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$)|(^\\s*$)||(^$)",
            message = "邮箱格式不正确")
    private String email;

    @ExcelProperty("备注")
    private String remark;
}
