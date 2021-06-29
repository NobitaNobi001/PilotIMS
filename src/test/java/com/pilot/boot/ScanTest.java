package com.pilot.boot;

import com.pilot.boot.service.ScanService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * scan test operation
 * @author ezuy
 * @date 21/1/21 13:31
 */
@Slf4j
@SpringBootTest
public class ScanTest {

    @Resource
    private ScanService scanService;

//    @Test
    public void testGetScanWithPilotId(){

        Long pilotId = 8l;
        System.out.println(scanService.findScanByPilotId(pilotId));
    }

}
