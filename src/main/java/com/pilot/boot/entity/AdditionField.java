package com.pilot.boot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * @author ezuy
 * @date 21/3/2 14:48
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("addition_filed")
public class AdditionField {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @NotBlank
    @NotEmpty
    @Length(max = 20, message = "名称过长")
    private String name;
}
