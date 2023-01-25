package se.sundsvall.precheck.integration.gis.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("integration.gis")
public record GISProperties (int connectTimeout, int readTimeout) {
}
