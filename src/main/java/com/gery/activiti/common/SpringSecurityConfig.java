package com.gery.activiti.common;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 该配置类仅仅为了跳过spring-security的验证
 */
@Configuration

public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {


}
