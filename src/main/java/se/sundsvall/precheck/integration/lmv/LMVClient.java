package se.sundsvall.precheck.integration.lmv;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import se.sundsvall.precheck.integration.lmv.configuration.LMVConfiguration;
import se.sundsvall.precheck.integration.lmv.model.FeatureCollection;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static se.sundsvall.precheck.integration.lmv.configuration.LMVConfiguration.CLIENT_REGISTRATION_ID;

@FeignClient(name = CLIENT_REGISTRATION_ID, url = "${integration.lmv.url}", configuration = LMVConfiguration.class)
@CircuitBreaker(name = CLIENT_REGISTRATION_ID)
public interface LMVClient {

	@GetMapping(path = "/{addressId}", produces = APPLICATION_JSON_VALUE)
	FeatureCollection getAddress(@PathVariable(value = "addressId") String addressId,  @RequestParam(value = "includeData") String includeData, @RequestParam(value = "srid") String srid);
}
