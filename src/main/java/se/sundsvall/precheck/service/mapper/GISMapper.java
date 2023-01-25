package se.sundsvall.precheck.service.mapper;

import generated.se.sundsvall.gisapi.PreCheck200Response;
import se.sundsvall.precheck.api.model.PreCheckResponse;

import static java.util.Optional.ofNullable;

public class GISMapper {
	private GISMapper() {
	}

	public static PreCheckResponse toPreCheckResponse(PreCheck200Response gisPrecheckResponse) {
		if (gisPrecheckResponse == null) {
			return null;
		}
		return PreCheckResponse.create().withDeliverable(gisPrecheckResponse.getDeliverable())
			.withFutureDeliverable(ofNullable(gisPrecheckResponse.getFutureDeliverable()).orElse(false))
			.withPlannedDevelopmentDate(gisPrecheckResponse.getPlannedDevelopmentDate())
			.withMetaData(gisPrecheckResponse.getMetaData());
	}
}

