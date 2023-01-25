package se.sundsvall.precheck.api.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Category", enumAsRef = true)
public enum Category {
	COMMUNICATION,
	DISTRICT_COOLING,
	DISTRICT_HEATING,
	ELECTRICITY,
	ELECTRICITY_TRADE,
	WASTE_MANAGEMENT,
	WATER
}
