package se.sundsvall.precheck.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;

import static io.swagger.v3.oas.annotations.media.Schema.AccessMode.READ_ONLY;

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

	public void setDeliverable(boolean deliverable) {
		this.deliverable = deliverable;
	}

	public PreCheckResponse withDeliverable(boolean deliverable) {
		this.deliverable = deliverable;
		return this;
	}

	public boolean isFutureDeliverable() {
		return futureDeliverable;
	}

	public void setFutureDeliverable(boolean futureDeliverable) {
		this.futureDeliverable = futureDeliverable;
	}

	public PreCheckResponse withFutureDeliverable(boolean futureDeliverable) {
		this.futureDeliverable = futureDeliverable;
		return this;
	}

	public LocalDate getPlannedDevelopmentDate() {
		return plannedDevelopmentDate;
	}

	public void setPlannedDevelopmentDate(LocalDate plannedDevelopmentDate) {
		this.plannedDevelopmentDate = plannedDevelopmentDate;
	}

	public PreCheckResponse withPlannedDevelopmentDate(LocalDate plannedDevelopmentDate) {
		this.plannedDevelopmentDate = plannedDevelopmentDate;
		return this;
	}

	public Map<String, String> getMetaData() {
		return metaData;
	}

	public void setMetaData(Map<String, String> metaData) {
		this.metaData = metaData;
	}

	public PreCheckResponse withMetaData(Map<String, String> metaData) {
		this.metaData = metaData;
		return this;
	}

	@Override
	public int hashCode() { return Objects.hash(deliverable, futureDeliverable, plannedDevelopmentDate, metaData); }

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PreCheckResponse other = (PreCheckResponse) obj;
		return Objects.equals(deliverable, other.deliverable) &&
				Objects.equals(futureDeliverable, other.futureDeliverable) &&
				Objects.equals(plannedDevelopmentDate, other.plannedDevelopmentDate) &&
				Objects.equals(metaData, other.metaData);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PreCheckResponse [deliverable=").append(deliverable).append(", futureDeliverable=").append(futureDeliverable).append(", plannedDevelopmentDate=").append(plannedDevelopmentDate)
				.append(", metaData=").append(metaData).append("]");
		return builder.toString();
	}
}