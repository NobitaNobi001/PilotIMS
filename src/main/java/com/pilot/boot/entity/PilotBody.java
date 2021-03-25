package com.pilot.boot.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Digits;
import java.io.Serializable;

/**
 * @author ezuy
 * @date 20/12/22 15:43
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("pilot_body")
public class PilotBody implements Serializable {

    @TableId("pilot_id")
    private Long pilotId;

    /**
     * 0表示未删除 1表示已删除
     */
    private Long deleted;

    @ExcelProperty("身高")
    @Digits(integer = 4, fraction = 2)
    private Float height;

    @ExcelProperty("体重")
    @Digits(integer = 4, fraction = 2)
    private Float weight;

    @ExcelProperty("领围")
    @Digits(integer = 4, fraction = 2)
    private Float neckCircumference;

    @ExcelProperty("颈根围")
    @Digits(integer = 4, fraction = 2)
    private Float neckRootCircumference;

    @ExcelProperty("左肩长")
    @Digits(integer = 4, fraction = 2)
    private Float leftShoulderLength;

    @ExcelProperty("右肩长")
    @Digits(integer = 4, fraction = 2)
    private Float rightShoulderLength;

    @ExcelProperty("总肩宽")
    @Digits(integer = 4, fraction = 2)
    private Float shoulderWidth;

    @ExcelProperty("背宽")
    @Digits(integer = 4, fraction = 2)
    private Float backWidth;

    @ExcelProperty("胸围")
    @Digits(integer = 4, fraction = 2)
    private Float bustCircumference;

    @ExcelProperty("乳间宽")
    @Digits(integer = 4, fraction = 2)
    private Float breastWidth;

    @ExcelProperty("下胸围")
    @Digits(integer = 4, fraction = 2)
    private Float underBustCircumference;

    @ExcelProperty("腰围")
    @Digits(integer = 4, fraction = 2)
    private Float waistCircumference;

    @ExcelProperty("臀围")
    @Digits(integer = 4, fraction = 2)
    private Float hipsCircumference;

    @ExcelProperty("左上臂围")
    @Digits(integer = 4, fraction = 2)
    private Float upperLeftArmCircumference;

    @ExcelProperty("右上臂围")
    @Digits(integer = 4, fraction = 2)
    private Float upperRightArmCircumference;

    @ExcelProperty("左手腕围")
    @Digits(integer = 4, fraction = 2)
    private Float leftWristCircumference;

    @ExcelProperty("右手腕围")
    @Digits(integer = 4, fraction = 2)
    private Float rightWristCircumference;

    @ExcelProperty("左大腿根围")
    @Digits(integer = 4, fraction = 2)
    private Float leftThighRootCircumference;

    @ExcelProperty("右大腿根围")
    @Digits(integer = 4, fraction = 2)
    private Float rightThighRootCircumference;

    @ExcelProperty("左大腿中部围")
    @Digits(integer = 4, fraction = 2)
    private Float leftMidThighCircumference;

    @ExcelProperty("右大腿中部围")
    @Digits(integer = 4, fraction = 2)
    private Float rightMidThighCircumference;

    @ExcelProperty("左膝围")
    @Digits(integer = 4, fraction = 2)
    private Float leftKneeCircumference;

    @ExcelProperty("右膝围")
    @Digits(integer = 4, fraction = 2)
    private Float rightKneeCircumference;

    @ExcelProperty("左腿肚围")
    @Digits(integer = 4, fraction = 2)
    private Float leftCalfCircumference;

    @ExcelProperty("右腿肚围")
    @Digits(integer = 4, fraction = 2)
    private Float rightCalfCircumference;

    @ExcelProperty("左踝上围")
    @Digits(integer = 4, fraction = 2)
    private Float upperLeftAnkleCircumference;

    @ExcelProperty("右踝上围")
    @Digits(integer = 4, fraction = 2)
    private Float upperRightAnkleCircumference;

    @ExcelProperty("左足长")
    @Digits(integer = 4, fraction = 2)
    private Float leftFootLength;

    @ExcelProperty("右足长")
    @Digits(integer = 4, fraction = 2)
    private Float rightFootLength;

    @ExcelProperty("躯干长")
    @Digits(integer = 4, fraction = 2)
    private Float torsoLength;

    @ExcelProperty("腰围高")
    @Digits(integer = 4, fraction = 2)
    private Float waistHeight;

    @ExcelProperty("臀围高")
    @Digits(integer = 4, fraction = 2)
    private Float hipHeight;

    @ExcelProperty("直裆")
    @Digits(integer = 4, fraction = 2)
    private Float straightCrotch;

    @ExcelProperty("左膝围高")
    @Digits(integer = 4, fraction = 2)
    private Float leftKneeCircumferenceHeight;

    @ExcelProperty("右膝围高")
    @Digits(integer = 4, fraction = 2)
    private Float rightKneeCircumferenceHeight;

    @ExcelProperty("左外踝高")
    @Digits(integer = 4, fraction = 2)
    private Float leftLateralAnkleHeight;

    @ExcelProperty("右外踝高")
    @Digits(integer = 4, fraction = 2)
    private Float rightLateralAnkleHeight;

    @ExcelProperty("腋窝深")
    @Digits(integer = 4, fraction = 2)
    private Float armpitDepth;

    @ExcelProperty("背腰长")
    @Digits(integer = 4, fraction = 2)
    private Float backWaistLength;

    @ExcelProperty("颈椎点至膝弯长")
    @Digits(integer = 4, fraction = 2)
    private Float cervicalVertebraPointKneeBendLength;

    @ExcelProperty("颈椎点高")
    @Digits(integer = 4, fraction = 2)
    private Float cervicalPointHeight;

    @ExcelProperty("左侧颈点至乳头点长")
    @Digits(integer = 4, fraction = 2)
    private Float leftNeckPointNipplePointLength;

    @ExcelProperty("右侧颈点至乳头点长")
    @Digits(integer = 4, fraction = 2)
    private Float rightNeckPointNipplePointLength;

    @ExcelProperty("左前腰长")
    @Digits(integer = 4, fraction = 2)
    private Float leftFrontWaistLength;

    @ExcelProperty("右前腰长")
    @Digits(integer = 4, fraction = 2)
    private Float rightFrontWaistLength;

    @ExcelProperty("左腰至臀长")
    @Digits(integer = 4, fraction = 2)
    private Float leftWaistHipLength;

    @ExcelProperty("右腰至臀长")
    @Digits(integer = 4, fraction = 2)
    private Float rightWaistHipLength;

    @ExcelProperty("躯干围")
    @Digits(integer = 4, fraction = 2)
    private Float trunkCircumference;

    @ExcelProperty("会阴上部前后长")
    @Digits(integer = 4, fraction = 2)
    private Float perineumUpperFrontBackLength;

    @ExcelProperty("左前臀围")
    @Digits(integer = 4, fraction = 2)
    private Float leftForearmCircumference;

    @ExcelProperty("右前臀围")
    @Digits(integer = 4, fraction = 2)
    private Float rightForearmCircumference;

    @ExcelProperty("左腿外侧长")
    @Digits(integer = 4, fraction = 2)
    private Float leftLegOutsideLength;

    @ExcelProperty("右腿外侧长")
    @Digits(integer = 4, fraction = 2)
    private Float rightLegOutsideLength;

    @ExcelProperty("左大腿长")
    @Digits(integer = 4, fraction = 2)
    private Float leftThighLength;

    @ExcelProperty("右大腿长")
    @Digits(integer = 4, fraction = 2)
    private Float rightThighLength;

    @ExcelProperty("会阴高")
    @Digits(integer = 4, fraction = 2)
    private Float perinealHeight;

    @ExcelProperty("左肩斜度")
    @Digits(integer = 4, fraction = 2)
    private Float leftShoulderSlope;

    @ExcelProperty("右肩斜度")
    @Digits(integer = 4, fraction = 2)
    private Float rightShoulderSlope;

    @ExcelProperty("左上臂长")
    @Digits(integer = 4, fraction = 2)
    private Float leftUpperArmLength;

    @ExcelProperty("右上臂长")
    @Digits(integer = 4, fraction = 2)
    private Float rightUpperArmLength;

    @Digits(integer = 4, fraction = 2)
    private Float field1;

    @Digits(integer = 4, fraction = 2)
    private Float field2;

    @Digits(integer = 4, fraction = 2)
    private Float field3;

    @Digits(integer = 4, fraction = 2)
    private Float field4;

    @Digits(integer = 4, fraction = 2)
    private Float field5;

    @Digits(integer = 4, fraction = 2)
    private Float field6;

    @Digits(integer = 4, fraction = 2)
    private Float field7;

    @Digits(integer = 4, fraction = 2)
    private Float field8;

    @Digits(integer = 4, fraction = 2)
    private Float field9;

    @Digits(integer = 4, fraction = 2)
    private Float field10;

    private String remark;

    private Pilot pilot;
}
