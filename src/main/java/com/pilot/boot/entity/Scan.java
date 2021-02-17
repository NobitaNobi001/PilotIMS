package com.pilot.boot.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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

    @NotNull
    private String scanTime;

    @NotNull
    @Length(min = 1,max = 10)
    private String scanLocation;

    private String dataFileName;

    @NotNull
    private String fileStorageAddress;

    private String remark;

    private Pilot pilot;
}
