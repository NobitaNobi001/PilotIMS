package com.pilot.boot.controller;

import com.pilot.boot.entity.Scan;
import com.pilot.boot.service.ScanService;
import com.pilot.boot.utils.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ClassUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.util.Map;

/**
 * scan data controller
 *
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
    public CommonResult addScanService(@RequestParam("file") CommonsMultipartFile file, @RequestBody Scan scan) {


        return new CommonResult(200, "添加成功");
    }

    @GetMapping("/scan/get/{pilotId}")
    public CommonResult downloadFileById(@PathVariable("pilotId") Long pilotId) {
        return new CommonResult(200, "添加成功");
    }

    @PutMapping("/scan/update")
    public CommonResult updateScan(@Valid @RequestBody Scan scan) {
        return new CommonResult(200, "添加成功");
    }

    @DeleteMapping("/scan/delete")
    public CommonResult deletePilotScanByPilotId(@RequestBody Map<String,String> scan) {
        return new CommonResult(200, "添加成功");
    }

    @DeleteMapping("/scan/batchDelete")
    public CommonResult deletePilotScans(@RequestBody Map<String,Object> scan) {
        return new CommonResult(200, "添加成功");
    }

}
