package com.pilot.boot.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Digits;
import java.time.LocalDateTime;

/**
 * @author ezuy
 * @date 20/12/22 15:43
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("pilot_body")
public class PilotBody {

    @TableId("pilot_id")
    @ExcelProperty("编号")
    private Long pilotId;

    /**
     * 0表示逻辑删除 1表示物理删除
     */
    @ExcelIgnore
    private Long deleted;

    @ExcelIgnore
    private LocalDateTime createdTime;

    @ExcelProperty("身高")
    @Digits(integer = 4, fraction = 10)
    private Float height;

    @ExcelProperty("体重")
    @Digits(integer = 4, fraction = 10)
    private Float weight;

    @ExcelProperty("领围")
    @Digits(integer = 4, fraction = 10)
    private Float collarCircumference;

    @ExcelProperty("颈根围")
    @Digits(integer = 4, fraction = 10)
    private Float neckRootCircumference;

    @ExcelProperty("胸围")
    @Digits(integer = 4, fraction = 10)
    private Float bust;

    @ExcelProperty("胸位")
    @Digits(integer = 4, fraction = 10)
    private Float chestPosition;

    @ExcelProperty("下胸围")
    @Digits(integer = 4, fraction = 10)
    private Float underBust;

    @ExcelProperty("腰围")
    @Digits(integer = 4, fraction = 10)
    private Float waistCircumference;

    @ExcelProperty("腰高")
    @Digits(integer = 4, fraction = 10)
    private Float waistHeight;

    @ExcelProperty("裤腰围")
    @Digits(integer = 4, fraction = 10)
    private Float pantWaistCircumference;

    @ExcelProperty("裤腰高")
    @Digits(integer = 4, fraction = 10)
    private Float pantWaistHeight;

    @ExcelProperty("臀围")
    @Digits(integer = 4, fraction = 10)
    private Float hipCircumference;

    @ExcelProperty("臀高")
    @Digits(integer = 4, fraction = 10)
    private Float hipHeight;

    @ExcelProperty("周裆")
    @Digits(integer = 4, fraction = 10)
    private Float straightCrotch;

    @ExcelProperty("会阴高")
    @Digits(integer = 4, fraction = 10)
    private Float perinealHeight;

    @ExcelProperty("左臂长")
    @Digits(integer = 4, fraction = 10)
    private Float leftArmLength;

    @ExcelProperty("右臂长")
    @Digits(integer = 4, fraction = 10)
    private Float rightArmLength;

    @ExcelProperty("左腿长")
    @Digits(integer = 4, fraction = 10)
    private Float leftLegLength;

    @ExcelProperty("右腿长")
    @Digits(integer = 4, fraction = 10)
    private Float rightLegLength;

    @ExcelProperty("前肩宽")
    @Digits(integer = 4, fraction = 10)
    private Float frontShoulderWidth;

    @ExcelProperty("后肩宽")
    @Digits(integer = 4, fraction = 10)
    private Float backShoulderWidth;

    @ExcelProperty("左胸高")
    @Digits(integer = 4, fraction = 10)
    private Float leftChestHeight;

    @ExcelProperty("右胸高")
    @Digits(integer = 4, fraction = 10)
    private Float rightChestHeight;

    @ExcelProperty("胸宽")
    @Digits(integer = 4, fraction = 10)
    private Float chestWidth;

    @ExcelProperty("背宽")
    @Digits(integer = 4, fraction = 10)
    private Float backWidth;

    @ExcelProperty("肚围")
    @Digits(integer = 4, fraction = 10)
    private Float bellyCircumference;

    @ExcelProperty("肚围高")
    @Digits(integer = 4, fraction = 10)
    private Float bellyCircumferenceHeight;

    @ExcelProperty("前腰节")
    @Digits(integer = 4, fraction = 10)
    private Float anteriorLumbarSection;

    @ExcelProperty("前衣长")
    @Digits(integer = 4, fraction = 10)
    private Float frontLength;

    @ExcelProperty("背长")
    @Digits(integer = 4, fraction = 10)
    private Float backLength;

    @ExcelProperty("后中长(短款)")
    @Digits(integer = 4, fraction = 10)
    private Float shortBackMiddleLength;

    @ExcelProperty("后中长(长款)")
    @Digits(integer = 4, fraction = 10)
    private Float longBackMiddleLength;

    @ExcelProperty("左臂根围")
    @Digits(integer = 4, fraction = 10)
    private Float leftArmRootCircumference;

    @ExcelProperty("右臂根围")
    @Digits(integer = 4, fraction = 10)
    private Float rightArmRootCircumference;

    @ExcelProperty("左大臂围")
    @Digits(integer = 4, fraction = 10)
    private Float leftArmCircumference;

    @ExcelProperty("右大臂围")
    @Digits(integer = 4, fraction = 10)
    private Float rightArmCircumference;

    @ExcelProperty("左肘围")
    @Digits(integer = 4, fraction = 10)
    private Float leftElbow;

    @ExcelProperty("右肘围")
    @Digits(integer = 4, fraction = 10)
    private Float rightElbow;

    @ExcelProperty("左中臂围")
    @Digits(integer = 4, fraction = 10)
    private Float leftMiddleArmCircumference;

    @ExcelProperty("右中臂围")
    @Digits(integer = 4, fraction = 10)
    private Float rightMiddleArmCircumference;

    @ExcelProperty("左手腕围")
    @Digits(integer = 4, fraction = 10)
    private Float leftWristCircumference;

    @ExcelProperty("右手腕围")
    @Digits(integer = 4, fraction = 10)
    private Float rightWristCircumference;

    @ExcelProperty("左肩袖长")
    @Digits(integer = 4, fraction = 10)
    private Float leftShoulderSleeveLength;

    @ExcelProperty("右肩袖长")
    @Digits(integer = 4, fraction = 10)
    private Float rightShoulderSleeveLength;

    @ExcelProperty("左立裆")
    @Digits(integer = 4, fraction = 10)
    private Float leftRise;

    @ExcelProperty("右立裆")
    @Digits(integer = 4, fraction = 10)
    private Float rightRise;

    @ExcelProperty("左大腿围")
    @Digits(integer = 4, fraction = 10)
    private Float leftThighCircumference;

    @ExcelProperty("右大腿围")
    @Digits(integer = 4, fraction = 10)
    private Float rightThighCircumference;

    @ExcelProperty("左膝盖围")
    @Digits(integer = 4, fraction = 10)
    private Float leftKneeCircumference;

    @ExcelProperty("右膝盖围")
    @Digits(integer = 4, fraction = 10)
    private Float rightKneeCircumference;

    @ExcelProperty("左小腿围")
    @Digits(integer = 4, fraction = 10)
    private Float leftCalfCircumference;

    @ExcelProperty("右小腿围")
    @Digits(integer = 4, fraction = 10)
    private Float rightCalfCircumference;

    @ExcelProperty("左脚腕围")
    @Digits(integer = 4, fraction = 10)
    private Float leftAnkleCircumference;

    @ExcelProperty("右脚腕围")
    @Digits(integer = 4, fraction = 10)
    private Float rightAnkleCircumference;

    @ExcelProperty("左裤长")
    @Digits(integer = 4, fraction = 10)
    private Float leftPantLength;

    @ExcelProperty("右裤长")
    @Digits(integer = 4, fraction = 10)
    private Float rightPantLength;

    @ExcelProperty("预留字段1")
    @Digits(integer = 4, fraction = 10)
    private Float field1;

    @ExcelProperty("预留字段2")
    @Digits(integer = 4, fraction = 10)
    private Float field2;

    @ExcelProperty("预留字段3")
    @Digits(integer = 4, fraction = 10)
    private Float field3;

    @ExcelProperty("预留字段4")
    @Digits(integer = 4, fraction = 10)
    private Float field4;

    @ExcelProperty("预留字段5")
    @Digits(integer = 4, fraction = 10)
    private Float field5;

    @ExcelProperty("预留字段6")
    @Digits(integer = 4, fraction = 10)
    private Float field6;

    @ExcelProperty("预留字段7")
    @Digits(integer = 4, fraction = 10)
    private Float field7;

    @ExcelProperty("预留字段8")
    @Digits(integer = 4, fraction = 10)
    private Float field8;

    @ExcelProperty("预留字段9")
    @Digits(integer = 4, fraction = 10)
    private Float field9;

    @ExcelProperty("预留字段10")
    @Digits(integer = 4, fraction = 10)
    private Float field10;

    @ExcelProperty("备注")
    private String remark;

    @ExcelIgnore
    private Pilot pilot;
}