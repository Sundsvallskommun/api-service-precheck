package se.sundsvall.precheck.api;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_PROBLEM_JSON_VALUE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zalando.problem.Problem;
import org.zalando.problem.violations.ConstraintViolationProblem;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import se.sundsvall.dept44.common.validators.annotation.ValidUuid;
import se.sundsvall.precheck.api.model.Category;
import se.sundsvall.precheck.api.model.PreCheckResponse;
import se.sundsvall.precheck.service.PreCheckService;

@RestController
@Validated
@RequestMapping("/address")
@Tag(name = "Address", description = "Pre check operations for addresses")
public class PreCheckResource {

	@Autowired
	private PreCheckService service;

	@GetMapping(value = "/{addressId}/{category}", produces = {APPLICATION_JSON_VALUE, APPLICATION_PROBLEM_JSON_VALUE})
	@Operation(summary = "Check conditions for delivering services at an address")
	@ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = PreCheckResponse.class)))
	@ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(oneOf = {Problem.class, ConstraintViolationProblem.class})))
	@ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
	@ApiResponse(responseCode = "500", description = "Internal Server error", content = @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
	@ApiResponse(responseCode = "502", description = "Bad Gateway", content = @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
	public ResponseEntity<PreCheckResponse> getPreCheck(
		@Parameter(name = "addressId", description = "Id of address", example = "2d4f6b8d-f2b4-6f8b-d2f4-6d8f0b2d4f6b") @ValidUuid @PathVariable(name = "addressId") String addressId,
		@Parameter(name = "category", description = "Category", example = "ELECTRICITY") @PathVariable(name = "category") Category category,
		@Parameter(name = "referenceSystem", description = "Reference system of geometric (default 3006)", example = "3006") @RequestParam(name = "referenceSystem", required = false) String referenceSystem) {

		return ResponseEntity.ok(service.preCheck(addressId, category, referenceSystem));
	}
}
