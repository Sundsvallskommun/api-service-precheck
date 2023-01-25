package se.sundsvall.precheck.integration.lmv.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Feature model")
public class Feature {
    private String type;

    private Properties properties;

    public static Feature create() {return new Feature();}

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Feature withType(String type) {
        this.type = type;
        return this;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public Feature withProperties(Properties properties) {
        this.properties = properties;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, properties);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Feature other = (Feature) obj;
        return Objects.equals(type, other.type) && Objects.equals(properties, other.properties);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Feature [type=").append(type).append(" properties=").append(properties).append("]");
        return builder.toString();
    }
}
