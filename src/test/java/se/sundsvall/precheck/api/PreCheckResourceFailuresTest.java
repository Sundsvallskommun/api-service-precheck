package se.sundsvall.precheck.api;

import static org.mockito.Mockito.verifyNoInteractions;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.METHOD_NOT_ALLOWED;
import static org.springframework.http.MediaType.APPLICATION_PROBLEM_JSON_VALUE;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import se.sundsvall.precheck.Application;
import se.sundsvall.precheck.api.model.Category;
import se.sundsvall.precheck.service.PreCheckService;

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("junit")
class PreCheckResourceFailuresTest {

	@Autowired
	private WebTestClient webTestClient;
	
	@MockBean
	private PreCheckService serviceMock;
	
	@Test
	void getPreCheckByInvalidCategory() {

		// Parameter values
		final var addressId = "addressId";

		webTestClient.get().uri("/address/{addressId}/{category}", addressId, "INVALID-CATEGORY")
		.exchange()
		.expectStatus().isBadRequest()
		.expectHeader().contentType(APPLICATION_PROBLEM_JSON_VALUE)
		.expectBody()
		.jsonPath("$.title").isEqualTo("Bad Request")
		.jsonPath("$.status").isEqualTo(BAD_REQUEST.value())
		.jsonPath("$.detail").isEqualTo(
				"""
				Failed to convert value of type 'java.lang.String' to required type 'se.sundsvall.precheck.api.model.Category'; \
				nested exception is org.springframework.core.convert.ConversionFailedException: Failed to convert from type [java.lang.String] \
				to type [@io.swagger.v3.oas.annotations.Parameter @org.springframework.web.bind.annotation.PathVariable \
				se.sundsvall.precheck.api.model.Category] for value 'INVALID-CATEGORY'; nested exception is java.lang.IllegalArgumentException: \
				No enum constant se.sundsvall.precheck.api.model.Category.INVALID-CATEGORY""");

		verifyNoInteractions(serviceMock);
	}

	@Test
	void getPreCheckByBlankAddressId() {

		// Parameter values
		final var addressId = " ";

		webTestClient.get().uri("/address/{addressId}/{category}", addressId, Category.DISTRICT_HEATING)
				.exchange()
				.expectStatus().isBadRequest()
				.expectHeader().contentType(APPLICATION_PROBLEM_JSON_VALUE)
				.expectBody()
				.jsonPath("$.title").isEqualTo("Constraint Violation")
				.jsonPath("$.status").isEqualTo(BAD_REQUEST.value())
				.jsonPath("$.violations[0].field").isEqualTo("getPreCheck.addressId")
				.jsonPath("$.violations[0].message").isEqualTo("not a valid UUID");

		verifyNoInteractions(serviceMock);
	}

	@Test
	void getPreCheckByNotValidAddressId() {

		// Parameter values
		final var addressId = "not-valid";

		webTestClient.get().uri("/address/{addressId}/{category}", addressId, Category.DISTRICT_HEATING)
				.exchange()
				.expectStatus().isBadRequest()
				.expectHeader().contentType(APPLICATION_PROBLEM_JSON_VALUE)
				.expectBody()
				.jsonPath("$.title").isEqualTo("Constraint Violation")
				.jsonPath("$.status").isEqualTo(BAD_REQUEST.value())
				.jsonPath("$.violations[0].field").isEqualTo("getPreCheck.addressId")
				.jsonPath("$.violations[0].message").isEqualTo("not a valid UUID");

		verifyNoInteractions(serviceMock);
	}

	@Test
	void getPreCheckWrongMethod() {

		// Parameter values
		final var addressId = "addressId";

		webTestClient.delete().uri("/address/{addressId}/{category}", addressId, Category.ELECTRICITY)
		.exchange()
		.expectStatus().isEqualTo(METHOD_NOT_ALLOWED)
		.expectHeader().contentType(APPLICATION_PROBLEM_JSON_VALUE)
		.expectBody()
		.jsonPath("$.title").isEqualTo("Method Not Allowed")
		.jsonPath("$.status").isEqualTo(METHOD_NOT_ALLOWED.value())
		.jsonPath("$.detail").isEqualTo("Request method 'DELETE' not supported");

		verifyNoInteractions(serviceMock);
	}
}
