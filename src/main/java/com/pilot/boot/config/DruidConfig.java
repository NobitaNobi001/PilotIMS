package com.pilot.boot.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Druid Configuration
 *
 * @author ezuy
 * @date 21/1/21 10:05
 */
@Configuration
public class DruidConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druid() throws SQLException {
        return new DruidDataSource();
    }


    /**
     * config manager servlet
     *
     * @return
     */
    @Bean
    public ServletRegistrationBean statViewServlet() {

        //1.get servlet bean
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");

        //2.save initParams
        Map<String, String> initParams = new HashMap<>(3);

        //3.save param
        initParams.put("loginUsername", "admin");
        initParams.put("loginPassword", "admin123");
        initParams.put("allow", "127.0.0.1");

        //4.write to servlet bean
        servletRegistrationBean.setInitParameters(initParams);

        return servletRegistrationBean;
    }

    /**
     * config filter
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean webStatFilter() {

        //1.get filter bean
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();

        //2.web filter
        registrationBean.setFilter(new WebStatFilter());

        //3.init params
        Map<String, String> initParams = new HashMap<>(1);

        //4.param
        initParams.put("exclusions", "/druid/*");

        //5.write to init param
        registrationBean.setInitParameters(initParams);

        registrationBean.setUrlPatterns(Arrays.asList("/*"));

        return registrationBean;
    }

}
