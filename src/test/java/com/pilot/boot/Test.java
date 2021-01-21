package com.pilot.boot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

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


}
