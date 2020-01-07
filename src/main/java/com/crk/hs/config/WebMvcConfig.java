package com.crk.hs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter implements WebMvcConfigurer  {
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//
//        if (!registry.hasMappingForPattern("/static/**")) {
//            registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
//        }
//        super.addResourceHandlers(registry);
//    }
//    /**
//     * springmvc视图解析
//     * @Title: viewResolver
//     * @Description: TODO
//     * @Date 2018年8月28日 下午4:46:07
//     * @author OnlyMate
//     * @return
//     */
//    @Bean
//    public InternalResourceViewResolver viewResolver(){
//        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//        viewResolver.setPrefix("template/page");
//        viewResolver.setSuffix(".html");
//        // viewResolver.setViewClass(JstlView.class); // 这个属性通常并不需要手动配置，高版本的Spring会自动检测
//        return viewResolver;
//    }
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login.html");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        super.addViewControllers(registry);
    }
@Override
public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/**")
            .addResourceLocations("classpath:/static/");
    registry.addResourceHandler("/**")
            .addResourceLocations("classpath:/templates/");
//    registry.addResourceHandler("swagger-ui.html")
//            .addResourceLocations("classpath:/META-INF/resources/");
    registry.addResourceHandler("/webjars/**")
            .addResourceLocations("classpath:/META-INF/resources/webjars/");
}
}

