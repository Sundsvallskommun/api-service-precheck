package se.sundsvall.precheck.service;

import generated.se.sundsvall.gisapi.PreCheck200Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.zalando.problem.ThrowableProblem;
import se.sundsvall.precheck.integration.gis.GISClient;
import se.sundsvall.precheck.integration.lmv.LMVClient;
import se.sundsvall.precheck.integration.lmv.model.Adressplatsattribut;
import se.sundsvall.precheck.integration.lmv.model.Adressplatspunkt;
import se.sundsvall.precheck.integration.lmv.model.Feature;
import se.sundsvall.precheck.integration.lmv.model.FeatureCollection;
import se.sundsvall.precheck.integration.lmv.model.Properties;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.zalando.problem.Status.BAD_GATEWAY;
import static org.zalando.problem.Status.NOT_IMPLEMENTED;
import static se.sundsvall.precheck.api.model.Category.DISTRICT_HEATING;
import static se.sundsvall.precheck.api.model.Category.ELECTRICITY;

@ExtendWith(MockitoExtension.class)
class PreCheckServiceTest {

	final static String INCLUDE_DATA = "basinformation";
	@Mock
	private LMVClient lmvClientMock;

	@Mock
	private GISClient gisClientMock;

	@Mock
	private FeatureCollection featureCollectionMock;
	
	@InjectMocks
	private PreCheckService preCheckService;

	@Test
	void preCheck() {
		final var addressId = "addressId";
		final var category = DISTRICT_HEATING;
		final var referenceSystem = "referenceSystem";
		final var northing = "1.234";
		final var easting = "2.345";

		when(lmvClientMock.getAddress(addressId, INCLUDE_DATA, referenceSystem)).thenReturn(createFeatureCollectionSuccess());
		when(gisClientMock.precheck(northing, easting, category.toString(), referenceSystem)).thenReturn(new PreCheck200Response().deliverable(true).futureDeliverable(false)
				.plannedDevelopmentDate(LocalDate.now()));

		final var result = preCheckService.preCheck(addressId, category, referenceSystem);

		assertThat(result).isNotNull();
		verify(lmvClientMock).getAddress(addressId, INCLUDE_DATA, referenceSystem);
		verify(gisClientMock).precheck(northing, easting, category.toString(), referenceSystem);
	}

	@Test
	void preCheckNoReferenceSystem() {
		final var addressId = "addressId";
		final var category = DISTRICT_HEATING;
		final var northing = "1.234";
		final var easting = "2.345";
		final var defaultReferenceSystem = "3006";

		when(lmvClientMock.getAddress(addressId, INCLUDE_DATA, defaultReferenceSystem)).thenReturn(createFeatureCollectionSuccess());
		when(gisClientMock.precheck(northing, easting, category.toString(), defaultReferenceSystem)).thenReturn(new PreCheck200Response().deliverable(true).futureDeliverable(false)
				.plannedDevelopmentDate(LocalDate.now()));

		final var result = preCheckService.preCheck(addressId, category, null);

		assertThat(result).isNotNull();
		verify(lmvClientMock).getAddress(addressId, INCLUDE_DATA, defaultReferenceSystem);
		verify(gisClientMock).precheck(northing, easting, category.toString(), defaultReferenceSystem);
	}

	@Test
	void getCoordinatesNullInResponse() {
		final var addressId = "addressId";
		final var category = DISTRICT_HEATING;
		final var referenceSystem = "referenceSystem";

		when(lmvClientMock.getAddress(addressId, INCLUDE_DATA, referenceSystem)).thenReturn(null);

		final var throwableProblem = assertThrows(ThrowableProblem.class, () -> preCheckService.preCheck(addressId, category, referenceSystem));

		assertThat(throwableProblem.getMessage()).isEqualTo("Bad Gateway: Something went wrong when fetching coordinates");
		assertThat(throwableProblem.getStatus()).isEqualTo(BAD_GATEWAY);

		verify(lmvClientMock).getAddress(addressId, INCLUDE_DATA, referenceSystem);
		verifyNoInteractions(gisClientMock);
	}

	@Test
	void preCheckOnlyOneCoordinateInResponse() {
		final var addressId = "addressId";
		final var category = DISTRICT_HEATING;
		final var referenceSystem = "referenceSystem";

		when(lmvClientMock.getAddress(addressId, INCLUDE_DATA, referenceSystem)).thenReturn(createFeatureCollectionOnlyOneCoordinate());

		final var throwableProblem = assertThrows(ThrowableProblem.class, () -> preCheckService.preCheck(addressId, category, referenceSystem));

		assertThat(throwableProblem.getMessage()).isEqualTo("Bad Gateway: Something went wrong when fetching coordinates");
		assertThat(throwableProblem.getStatus()).isEqualTo(BAD_GATEWAY);

		verify(lmvClientMock).getAddress(addressId, INCLUDE_DATA, referenceSystem);
		verifyNoInteractions(gisClientMock);
	}

	@Test
	void preCheckNotSupportedCategory() {
		final var addressId = "addressId";
		final var category = ELECTRICITY;
		final var referenceSystem = "referenceSystem";

		final var throwableProblem = assertThrows(ThrowableProblem.class, () -> preCheckService.preCheck(addressId, category, referenceSystem));

		assertThat(throwableProblem.getMessage()).isEqualTo("Not Implemented: Category ELECTRICITY is not supported yet");
		assertThat(throwableProblem.getStatus()).isEqualTo(NOT_IMPLEMENTED);

		verifyNoInteractions(lmvClientMock);
		verifyNoInteractions(gisClientMock);
	}

	private FeatureCollection createFeatureCollectionSuccess() {
		return FeatureCollection.create()
			.withFeatures(List.of(Feature.create()
				.withType("Feature")
				.withProperties(Properties.create()
					.withAdressplatsattribut(Adressplatsattribut.create()
						.withAdressplatspunkt(Adressplatspunkt.create()
							.withType("Point")
							.withCoordinates(List.of(BigDecimal.valueOf(1.234), BigDecimal.valueOf(2.345))))))));
	}

	private FeatureCollection createFeatureCollectionOnlyOneCoordinate() {
		return FeatureCollection.create()
			.withFeatures(List.of(Feature.create()
				.withType("Feature")
				.withProperties(Properties.create()
					.withAdressplatsattribut(Adressplatsattribut.create()
						.withAdressplatspunkt(Adressplatspunkt.create()
							.withType("Point")
							.withCoordinates(List.of(BigDecimal.valueOf(1.234))))))));
	}
}
