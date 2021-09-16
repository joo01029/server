package com.map.map.config

import com.map.map.filter.JwtAuthenticationFilter
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.HandlerExceptionResolver

@Configuration
class JwtFilterConfig(
    private val handlerExceptionResolver: HandlerExceptionResolver
) {

    @Bean
    fun authFilter(): FilterRegistrationBean<JwtAuthenticationFilter> {
        val registrationBean: FilterRegistrationBean<JwtAuthenticationFilter> = FilterRegistrationBean()
        registrationBean.filter = JwtAuthenticationFilter(handlerExceptionResolver)
        registrationBean.addUrlPatterns("/")
        registrationBean.order = 2

        return registrationBean
    }
}