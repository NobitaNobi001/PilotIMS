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

    @NotEmpty
    @NotBlank
    @Length(min = 10, max = 10, message = "日期信息不合法")
    private String scanTime;

    @NotEmpty
    @NotBlank
    @Length(max = 100,message = "扫描地点长度过长")
    private String scanLocation;

    @NotEmpty
    @NotBlank
    @Length(max = 50,message = "文件名过长")
    private String dataFileName;

    @NotEmpty
    @NotBlank
    @Length(max = 255,message = "文件存放地址过长")
    private String fileStorageAddress;

    private String remark;

    private Pilot pilot;
}
