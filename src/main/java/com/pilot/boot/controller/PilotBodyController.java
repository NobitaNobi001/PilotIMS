package com.pilot.boot.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pilot.boot.entity.Pilot;
import com.pilot.boot.entity.PilotBody;
import com.pilot.boot.service.PilotBodyService;
import com.pilot.boot.utils.CommonResult;
import com.pilot.boot.utils.ConstantUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.validation.Valid;
import java.util.List;
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

        //1.insert operation
        int result = pilotBodyService.addPilotBodyByPilotId(pilotBody);

        //2.check and response
        if (result == 0) {
            return CommonResult.fail(100, "添加失败");
        }
        return CommonResult.success("添加成功");
    }

    @PostMapping("/pilotBody/add/excel")
    public CommonResult addPilotBodyByExcel(@RequestParam("file") CommonsMultipartFile file) {
        return CommonResult.success("添加成功");
    }

    @PostMapping("/pilotBody/condition/list")
    public CommonResult findPilotBodyWithCondition(@RequestParam(name = "current", defaultValue = "1") Long current,
                                                   @RequestParam(name = "size", defaultValue = "8") Long size,
                                                   @RequestBody Map<String, List<Long>> listCondition) {
        //1.Encapsulate the pilotBody page
        Page<PilotBody> pilotBodyPage = new Page<>(current, size);

        //2.find all pilotBody with page
        IPage<PilotBody> pilotBodyIPage = pilotBodyService.findPilotBodyWithCondition(pilotBodyPage, listCondition);

        //3.response to front
        return CommonResult.success(pilotBodyIPage);
    }

    @GetMapping("/pilotBody/list")
    public CommonResult findAllPilotBody(@RequestParam(name = "current", defaultValue = "1") Long current,
                                         @RequestParam(name = "size", defaultValue = "8") Long size) {

        //1.Encapsulate the pilotBody page
        Page<PilotBody> pilotBodyPage = new Page<>(current, size);
        //2.find pilotBody with page
        IPage iPage = pilotBodyService.findAllPilotBody(pilotBodyPage);
        //3.response to front
        return CommonResult.success(iPage);
    }

    @GetMapping("/pilotBody/get/{pilotId}")
    public CommonResult findPilotBodyById(@PathVariable("pilotId") Long pilotId) {

        //1.get result
        PilotBody pilotBody = pilotBodyService.findPilotBodyByPilotId(pilotId);
        //2.check and response
        if (pilotBody == null) {
            return CommonResult.fail(100, "查无此人");
        }
        return CommonResult.success(pilotBody);
    }

    @PutMapping("/pilotBody/update")
    public CommonResult updatePilotBodyById(@Valid @RequestBody PilotBody pilotBody) {

        //1.update operation
        int result = pilotBodyService.updatePilotBodyByPilotId(pilotBody);
        //2.check and response
        if (result == 0) {
            return CommonResult.fail(100, "更新失败");
        }
        return CommonResult.success(pilotBody);
    }

    @DeleteMapping("/pilotBody/delete")
    public CommonResult deletePilotBodyById(@RequestBody Map<String, Long> pilotIdMap) {

        //1.get pilotId
        Long pilotId = pilotIdMap.get(ConstantUtil.pilotId.toString());
        //2.delete operation
        int result = pilotBodyService.deletePilotBodyByPilotId(pilotId);
        //3.check and response
        if (result == 0) {
            return CommonResult.fail(100, "删除失败");
        }
        return CommonResult.success("删除成功");
    }

    @DeleteMapping("/pilotBody/batchDelete")
    public CommonResult deletePilotBodyByIds(@RequestBody Map<String, List<Long>> pilotIdsMap) {

        //1.get pilotIds
        List<Long> pilotId = pilotIdsMap.get(ConstantUtil.pilotId.toString());
        //2.delete operation
        int result = pilotBodyService.batchDeletePilotBodyByPilotIds(pilotId);
        //3.check and response
        if (result == 0) {
            return CommonResult.fail(100, "删除失败");
        }
        return CommonResult.success("删除成功");
    }

    @PostMapping("/pilotBody/check")
    public CommonResult checkPilotBodyExist(@RequestBody Map<String, Long> pilotIdMap) {

        //1.get service return result
        boolean flag = pilotBodyService.checkPilotBodyExist(pilotIdMap);
        //2.check
        if (flag) {
            return CommonResult.fail(100, "此飞行员体型数据信息已存在");
        }
        return CommonResult.success("此飞行员可以进行体型数据信息的添加");

    }

}
