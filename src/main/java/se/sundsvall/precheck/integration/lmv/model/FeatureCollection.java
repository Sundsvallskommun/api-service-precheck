package se.sundsvall.precheck.integration.lmv.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "FeatureCollection model")
public class FeatureCollection {

    private List<Feature> features;

    public static FeatureCollection create() {
        return new FeatureCollection();
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

    public FeatureCollection withFeatures(List<Feature> features) {
        this.features = features;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(features);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        FeatureCollection other = (FeatureCollection) obj;
        return Objects.equals(features, other.features);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("FeatureCollection [features=").append(features).append("]");
        return builder.toString();
    }
}