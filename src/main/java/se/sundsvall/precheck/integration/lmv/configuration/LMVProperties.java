package se.sundsvall.precheck.integration.lmv.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("integration.lmv")
public record LMVProperties (int connectTimeout, int readTimeout) {
}
