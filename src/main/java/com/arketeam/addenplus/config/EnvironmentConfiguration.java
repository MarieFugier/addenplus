package com.arketeam.addenplus.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
@ConfigurationProperties("environment")
public class EnvironmentConfiguration {

	private String name;
	
}
