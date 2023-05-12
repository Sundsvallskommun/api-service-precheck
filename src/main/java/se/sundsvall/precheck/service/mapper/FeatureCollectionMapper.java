package se.sundsvall.precheck.service.mapper;

import static java.util.Collections.emptyList;
import static java.util.Optional.ofNullable;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import se.sundsvall.precheck.integration.lmv.model.Adressplatsattribut;
import se.sundsvall.precheck.integration.lmv.model.Adressplatspunkt;
import se.sundsvall.precheck.integration.lmv.model.Feature;
import se.sundsvall.precheck.integration.lmv.model.FeatureCollection;
import se.sundsvall.precheck.integration.lmv.model.Properties;

public class FeatureCollectionMapper {

	private FeatureCollectionMapper() {}

	public static List<BigDecimal> toCoordinates(final FeatureCollection featureCollection) {
		if (featureCollection == null) {
			return emptyList();
		}
		return ofNullable(featureCollection.getFeatures()).orElse(emptyList()).stream()
			.filter(Objects::nonNull)
			.filter(feature -> "Feature".equalsIgnoreCase(feature.getType()))
			.map(Feature::getProperties)
			.map(Properties::getAdressplatsattribut)
			.filter(Objects::nonNull)
			.map(Adressplatsattribut::getAdressplatspunkt)
			.filter(Objects::nonNull)
			.filter(adressplatspunkt -> "Point".equalsIgnoreCase(adressplatspunkt.getType()))
			.map(Adressplatspunkt::getCoordinates)
			.flatMap(List::stream)
			.toList();
	}
}
