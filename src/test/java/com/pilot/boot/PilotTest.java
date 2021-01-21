package com.pilot.boot;

import com.pilot.boot.entity.Pilot;
import com.pilot.boot.service.PilotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * pilot test operation
 * @author ezuy
 * @date 21/1/21 13:30
 */
@Slf4j
@SpringBootTest
public class PilotTest {


    @Resource
    private PilotService pilotService;

    /**
     * pilot
     */
    @org.junit.jupiter.api.Test
    public void testInsertPilot() {
        String card = "111111111111111111";

        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            Pilot pilot = new Pilot();

            pilot.setName("pilot" + i);
            pilot.setSex(1);
            pilot.setCard(card);
            pilot.setDeptId(Long.valueOf(1));
            pilot.setPosition("教授");
            pilot.setJobTitle("军官");
            pilot.setPhone("12345678911");
            pilot.setEmail("frank.ezuy@qq.com");
            pilot.setRemark("无");
//            pilotService.insert(pilot);
        }
        long end = System.currentTimeMillis();

        log.info(String.valueOf(end - start));

    }
}
