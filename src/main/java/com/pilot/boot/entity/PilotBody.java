package com.pilot.boot.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Digits;
import java.io.Serializable;

/**
 * pilotBody
 *
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

    @Digits(integer = 4, fraction = 2)
    private Float height;

    @Digits(integer = 4, fraction = 2)
    private Float weight;

    @Digits(integer = 4, fraction = 2)
    private Float neckCircumference;

    @Digits(integer = 4, fraction = 2)
    private Float neckRootCircumference;

    @Digits(integer = 4, fraction = 2)
    private Float leftShoulderLength;

    @Digits(integer = 4, fraction = 2)
    private Float rightShoulderLength;

    @Digits(integer = 4, fraction = 2)
    private Float shoulderWidth;

    @Digits(integer = 4, fraction = 2)
    private Float backWidth;

    @Digits(integer = 4, fraction = 2)
    private Float bustCircumference;

    @Digits(integer = 4, fraction = 2)
    private Float breastWidth;

    @Digits(integer = 4, fraction = 2)
    private Float underBustCircumference;

    @Digits(integer = 4, fraction = 2)
    private Float waistCircumference;

    @Digits(integer = 4, fraction = 2)
    private Float hipsCircumference;

    @Digits(integer = 4, fraction = 2)
    private Float upperLeftArmCircumference;

    @Digits(integer = 4, fraction = 2)
    private Float upperRightArmCircumference;

    @Digits(integer = 4, fraction = 2)
    private Float leftWristCircumference;

    @Digits(integer = 4, fraction = 2)
    private Float rightWristCircumference;

    @Digits(integer = 4, fraction = 2)
    private Float leftThighRootCircumference;

    @Digits(integer = 4, fraction = 2)
    private Float rightThighRootCircumference;

    @Digits(integer = 4, fraction = 2)
    private Float leftMidThighCircumference;

    @Digits(integer = 4, fraction = 2)
    private Float rightMidThighCircumference;

    @Digits(integer = 4, fraction = 2)
    private Float leftKneeCircumference;

    @Digits(integer = 4, fraction = 2)
    private Float rightKneeCircumference;

    @Digits(integer = 4, fraction = 2)
    private Float leftCalfCircumference;

    @Digits(integer = 4, fraction = 2)
    private Float rightCalfCircumference;

    @Digits(integer = 4, fraction = 2)
    private Float upperLeftAnkleCircumference;

    @Digits(integer = 4, fraction = 2)
    private Float upperRightAnkleCircumference;

    @Digits(integer = 4, fraction = 2)
    private Float leftFootLength;

    @Digits(integer = 4, fraction = 2)
    private Float rightFootLength;

    @Digits(integer = 4, fraction = 2)
    private Float torsoLength;

    @Digits(integer = 4, fraction = 2)
    private Float waistHeight;

    @Digits(integer = 4, fraction = 2)
    private Float hipHeight;

    @Digits(integer = 4, fraction = 2)
    private Float straightCrotch;

    @Digits(integer = 4, fraction = 2)
    private Float leftKneeCircumferenceHeight;

    @Digits(integer = 4, fraction = 2)
    private Float rightKneeCircumferenceHeight;

    @Digits(integer = 4, fraction = 2)
    private Float leftLateralAnkleHeight;

    @Digits(integer = 4, fraction = 2)
    private Float rightLateralAnkleHeight;

    @Digits(integer = 4, fraction = 2)
    private Float armpitDepth;

    @Digits(integer = 4, fraction = 2)
    private Float backWaistLength;

    @Digits(integer = 4, fraction = 2)
    private Float cervicalVertebraPointKneeBendLength;

    @Digits(integer = 4, fraction = 2)
    private Float cervicalPointHeight;

    @Digits(integer = 4, fraction = 2)
    private Float leftNeckPointNipplePointLength;

    @Digits(integer = 4, fraction = 2)
    private Float rightNeckPointNipplePointLength;

    @Digits(integer = 4, fraction = 2)
    private Float leftFrontWaistLength;

    @Digits(integer = 4, fraction = 2)
    private Float rightFrontWaistLength;

    @Digits(integer = 4, fraction = 2)
    private Float leftWaistHipLength;

    @Digits(integer = 4, fraction = 2)
    private Float rightWaistHipLength;

    @Digits(integer = 4, fraction = 2)
    private Float trunkCircumference;

    @Digits(integer = 4, fraction = 2)
    private Float perineumUpperFrontBackLength;

    @Digits(integer = 4, fraction = 2)
    private Float leftForearmCircumference;

    @Digits(integer = 4, fraction = 2)
    private Float rightForearmCircumference;

    @Digits(integer = 4, fraction = 2)
    private Float leftLegOutsideLength;

    @Digits(integer = 4, fraction = 2)
    private Float rightLegOutsideLength;

    @Digits(integer = 4, fraction = 2)
    private Float leftThighLength;

    @Digits(integer = 4, fraction = 2)
    private Float rightThighLength;

    @Digits(integer = 4, fraction = 2)
    private Float perinealHeight;

    @Digits(integer = 4, fraction = 2)
    private Float leftShoulderSlope;

    @Digits(integer = 4, fraction = 2)
    private Float rightShoulderSlope;

    @Digits(integer = 4, fraction = 2)
    private Float leftUpperArmLength;

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
}
