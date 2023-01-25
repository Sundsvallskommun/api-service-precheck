package se.sundsvall.precheck.integration.gis;

import generated.se.sundsvall.gisapi.PreCheck200Response;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.sundsvall.precheck.integration.gis.configuration.GISConfiguration;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static se.sundsvall.precheck.integration.gis.configuration.GISConfiguration.CLIENT_REGISTRATION_ID;


@FeignClient(name = CLIENT_REGISTRATION_ID, url = "${integration.gis.url}", configuration = GISConfiguration.class)
@CircuitBreaker(name = CLIENT_REGISTRATION_ID)
public interface GISClient {

	@GetMapping(path = "/precheck", produces = APPLICATION_JSON_VALUE)
	PreCheck200Response precheck(@RequestParam(value = "northing") String northing, @RequestParam(value = "easting") String easting,
			@RequestParam(value = "category") String category, @RequestParam(value = "crs") String crs);
}
