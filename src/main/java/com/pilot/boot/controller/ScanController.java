package com.pilot.boot.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pilot.boot.entity.Scan;
import com.pilot.boot.service.ScanService;
import com.pilot.boot.utils.CommonResult;
import com.pilot.boot.utils.ConstantUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * @author ezuy
 * @date 20/12/22 15:50
 */
@Slf4j
@Validated
@CrossOrigin
@RestController
public class ScanController {

    @Autowired
    private ScanService scanService;

    @PostMapping("/scan/add")
    public CommonResult addScanAndFile(@RequestParam("file") MultipartFile file, @Valid Scan scan) {

        return scanService.addScan(scan, file);
    }

    @PostMapping("/scan/check")
    public CommonResult checkScanExists(@RequestBody Map<String, Long> pilotIdMaps) {

        //1.get pilotId and check format
        Long pilotId = pilotIdMaps.get(ConstantUtil.pilotId.toString());
        if (null == pilotId || ("".equals(pilotId))) {
            return CommonResult.fail(100, "不存在此飞行员");
        }

        //2.get result
        boolean flag = scanService.checkScanExist(pilotId);

        //3.check and response
        if (flag) {
            return CommonResult.fail(100, "此飞行员点云文件信息已存在");
        }
        return CommonResult.success("此飞行员可以进行点云文件的添加");
    }

    @GetMapping("/scan/get/{pilotId}")
    public CommonResult downloadFileById(@PathVariable("pilotId") Long pilotId) {

        //1.get scan
        Scan scan = scanService.findScanByPilotId(pilotId);
        //2.check and response
        if (scan == null) {
            return new CommonResult(100, "此飞行员的点云文件信息不存在!");
        }
        return CommonResult.success(scan);
    }

    @GetMapping("/scan/list")
    public CommonResult findAllScans(@RequestParam(name = "current", required = false) Long current,
                                     @RequestParam(name = "size", required = false) Long size) {
        //1.check page or not
        if (current == null || size == null) {
            //2.get result
            List<Scan> scans = scanService.findAllScan();
            //3.response to front
            return CommonResult.success(scans);
        } else {
            //2.Encapsulate the scan page
            Page<Scan> scanPage = new Page<>(current, size);
            //3.get result
            IPage<Scan> scanIPage = scanService.findScanWithPage(scanPage);
            //4.response to front
            return CommonResult.success(scanIPage);
        }
    }

    @PutMapping("/scan/update")
    public CommonResult updateScan(@Valid @RequestBody Scan scan) {
        //1.get result
        int result = scanService.updateScanByPilotId(scan);
        //2.check and response
        if (result == 0) {
            return CommonResult.fail(100, "更新失败");
        }
        return CommonResult.success("更新成功");
    }

    @DeleteMapping("/scan/delete")
    public CommonResult deletePilotScanByPilotId(@RequestBody Map<String, Long> scanPilotId) {

        //1.get pilotId
        Long pilotId = scanPilotId.get(ConstantUtil.pilotId.toString());
        //2.check and get scan
        Scan scan = scanService.findScanByPilotId(pilotId);
        if (scan == null) {
            return CommonResult.fail(100, "需要进行删除的点云文件信息不存在");
        }
        //3.delete
        //delete record
        int result = scanService.deleteScanByPilotId(pilotId);
        //4.check and response
        if (result == 0) {
            return CommonResult.fail(100, "删除失败");
        }
        //delete file
        File file = new File(scan.getFileStorageAddress());
        file.delete();
        return CommonResult.success("删除成功");
    }

    @DeleteMapping("/scan/batchDelete")
    public CommonResult deletePilotScans(@RequestBody Map<String, List<Long>> scanPilotIds) {

        //1.get
        List<Long> pilotId = scanPilotIds.get(ConstantUtil.pilotId.toString());
        //2.operation
        int result = scanService.batchDeleteScans(pilotId);
        //3.check and response
        if (result == 0) {
            return CommonResult.fail(100, "删除失败");
        }
        return CommonResult.success("删除成功");
    }

}
