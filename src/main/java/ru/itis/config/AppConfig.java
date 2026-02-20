package ru.itis.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"ru.itis.data", "ru.itis.entity", "ru.itis.repository", "ru.itis.service"})
public class AppConfig {
}