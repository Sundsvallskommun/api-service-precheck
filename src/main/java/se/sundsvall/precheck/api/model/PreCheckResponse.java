package se.sundsvall.precheck.api.model;

import static io.swagger.v3.oas.annotations.media.Schema.AccessMode.READ_ONLY;

import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.v3.oas.annotations.media.Schema;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "PreCheckResponse model")
public class PreCheckResponse {

	@Schema(description = "Shows if installation of service is possible or not", example = "true", accessMode = READ_ONLY)
	private boolean deliverable;

	@Schema(description = "Shows if installation of service is possible in the future or not", example = "true", accessMode = READ_ONLY)
	private boolean futureDeliverable;

	@Schema(description = "Planned development date ", example = "2024-01-01", accessMode = READ_ONLY)
	private LocalDate plannedDevelopmentDate;

	@Schema(description = "Map with name-value pairs", example = "true", accessMode = READ_ONLY)
	private Map<String, String> metaData;

	public static PreCheckResponse create() {
		return new PreCheckResponse();
	}

	public boolean isDeliverable() {
		return deliverable;
	}

	public void setDeliverable(final boolean deliverable) {
		this.deliverable = deliverable;
	}

	public PreCheckResponse withDeliverable(final boolean deliverable) {
		this.deliverable = deliverable;
		return this;
	}

	public boolean isFutureDeliverable() {
		return futureDeliverable;
	}

	public void setFutureDeliverable(final boolean futureDeliverable) {
		this.futureDeliverable = futureDeliverable;
	}

	public PreCheckResponse withFutureDeliverable(final boolean futureDeliverable) {
		this.futureDeliverable = futureDeliverable;
		return this;
	}

	public LocalDate getPlannedDevelopmentDate() {
		return plannedDevelopmentDate;
	}

	public void setPlannedDevelopmentDate(final LocalDate plannedDevelopmentDate) {
		this.plannedDevelopmentDate = plannedDevelopmentDate;
	}

	public PreCheckResponse withPlannedDevelopmentDate(final LocalDate plannedDevelopmentDate) {
		this.plannedDevelopmentDate = plannedDevelopmentDate;
		return this;
	}

	public Map<String, String> getMetaData() {
		return metaData;
	}

	public void setMetaData(final Map<String, String> metaData) {
		this.metaData = metaData;
	}

	public PreCheckResponse withMetaData(final Map<String, String> metaData) {
		this.metaData = metaData;
		return this;
	}

	@Override
	public int hashCode() {
		return Objects.hash(deliverable, futureDeliverable, metaData, plannedDevelopmentDate);
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof PreCheckResponse other)) {
			return false;
		}
		return (deliverable == other.deliverable) && (futureDeliverable == other.futureDeliverable) && Objects.equals(metaData, other.metaData) && Objects.equals(plannedDevelopmentDate, other.plannedDevelopmentDate);
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("PreCheckResponse [deliverable=").append(deliverable).append(", futureDeliverable=").append(futureDeliverable).append(", plannedDevelopmentDate=").append(plannedDevelopmentDate).append(", metaData=").append(metaData).append("]");
		return builder.toString();
	}
}
