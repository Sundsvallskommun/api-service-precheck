package se.sundsvall.precheck.service;

import static java.lang.String.format;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import se.sundsvall.precheck.api.model.Category;
import se.sundsvall.precheck.api.model.PreCheckResponse;
import se.sundsvall.precheck.integration.gis.GISClient;
import se.sundsvall.precheck.integration.lmv.LMVClient;
import se.sundsvall.precheck.service.mapper.FeatureCollectionMapper;
import se.sundsvall.precheck.service.mapper.GISMapper;

@Service
public class PreCheckService {

	private static final String DEFAULT_REFERENCE_SYSTEM = "3006";
	private static final String INCLUDE_DATA = "basinformation";
	private static final String ERROR_NOT_IMPLEMENTED_FOR_CATEGORY = "Category %s is not supported yet";
	private static final String ERROR_GET_COORDINATES = "Something went wrong when fetching coordinates";
	private static final int NUMBER_OF_COORDINATES = 2;

	private final LMVClient lmvClient;
	private final GISClient gisClient;

	public PreCheckService(LMVClient lmvClient, GISClient gisClient) {
		this.lmvClient = lmvClient;
		this.gisClient = gisClient;
	}

	public PreCheckResponse preCheck(final String addressId, final Category category, String referenceSystem) {

		if (StringUtils.isEmpty(referenceSystem)) {
			referenceSystem = DEFAULT_REFERENCE_SYSTEM;
		}

		final var coordinates = getCoordinates(addressId, category, referenceSystem);

		return performPreCheck(coordinates, category, referenceSystem);
	}

	private List<BigDecimal> getCoordinates(final String addressId, final Category category, final String referenceSystem) {

		if (!isCategoryValid(category)) {
			throw Problem.valueOf(Status.NOT_IMPLEMENTED, format(ERROR_NOT_IMPLEMENTED_FOR_CATEGORY, category));
		}

		return FeatureCollectionMapper.toCoordinates(lmvClient.getAddress(addressId, INCLUDE_DATA, referenceSystem));
	}

	private PreCheckResponse performPreCheck(final List<BigDecimal> coordinates, final Category category, final String referenceSystem) {
		if ((coordinates == null) || (coordinates.size() != NUMBER_OF_COORDINATES)) {
			throw Problem.valueOf(Status.BAD_GATEWAY, ERROR_GET_COORDINATES);
		}
		return GISMapper.toPreCheckResponse(gisClient.precheck(coordinates.get(0).toString(), coordinates.get(1).toString(), category.toString(),
			referenceSystem));
	}

	private boolean isCategoryValid(final Category category) {
		return Category.DISTRICT_HEATING.equals(category);
	}
}
