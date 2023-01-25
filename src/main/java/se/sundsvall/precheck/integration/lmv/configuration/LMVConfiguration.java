package se.sundsvall.precheck.integration.lmv.configuration;

import org.springframework.cloud.openfeign.FeignBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import se.sundsvall.dept44.configuration.feign.FeignConfiguration;
import se.sundsvall.dept44.configuration.feign.FeignMultiCustomizer;
import se.sundsvall.dept44.configuration.feign.decoder.ProblemErrorDecoder;

@Import(FeignConfiguration.class)
public class LMVConfiguration {

	public static final String CLIENT_REGISTRATION_ID = "lmv";

	@Bean
	FeignBuilderCustomizer feignBuilderCustomizer(ClientRegistrationRepository clientRepository, LMVProperties lmvProperties) {
		return FeignMultiCustomizer.create()
				.withErrorDecoder(new ProblemErrorDecoder(CLIENT_REGISTRATION_ID))
				.withRequestTimeoutsInSeconds(lmvProperties.connectTimeout(), lmvProperties.readTimeout())
				.withRetryableOAuth2InterceptorForClientRegistration(clientRepository.findByRegistrationId(CLIENT_REGISTRATION_ID))
				.composeCustomizersToOne();
	}
}
