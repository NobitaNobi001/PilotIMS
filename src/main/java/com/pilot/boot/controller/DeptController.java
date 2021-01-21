package com.pilot.boot.controller;

import com.pilot.boot.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * dept controller
 * @author ezuy
 * @date 20/12/22 15:50
 */
@RestController("/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;
}
