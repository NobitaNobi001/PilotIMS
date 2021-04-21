package com.pilot.boot.listener;

import com.pilot.boot.service.DeptService;
import com.pilot.boot.aspect.DeptAspect;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @author ezuy
 * @date 21/4/15 20:54
 */
@Slf4j
@Component
public class ContextInitializeListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private DeptService deptService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        deptService.findAllDept().forEach(dept -> {
            DeptAspect.dept.put(dept.getDeptId(), dept.getDeptName());
        });
        log.info(DeptAspect.dept.toString());
    }
}