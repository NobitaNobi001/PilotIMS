package com.pilot.boot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * @author ezuy
 * @date 20/12/22 15:43
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("dept")
public class Dept implements Serializable {

    @TableId(value = "dept_id", type = IdType.AUTO)
    @Min(value = 0, message = "部门id数值不合法")
    @Max(value = Long.MAX_VALUE, message = "部门id数值不合法")
    private Long deptId;

    @Length(max = 20, message = "部门名称过长")
    private String deptName;
}
