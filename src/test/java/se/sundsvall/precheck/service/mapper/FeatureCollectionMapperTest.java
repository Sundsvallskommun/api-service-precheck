package se.sundsvall.precheck.service.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import se.sundsvall.precheck.integration.lmv.model.Adressplatsattribut;
import se.sundsvall.precheck.integration.lmv.model.Adressplatspunkt;
import se.sundsvall.precheck.integration.lmv.model.Feature;
import se.sundsvall.precheck.integration.lmv.model.FeatureCollection;
import se.sundsvall.precheck.integration.lmv.model.Properties;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static se.sundsvall.precheck.service.mapper.FeatureCollectionMapper.toCoordinates;

@ExtendWith(MockitoExtension.class)
class FeatureCollectionMapperTest {

	@Test
	void toCoordinatesSuccess() {
		final var northing = BigDecimal.valueOf(1.234);
		final var easting =  BigDecimal.valueOf(2.345);
		final var featureCollection = FeatureCollection.create()
			.withFeatures(List.of(Feature.create()
			.withType("Feature")
			.withProperties(Properties.create()
			.withAdressplatsattribut(Adressplatsattribut.create()
			.withAdressplatspunkt(Adressplatspunkt.create()
			.withType("Point")
			.withCoordinates(List.of(northing, easting)))))));
		final var coordinates = toCoordinates(featureCollection);

		assertThat(coordinates).isNotNull().hasSize(2);
		assertThat(coordinates.get(0)).isEqualTo(northing);
		assertThat(coordinates.get(1)).isEqualTo(easting);
	}

	@Test
	void toCoordinatesWhenNull() {

		final var coordinates = toCoordinates(null);

		assertThat(coordinates).isNotNull().isEmpty();
	}
}
