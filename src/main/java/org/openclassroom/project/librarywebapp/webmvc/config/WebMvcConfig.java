package org.openclassroom.project.librarywebapp.webmvc.config;

import org.openclassroom.project.librarywebapp.converters.StringToXMLGregorianCalendarConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

/**
 * Configures resource handlers, formatters and interceptors for the locale resolver.
 * */
@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * Add static css and js folders to the application's registry
     *
     * @param registry - {@link ResourceHandlerRegistry} containing the static folders.
     * */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**", "/js/**")
                .addResourceLocations("classpath:/static/css/", "classpath:/static/js/");
    }



    /**
     * Adds formatters to the application's registry
     *
     * @param registry - {@link FormatterRegistry} containing the custom converters.
     * */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToXMLGregorianCalendarConverter());
    }



    /**
     * The {@link LocaleResolver} interface has implementations that determine the current locale based on the session,
     * cookies, the Accept-Language header, or a fixed value.
     *
     * @return {@link SessionLocaleResolver} that determine the current locale.
     * */
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.US);
        return slr;
    }



    /**
     * Interceptor bean that will switch to a new locale based on the value of the lang parameter appended to a request
     *
     * @return {@link LocaleChangeInterceptor} containing the new locale.
     * */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }



    /**
     * Adds interceptors to the application's registry
     *
     * @param registry - {@link InterceptorRegistry} containing the custom interceptors.
     * */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
}
