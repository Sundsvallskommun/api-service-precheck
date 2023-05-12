package se.sundsvall.precheck.integration.lmv;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static se.sundsvall.precheck.integration.lmv.configuration.LMVConfiguration.CLIENT_ID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import se.sundsvall.precheck.integration.lmv.configuration.LMVConfiguration;
import se.sundsvall.precheck.integration.lmv.model.FeatureCollection;

@FeignClient(name = CLIENT_ID, url = "${integration.lmv.url}", configuration = LMVConfiguration.class)
public interface LMVClient {

	@GetMapping(path = "/{addressId}", produces = APPLICATION_JSON_VALUE)
	FeatureCollection getAddress(@PathVariable(value = "addressId") String addressId,  @RequestParam(value = "includeData") String includeData, @RequestParam(value = "srid") String srid);
}
