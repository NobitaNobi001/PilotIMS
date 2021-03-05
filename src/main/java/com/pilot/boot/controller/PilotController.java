package com.pilot.boot.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.builder.ExcelReaderSheetBuilder;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pilot.boot.entity.Pilot;
import com.pilot.boot.entity.Scan;
import com.pilot.boot.entity.excel.PilotExcel;
import com.pilot.boot.listener.PilotListener;
import com.pilot.boot.service.PilotService;
import com.pilot.boot.utils.CommonResult;
import com.pilot.boot.utils.ConstantUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author ezuy
 * @date 20/12/22 15:49
 */
@Validated
@CrossOrigin
@RestController
public class PilotController {

    @Autowired
    private PilotService pilotService;
    @Autowired
    private PilotListener pilotListener;

    @PostMapping("/pilot/add")
    public CommonResult addPilot(@Valid @RequestBody Pilot pilot) {

        //1.get result
        int result = pilotService.addPilot(pilot);

        //2.check result and response
        if (result == 0) {
            return CommonResult.fail(100, "添加失败");
        }

        return CommonResult.success("添加成功");
    }

    @PostMapping("/pilot/add/excel")
    public CommonResult addPilotsByExcel(@RequestParam("file") MultipartFile file) {

        //1.check file == null
        if (file.getSize() == 0) {
            return new CommonResult(100, "请选择文件");
        }

        //2.get file suffix
        int begin = file.getOriginalFilename().indexOf(".");
        String suffix = file.getOriginalFilename().substring(begin);

        //3.check file format
        if (!(".xls").equals(suffix)) {
            return new CommonResult(100, "请上传xls格式文件");
        }

        try {
            //4.read excel
            //4.1 work sheet
            ExcelReaderBuilder readerBuilder = EasyExcel.read(file.getInputStream(), PilotExcel.class, pilotListener);
            //4.2 work table
            ExcelReaderSheetBuilder sheetBuilder = readerBuilder.sheet();
            //4.3 read table
            sheetBuilder.headRowNumber(1).doRead();

        } catch (IOException e) {

            //5. clear remain data
            pilotListener.getPilots().clear();
            e.printStackTrace();
            return CommonResult.fail(100, "添加失败");
        }

        return CommonResult.success("添加成功");
    }

    @GetMapping("/pilot/get/{pilotId}")
    public CommonResult findPilotById(@PathVariable("pilotId") Long pilotId) {

        //1.get result
        Pilot pilot = pilotService.findPilotById(pilotId);

        //2.check and response
        if (pilot == null) {
            return CommonResult.fail(100, "没有此飞行员");
        }
        return CommonResult.success(pilot);
    }

    @GetMapping("/pilot/list")
    public CommonResult findAllPilot(@RequestParam(name = "pilotName", required = false) String pilotName,
                                     @RequestParam(name = "current", required = false) Long current,
                                     @RequestParam(name = "size", required = false) Long size) {
        //1.check page or not
        if (current == null || size == null) {

            //2.get result
            List<Pilot> pilots = pilotService.findAllPilot();

            //3.response to front
            return CommonResult.success(pilots);
        } else {
            //2.Encapsulate the pilot page
            Page<Pilot> pilotPage = new Page<>(current, size);

            //3.get page
            IPage<Pilot> pilotPages = pilotService.findAllPilotWithPage(pilotPage, pilotName);

            //4.response to front
            return CommonResult.success(pilotPages);
        }
    }

    /**
     * TODO
     * @return
     */
    @GetMapping("/pilot/find/{pilotName}")
    public CommonResult findPilotByPilotName(@PathVariable("pilotName") String pilotName) {
        return null;
    }

    @PutMapping("/pilot/update")
    public CommonResult updatePilotById(@Valid @RequestBody Pilot pilot) {

        //1.update operation
        int result = pilotService.updatePilot(pilot);

        //2.check and response to front
        if (result == 0) {
            return CommonResult.fail(100, "更新失败");
        }
        return CommonResult.success("更新成功");
    }

    /**
     * fix delete pilot -> pilotBody Scan all delete
     */

    @DeleteMapping("/pilot/delete")
    public CommonResult deletePilotById(@RequestBody Map<String, Long> pilotId) {

        //1.delete operation
        int result = pilotService.deletePilotByPilotId(pilotId.get(ConstantUtil.pilotId));

        //2.check and response to front
        if (result == 0) {
            return CommonResult.fail(100, "删除失败");
        }
        return CommonResult.success("更新成功");
    }

    @DeleteMapping("/pilot/batchDelete")
    public CommonResult deletePilotByIds(@RequestBody Map<String, List<Long>> pilotId) {

        //1.save to list
        List<Long> list = pilotId.get(ConstantUtil.pilotId);

        //2.delete operation
        int result = pilotService.batchDeletePilot(list);

        //3.check and response to front
        if (result == 0) {
            return new CommonResult(100, "删除失败");
        }
        return new CommonResult(200, "删除" + result + "条记录");
    }
}
