package com.pilot.boot.controller;

import com.pilot.boot.entity.PilotBody;
import com.pilot.boot.service.PilotBodyService;
import com.pilot.boot.utils.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.validation.Valid;

/**
 * pilotBody controller
 *
 * @author ezuy
 * @date 20/12/22 15:49
 */
@Validated
@RestController
public class PilotBodyController {

    @Autowired
    private PilotBodyService pilotBodyService;

    /**
     * create
     * add a pilotBody
     *
     * @param pilotBody
     * @return
     */
    @PostMapping("/pilotBody/add")
    public CommonResult addPilotBody(@Valid @RequestBody PilotBody pilotBody, BindingResult result) {
        return new CommonResult(200, "添加成功", pilotBody);
    }

    /**
     * add pilotBody by excel
     *
     * @return
     */
    @PostMapping("/pilotBody/excel/add")
    public CommonResult addPilotBodyByExcel(@RequestParam("file") CommonsMultipartFile file) {
        return new CommonResult(200, "添加成功");
    }

    /**
     * retrieve
     * get all pilotBody
     *
     * @param current
     * @param size
     * @return
     */
    @GetMapping("/pilotBody/list")
    public CommonResult findALLPilotBody(@RequestParam(name = "current", defaultValue = "1") Long current, @RequestParam(name = "size", defaultValue = "%") Long size) {
        return new CommonResult(200, "查询成功", "success");
    }

    /**
     * find by pilotId
     *
     * @param pilotId
     * @return
     */
    @GetMapping("/pilotBody/get/{pilotId}")
    public CommonResult findPilotBodyById(@PathVariable("pilotId") Long pilotId) {
        return new CommonResult(200, "查询成功,飞行员身体信息记录为:" + pilotId, "success");
    }

    /**
     * update
     * update pilotBody by id
     *
     * @param pilotBody
     * @return
     */
    @PutMapping("/pilotBody/update")
    public CommonResult updatePilotBodyById(@Valid @RequestBody PilotBody pilotBody, BindingResult result) {
        return new CommonResult(200, "更新成功", pilotBody);
    }


    /**
     * delete
     * delete pilotBody by pilotId
     *
     * @param pilotId
     * @return
     */
    @DeleteMapping("/pilotBody/delete/{pilotId}")
    public CommonResult deletePilotBodyById(@PathVariable("pilotId") Long pilotId) {
        return new CommonResult(200, "删除成功,删除的飞行员id为:" + pilotId);
    }

    /**
     * delete pilot by pilotIds
     *
     * @param pilotIds
     * @return
     */
    @DeleteMapping("/pilotBody/batchDelete/{pilotIds}")
    public CommonResult deletePilotBodyByIds(@PathVariable("pilotIds") Long[] pilotIds) {
        return new CommonResult(200, "删除成功,删除的飞行员身体数据id为:" + pilotIds);
    }
}
