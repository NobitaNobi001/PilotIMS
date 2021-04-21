package com.pilot.boot.aspect;

import com.pilot.boot.service.DeptService;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ezuy
 * @date 21/4/15 21:03
 */
@Aspect
@Component
public class DeptAspect {

    @Autowired
    private DeptService deptService;

    public static Map<Long,String> dept = new HashMap<>();

    @After("execution(* com.pilot.boot.controller.DeptController.*Dept(..)) && within(com.pilot.boot.controller.DeptController)")
    public void getDept(){
        DeptAspect.dept.clear();
        deptService.findAllDept().forEach(deptTemp -> {
            DeptAspect.dept.put(deptTemp.getDeptId(),deptTemp.getDeptName());
        });
    }
}