package com.pilot.boot.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * scan
 * @author ezuy
 * @date 20/12/22 15:43
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("scan")
public class Scan implements Serializable {

    @TableId("pilot_id")
    private Long pilotId;

    private Date scanTime;

    private String scanLocation;

    private String dataFileName;

    private String fileStorageAddress;

    private String remark;
}
