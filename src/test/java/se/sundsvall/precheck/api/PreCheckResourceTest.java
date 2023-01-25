package se.sundsvall.precheck.api;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import java.time.LocalDate;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import se.sundsvall.precheck.Application;
import se.sundsvall.precheck.api.model.Category;
import se.sundsvall.precheck.api.model.PreCheckResponse;
import se.sundsvall.precheck.service.PreCheckService;

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("junit")
class PreCheckResourceTest {

	@MockBean
	private PreCheckService serviceMock;
	
	@Autowired
	private WebTestClient webTestClient;
	
	@Test
	void getPrecheck() {
		// Parameter values
		final var addressId = "e776cb4f-966f-4e4f-a1ea-abb347b3a93d";
		final var category = Category.DISTRICT_HEATING;
		final var referenceSysten = "referenceSystem";

		final var response = PreCheckResponse.create()
			.withDeliverable(false)
			.withFutureDeliverable(true)
			.withPlannedDevelopmentDate(LocalDate.now())
			.withMetaData(Map.of("key", "value"));

		when(serviceMock.preCheck(addressId, category, referenceSysten)).thenReturn(response);

		webTestClient.get().uri("/address/{addressId}/{category}?referenceSystem={referenceSystem}", addressId, category, referenceSysten)
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBody(PreCheckResponse.class)
			.isEqualTo(response);
		
		verify(serviceMock).preCheck(addressId, category, referenceSysten);
	}
}
