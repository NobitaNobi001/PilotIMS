package com.pilot.boot.config;

import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author ezuy
 * @date 20/12/22 16:14
 */

@EnableTransactionManagement
@MapperScan("com.pilot.boot.dao")
@Configuration
public class MyBatisPlusConfiguration {

    /**
     * start mybatis-plus page plugin
     *
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }


//    @Bean
//    public ConfigurationCustomizer configurationCustomizer() {
//
//        return configuration ->{
//
//            //open a_b to aB
//            configuration.setMapUnderscoreToCamelCase(true);
//        };
//    }

}
