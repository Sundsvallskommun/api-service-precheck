package se.sundsvall.precheck.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.APPLICATION_PROBLEM_JSON_VALUE;
import static org.zalando.problem.Status.BAD_REQUEST;
import static org.zalando.problem.Status.METHOD_NOT_ALLOWED;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.zalando.problem.Problem;
import org.zalando.problem.violations.ConstraintViolationProblem;
import org.zalando.problem.violations.Violation;

import se.sundsvall.precheck.Application;
import se.sundsvall.precheck.api.model.Category;
import se.sundsvall.precheck.service.PreCheckService;

@SpringBootTest(classes = Application.class, webEnvironment = RANDOM_PORT)
@ActiveProfiles("junit")
class PreCheckResourceFailuresTest {

	@Autowired
	private WebTestClient webTestClient;

	@MockBean
	private PreCheckService serviceMock;

	@Test
	void getPreCheckByInvalidCategory() {

		// Arrange
		final var addressId = "addressId";

		// Act
		final var response = webTestClient.get().uri("/address/{addressId}/{category}", addressId, "INVALID-CATEGORY")
			.exchange()
			.expectStatus().isBadRequest()
			.expectHeader().contentType(APPLICATION_PROBLEM_JSON_VALUE)
			.expectBody(Problem.class)
			.returnResult()
			.getResponseBody();

		// Assert
		assertThat(response).isNotNull();
		assertThat(response.getTitle()).isEqualTo(BAD_REQUEST.getReasonPhrase());
		assertThat(response.getStatus()).isEqualTo(BAD_REQUEST);
		assertThat(response.getDetail()).isEqualTo(
			"Failed to convert value of type 'java.lang.String' to required type 'se.sundsvall.precheck.api.model.Category'; Failed to convert from type [java.lang.String] to type [@io.swagger.v3.oas.annotations.Parameter @org.springframework.web.bind.annotation.PathVariable se.sundsvall.precheck.api.model.Category] for value [INVALID-CATEGORY]");

		verifyNoInteractions(serviceMock);
	}

	@Test
	void getPreCheckByBlankAddressId() {

		// Arrange
		final var addressId = " ";

		// Act
		final var response = webTestClient.get().uri("/address/{addressId}/{category}", addressId, Category.DISTRICT_HEATING)
			.exchange()
			.expectStatus().isBadRequest()
			.expectHeader().contentType(APPLICATION_PROBLEM_JSON_VALUE)
			.expectBody(ConstraintViolationProblem.class)
			.returnResult()
			.getResponseBody();

		// Assert
		assertThat(response.getTitle()).isEqualTo("Constraint Violation");
		assertThat(response.getStatus()).isEqualTo(BAD_REQUEST);
		assertThat(response.getViolations())
			.extracting(Violation::getField, Violation::getMessage)
			.containsExactly(tuple("getPreCheck.addressId", "not a valid UUID"));

		verifyNoInteractions(serviceMock);
	}

	@Test
	void getPreCheckByNotValidAddressId() {

		// Arrange
		final var addressId = "not-valid";

		// Act
		final var response = webTestClient.get().uri("/address/{addressId}/{category}", addressId, Category.DISTRICT_HEATING)
			.exchange()
			.expectStatus().isBadRequest()
			.expectHeader().contentType(APPLICATION_PROBLEM_JSON_VALUE)
			.expectBody(ConstraintViolationProblem.class)
			.returnResult()
			.getResponseBody();

		// Assert
		assertThat(response.getTitle()).isEqualTo("Constraint Violation");
		assertThat(response.getStatus()).isEqualTo(BAD_REQUEST);
		assertThat(response.getViolations())
			.extracting(Violation::getField, Violation::getMessage)
			.containsExactly(tuple("getPreCheck.addressId", "not a valid UUID"));

		verifyNoInteractions(serviceMock);
	}

	@Test
	void getPreCheckWrongMethod() {

		// Arrange
		final var addressId = "addressId";

		// Act
		final var response = webTestClient.delete().uri("/address/{addressId}/{category}", addressId, Category.ELECTRICITY)
			.exchange()
			.expectStatus().isEqualTo(METHOD_NOT_ALLOWED.getStatusCode())
			.expectHeader().contentType(APPLICATION_PROBLEM_JSON_VALUE)
			.expectBody(Problem.class)
			.returnResult()
			.getResponseBody();

		// Assert
		assertThat(response).isNotNull();
		assertThat(response.getTitle()).isEqualTo(METHOD_NOT_ALLOWED.getReasonPhrase());
		assertThat(response.getStatus()).isEqualTo(METHOD_NOT_ALLOWED);
		assertThat(response.getDetail()).isEqualTo("Request method 'DELETE' is not supported");

		verifyNoInteractions(serviceMock);
	}
}
