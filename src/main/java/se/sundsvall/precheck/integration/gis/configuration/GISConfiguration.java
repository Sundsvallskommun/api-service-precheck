package se.sundsvall.precheck.integration.gis.configuration;

import org.springframework.cloud.openfeign.FeignBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import se.sundsvall.dept44.configuration.feign.FeignConfiguration;
import se.sundsvall.dept44.configuration.feign.FeignMultiCustomizer;
import se.sundsvall.dept44.configuration.feign.decoder.ProblemErrorDecoder;

@Import(FeignConfiguration.class)
public class GISConfiguration {

	public static final String CLIENT_REGISTRATION_ID = "gis";

	@Bean
	FeignBuilderCustomizer feignBuilderCustomizer(ClientRegistrationRepository clientRepository, GISProperties gisProperties) {
		return FeignMultiCustomizer.create()
				.withErrorDecoder(new ProblemErrorDecoder(CLIENT_REGISTRATION_ID))
				.withRequestTimeoutsInSeconds(gisProperties.connectTimeout(), gisProperties.readTimeout())
				.withRetryableOAuth2InterceptorForClientRegistration(clientRepository.findByRegistrationId(CLIENT_REGISTRATION_ID))
				.composeCustomizersToOne();
	}
}
