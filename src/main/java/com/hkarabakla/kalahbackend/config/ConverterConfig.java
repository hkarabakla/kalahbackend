package com.hkarabakla.kalahbackend.config;

import com.hkarabakla.kalahbackend.controller.converter.GameToGameResourceConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.core.convert.support.GenericConversionService;

@Configuration
public class ConverterConfig {

    private final GameToGameResourceConverter gameToGameResourceConverter;


    public ConverterConfig(GameToGameResourceConverter gameToGameResourceConverter) {
        this.gameToGameResourceConverter = gameToGameResourceConverter;
    }

    @Bean
    @Primary
    public ConfigurableConversionService gameConversionService() {
        ConfigurableConversionService conversionService = new GenericConversionService();
        conversionService.addConverter(gameToGameResourceConverter);
        return conversionService;
    }
}
