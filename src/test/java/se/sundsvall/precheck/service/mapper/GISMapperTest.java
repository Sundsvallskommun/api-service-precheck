package se.sundsvall.precheck.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static se.sundsvall.precheck.service.mapper.GISMapper.toPreCheckResponse;

import java.time.LocalDate;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import generated.se.sundsvall.gisapi.PreCheck200Response;

@ExtendWith(MockitoExtension.class)
class GISMapperTest {

	@Test
	void toPreCheckSuccess() {
		final var plannedDevelopmentDate = LocalDate.now();
		final var gisPrecheckResponse = new PreCheck200Response().deliverable(true)
			.futureDeliverable(false)
			.plannedDevelopmentDate(plannedDevelopmentDate)
			.metaData(Map.of("type", "DISTRICT_HEATING"));

		final var preCheckResponse = toPreCheckResponse(gisPrecheckResponse);

		assertThat(preCheckResponse.isDeliverable()).isTrue();
		assertThat(preCheckResponse.isFutureDeliverable()).isFalse();
		assertThat(preCheckResponse.getPlannedDevelopmentDate()).isEqualTo(plannedDevelopmentDate);
	}

	@Test
	void toPreCheckWhenNull() {

		final var preCheckResponse = toPreCheckResponse(null);

		assertThat(preCheckResponse).isNull();
	}
}
