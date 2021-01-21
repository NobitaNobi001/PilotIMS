package com.pilot.boot.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pilot.boot.entity.Pilot;
import com.pilot.boot.service.PilotService;
import com.pilot.boot.utils.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.validation.Valid;

/**
 * pilot information controller
 *
 * @author ezuy
 * @date 20/12/22 15:49
 */
@Validated
@RestController
public class PilotController {

    @Autowired
    private PilotService pilotService;

    /**
     * create
     * add a pilot
     *
     * @param pilot
     * @return
     */
    @PostMapping("/pilot/add")
    public CommonResult addPilot(@Valid @RequestBody Pilot pilot) {
        return new CommonResult(200, "添加成功", pilot);
    }

    /**
     * add pilot by excel
     *
     * @return
     */
    @PostMapping("/pilot/add/excel")
    public CommonResult addPilotsByExcel(@RequestParam("file") CommonsMultipartFile file) {
        return new CommonResult(200, "添加成功");
    }

    /**
     * retrieve
     * get all pilots
     *
     * @param current
     * @param size
     * @return
     */
    @GetMapping("/pilot/list")
    public CommonResult findALLPilot(@RequestParam(name = "current", defaultValue = "1") Long current, @RequestParam(name = "size", defaultValue = "5") Long size) {

        //1.封装分页对象
        Page<Pilot> pilotPage = new Page<>(current, size);

        //2.返回查询到的分页记录
        IPage<Pilot> pilotIPage = pilotService.findAllPilotWithPage(pilotPage);

        //3.返回给前端
        return new CommonResult(200, "查询成功", pilotIPage);
    }

    /**
     * find by pilotId
     *
     * @param pilotId
     * @return
     */
    @GetMapping("/pilot/get/{pilotId}")
    public CommonResult findPilotById(@PathVariable("pilotId") Long pilotId) {
        return new CommonResult(200, "查询成功,飞行员记录为:" + pilotId, "success");
    }

    /**
     * update
     * update pilot by id
     *
     * @param pilot
     * @return
     */
    @PutMapping("/pilot/update")
    public CommonResult updatePilotById(@Valid @RequestBody Pilot pilot, BindingResult result) {
        return new CommonResult(200, "更新成功", pilot);
    }


    /**
     * delete
     * delete pilot by pilotId
     *
     * @param pilotId
     * @return
     */
    @DeleteMapping("/pilot/delete/{pilotId}")
    public CommonResult deletePilotById(@PathVariable("pilotId") Long pilotId) {
        return new CommonResult(200, "删除成功,删除的飞行员id为:" + pilotId);
    }

    /**
     * delete pilot by pilotIds
     *
     * @param pilotIds
     * @return
     */
    @DeleteMapping("/pilot/batchDelete/{pilotIds}")
    public CommonResult deletePilotByIds(@PathVariable("pilotIds") Long[] pilotIds) {
        return new CommonResult(200, "删除成功,删除的飞行员id为:" + pilotIds);
    }
}
