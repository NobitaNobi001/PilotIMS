package com.pilot.boot.controller;

import com.pilot.boot.entity.PilotBody;
import com.pilot.boot.service.PilotBodyService;
import com.pilot.boot.utils.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.validation.Valid;
import java.util.Map;

/**
 * @author ezuy
 * @date 20/12/22 15:49
 */
@Slf4j
@Validated
@CrossOrigin
@RestController
public class PilotBodyController {

    @Autowired
    private PilotBodyService pilotBodyService;

    @PostMapping("/pilotBody/add")
    public CommonResult addPilotBody(@Valid @RequestBody PilotBody pilotBody) {
        return new CommonResult(200, "添加成功", pilotBody);
    }

    @PostMapping("/pilotBody/add/excel")
    public CommonResult addPilotBodyByExcel(@RequestParam("file") CommonsMultipartFile file) {
        return new CommonResult(200, "添加成功");
    }

    @GetMapping("/pilotBody/list")
    public CommonResult findAllPilotBody(@RequestParam(name = "current", defaultValue = "1") Long current, @RequestParam(name = "size", defaultValue = "%") Long size) {
        return new CommonResult(200, "查询成功", "success");
    }

    @GetMapping("/pilotBody/get/{pilotId}")
    public CommonResult findPilotBodyById(@PathVariable("pilotId") Long pilotId) {
        return new CommonResult(200, "查询成功,飞行员身体信息记录为:" + pilotId, "success");
    }

    @PutMapping("/pilotBody/update")
    public CommonResult updatePilotBodyById(@Valid @RequestBody PilotBody pilotBody, BindingResult result) {
        return new CommonResult(200, "更新成功", pilotBody);
    }

    @DeleteMapping("/pilotBody/delete")
    public CommonResult deletePilotBodyById(@RequestBody Map<String, Long> pilotIdMap) {
        return new CommonResult(200, "删除成功,删除的飞行员id为:");
    }

    @DeleteMapping("/pilotBody/batchDelete")
    public CommonResult deletePilotBodyByIds(@RequestBody Map<String, Object> pilotIdsMap) {
        return new CommonResult(200, "删除成功,删除的飞行员身体数据id为:" + pilotIdsMap);
    }

    @PostMapping("/pilotBody/check")
    public CommonResult checkPilotBodyExist(@RequestBody Map<String, Long> pilotIdMap) {

        //1.get service return result
        boolean flag = pilotBodyService.checkPilotBodyExist(pilotIdMap);

        //2.check
        if (flag) {
            return new CommonResult(100, "此飞行员身体数据信息已存在");
        } else {
            return new CommonResult(200, "此飞行员可以进行身体数据信息的添加");
        }

    }
}
