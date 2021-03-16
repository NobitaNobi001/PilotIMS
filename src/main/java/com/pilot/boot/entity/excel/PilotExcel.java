package com.pilot.boot.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ezuy
 * @date 21/1/30 0:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PilotExcel implements Serializable {

    @ExcelProperty("姓名")
    private String pilotName;

    @ExcelProperty("性别")
    private String sex;

    @ExcelProperty("身份证号")
    private String card;

    @ExcelProperty("所属部门")
    private String deptName;

    @ExcelProperty("职务")
    private String position;

    @ExcelProperty("职称")
    private String jobTitle;

    @ExcelProperty("联系电话")
    private String phone;

    @ExcelProperty("邮箱")
    private String email;

    @ExcelProperty("备注")
    private String remark;
}
