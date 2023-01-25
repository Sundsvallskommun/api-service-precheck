package se.sundsvall.precheck.service.mapper;

import se.sundsvall.precheck.integration.lmv.model.Adressplatsattribut;
import se.sundsvall.precheck.integration.lmv.model.Adressplatspunkt;
import se.sundsvall.precheck.integration.lmv.model.Feature;
import se.sundsvall.precheck.integration.lmv.model.FeatureCollection;
import se.sundsvall.precheck.integration.lmv.model.Properties;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import static java.util.Collections.emptyList;
import static java.util.Optional.ofNullable;

public class FeatureCollectionMapper {
	private FeatureCollectionMapper() {}
	

	public static List<BigDecimal> toCoordinates(FeatureCollection featureCollection) {
		if (featureCollection == null) {
			return emptyList();
		}
		return ofNullable(featureCollection.getFeatures()).orElse(emptyList()).stream()
			.filter(Objects::nonNull)
			.filter(feature -> ofNullable(feature.getType()).orElse("").equalsIgnoreCase("Feature"))
			.map(Feature::getProperties)
			.map(Properties::getAdressplatsattribut)
			.filter(Objects::nonNull)
			.map(Adressplatsattribut::getAdressplatspunkt)
			.filter(Objects::nonNull)
			.filter(adressplatspunkt -> ofNullable(adressplatspunkt.getType()).orElse("").equalsIgnoreCase("Point"))
			.map(Adressplatspunkt::getCoordinates)
			.flatMap(List::stream)
			.toList();
	}
}
