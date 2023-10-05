package com.niit.FurnitureApp;

import com.niit.FurnitureApp.filter.FurnitureFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
public class FurnitureAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(FurnitureAppApplication.class, args);
	}
	@Bean
	public FilterRegistrationBean filterUrl()
	{
		FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(new FurnitureFilter());

		filterRegistrationBean.addUrlPatterns("/api/product/v1/add-product", "/api/product/v1/update-product/*", "/api/product/v1/delete-product/*","/api/product/v1/addToCart/*");
		return filterRegistrationBean;
	}
	@Bean
	public FilterRegistrationBean filterRegistrationBean(){
		final CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("http://localhost:4200");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**",config);
		FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}

}
