package apptest;

import org.junit.jupiter.api.Test;
import se.sundsvall.dept44.test.AbstractAppTest;
import se.sundsvall.dept44.test.annotation.wiremock.WireMockAppTestSuite;
import se.sundsvall.precheck.Application;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;

@WireMockAppTestSuite(files = "classpath:/Precheck/", classes = Application.class)
class PrecheckIT extends AbstractAppTest {

	private static final String RESPONSE_FILE = "response.json";

	@Test
	void test001_doPrecheck() {

		setupCall()
			.withServicePath("/address/e776cb4f-966f-4e4f-a1ea-abb347b3a93d/DISTRICT_HEATING?referenceSystem=3006")
			.withHttpMethod(GET)
			.withExpectedResponse(RESPONSE_FILE)
			.withExpectedResponseStatus(OK)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test002_doPrecheckTimeoutLMV() {

		setupCall()
			.withServicePath("/address/e776cb4f-966f-4e4f-a1ea-abb347b3a93d/DISTRICT_HEATING?referenceSystem=3006")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(INTERNAL_SERVER_ERROR)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test003_doPrecheckTimeoutGIS() {

		setupCall()
			.withServicePath("/address/e776cb4f-966f-4e4f-a1ea-abb347b3a93d/DISTRICT_HEATING?referenceSystem=3006")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(INTERNAL_SERVER_ERROR)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}
}
