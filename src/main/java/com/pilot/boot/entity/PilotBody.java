package com.pilot.boot.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * pilotBody
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

    private Float height;

    private Float weight;

    private Float neckCircumference;

    private Float neckRootCircumference;

    private Float leftShoulderLength;

    private Float rightShoulderLength;

    private Float shoulderWidth;

    private Float backWidth;

    private Float bustCircumference;

    private Float breastWidth;

    private Float underBustCircumference;

    private Float waistCircumference;

    private Float hipsCircumference;

    private Float upperLeftArmCircumference;

    private Float upperRightArmCircumference;

    private Float leftWristCircumference;

    private Float rightWristCircumference;

    private Float leftThighRootCircumference;

    private Float rightThighRootCircumference;

    private Float leftMidThighCircumference;

    private Float rightMidThighCircumference;

    private Float leftKneeCircumference;

    private Float rightKneeCircumference;

    private Float leftCalfCircumference;

    private Float rightCalfCircumference;

    private Float upperLeftAnkleCircumference;

    private Float upperRightAnkleCircumference;

    private Float leftFootLength;

    private Float rightFootLength;

    private Float torsoLength;

    private Float waistHeight;

    private Float hipHeight;

    private Float straightCrotch;

    private Float leftKneeCircumferenceHeight;

    private Float rightKneeCircumferenceHeight;

    private Float leftLateralAnkleHeight;

    private Float rightLateralAnkleHeight;

    private Float armpitDepth;

    private Float backWaistLength;

    private Float cervicalVertebraPointKneeBendLength;

    private Float cervicalPointHeight;

    private Float leftNeckPointNipplePointLength;

    private Float rightNeckPointNipplePointLength;

    private Float leftFrontWaistLength;

    private Float rightFrontWaistLength;

    private Float leftWaistHipLength;

    private Float rightWaistHipLength;

    private Float trunkCircumference;

    private Float perineumUpperFrontBackLength;

    private Float leftForearmCircumference;

    private Float rightForearmCircumference;

    private Float leftLegOutsideLength;

    private Float rightLegOutsideLength;

    private Float leftThighLength;

    private Float rightThighLength;

    private Float perinealHeight;

    private Float leftShoulderSlope;

    private Float rightShoulderSlope;

    private Float leftUpperArmLength;

    private Float rightUpperArmLength;

    private String remark;
}
