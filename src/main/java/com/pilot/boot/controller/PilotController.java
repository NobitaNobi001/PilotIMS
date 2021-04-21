package com.pilot.boot.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pilot.boot.entity.Pilot;
import com.pilot.boot.exception.Assert;
import com.pilot.boot.exception.MyException;
import com.pilot.boot.listener.PilotListener;
import com.pilot.boot.service.PilotService;
import com.pilot.boot.utils.CheckFileFormat;
import com.pilot.boot.utils.CommonResult;
import com.pilot.boot.utils.ConstantUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.InputStream;
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
public class PilotController {

    @Autowired
    private PilotService pilotService;

    @PostMapping("/pilot/add")
    public CommonResult addPilot(@Valid @RequestBody Pilot pilot) {

        //1.get result
        int result = pilotService.addPilot(pilot);
        //2.check result and response
        if (result == 0) {
            return CommonResult.fail(100, "添加失败");
        }
        return CommonResult.success("添加成功", "");
    }

    @PostMapping("/pilot/add/excel")
    public CommonResult addPilotsByExcel(@RequestParam("file") MultipartFile file) {

        //1.check file
        CheckFileFormat.checkExcelFile(file);

        InputStream inputStream;

        try {

            inputStream = file.getInputStream();

            pilotService.importPilot(inputStream);

            return CommonResult.success("导入成功", "");
        } catch (MyException e) {
            throw new MyException(CommonResult.fail(e.getCode(), e.getMessage()));
        } catch (Exception e) {
            throw new MyException(CommonResult.fail(100, "文件上传错误"));
        }finally {
            PilotListener.getPilots().clear();
        }
    }

    @PostMapping("/pilot/check")
    public CommonResult checkPilotExist(@RequestBody Map<String, String> nameAndCard) {

        //1.获得飞行员姓名和身份证号并校验
        String pilotName = nameAndCard.get(ConstantUtil.pilotId.toString());
        Assert.validName(pilotName, CommonResult.fail(100, "飞行员姓名格式不正确"));
        String card = nameAndCard.get(ConstantUtil.card.toString());
        Assert.validCard(card, CommonResult.fail(100, "飞行员身份证号不正确"));

        boolean flag = pilotService.checkPilotExist(pilotName, card);

        if (!flag) {
            return CommonResult.success("此飞行员信息可添加");
        }
        return CommonResult.fail(100, "飞行员已存在");
    }

    @GetMapping("/pilot/get/{pilotId}")
    public CommonResult findPilotById(@PathVariable("pilotId") Long pilotId) {

        //1.get result
        Pilot pilot = pilotService.findPilotById(pilotId);
        //2.check and response
        Assert.notNull(pilot, CommonResult.fail(100, "不存在id为" + pilotId + "的飞行员"));
        return CommonResult.success("查找成功", pilot);
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
            return CommonResult.success("查询成功", pilots);
        } else {
            //2.Encapsulate the pilot page
            Page<Pilot> pilotPage = new Page<>(current, size);
            //3.get page
            IPage<Pilot> pilotPages = pilotService.findAllPilotWithPage(pilotPage, pilotName);
            //4.response to front
            return CommonResult.success("查询成功", pilotPages);
        }
    }

    @PutMapping("/pilot/update")
    public CommonResult updatePilotById(@Valid @RequestBody Pilot pilot) {

        //1.update operation
        int result = pilotService.updatePilot(pilot);
        //2.check and response to front
        if (result == 0) {
            return CommonResult.fail(100, "更新失败");
        }
        return CommonResult.success("更新成功", "");
    }

    @DeleteMapping("/pilot/delete")
    public CommonResult deletePilotById(@RequestBody Map<String, Long> pilotId) {

        //1.delete operation
        int result = pilotService.deletePilotByPilotId(pilotId.get(ConstantUtil.pilotId.toString()));

        //2.check and response to front
        if (result == 0) {
            return CommonResult.fail(100, "删除失败");
        }
        return CommonResult.success("删除成功", "");
    }

    @DeleteMapping("/pilot/batchDelete")
    public CommonResult deletePilotByIds(@RequestBody Map<String, List<Long>> pilotId) {

        //1.save to list
        List<Long> list = pilotId.get(ConstantUtil.pilotId.toString());
        if (list.size() == 0 || list.contains(null)) {
            return CommonResult.fail(100, "请选择需要删除的飞行员");
        }
        //2.delete operation
        int result = pilotService.batchDeletePilot(list);
        //3.check and response to front
        if (result == 0) {
            return CommonResult.fail(100, "删除失败");
        }
        return CommonResult.success("删除成功", "");
    }
}
