package com.pilot.boot;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ezuy
 * @date 21/1/19 11:50
 */
@SpringBootTest
@Slf4j
public class Test {

    @Autowired
    DataSource dataSource;

    /**
     * test connection
     * @throws SQLException
     */
    @org.junit.jupiter.api.Test
    public void testConnection() throws SQLException {
        Connection connection = dataSource.getConnection();

        System.out.println(connection);
    }


    @org.junit.jupiter.api.Test
    public void testDate() throws ParseException {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        log.info(sdf.parse(sdf.format(d)).toString());
        System.out.println(sdf.parse(sdf.format(d)) instanceof Date);
    }

}
