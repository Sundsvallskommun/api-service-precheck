package se.sundsvall.precheck.integration.lmv.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Adressplatspunkt model")
public class Adressplatspunkt {

    private String type;
    private List<BigDecimal> coordinates;

    public static Adressplatspunkt create() {return new Adressplatspunkt();}

    public List<BigDecimal> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<BigDecimal> coordinates) {
        this.coordinates = coordinates;
    }

    public Adressplatspunkt withCoordinates(List<BigDecimal> coordinates) {
        this.coordinates = coordinates;
        return this;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Adressplatspunkt withType(String type) {
        this.type = type;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, coordinates);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Adressplatspunkt other = (Adressplatspunkt) obj;
        return Objects.equals(type, other.type) && Objects.equals(coordinates, other.coordinates);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Adressplatspunkt [type=").append(type).append(" coordinates=").append(coordinates).append("]");
        return builder.toString();
    }
}
