package se.sundsvall.precheck.integration.lmv.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Adressplatsattribut model")
public class Adressplatsattribut {

    private String type;
    private Adressplatspunkt adressplatspunkt;

    public static Adressplatsattribut create() {return new Adressplatsattribut();}

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Adressplatsattribut withType(String type) {
        this.type = type;
        return this;
    }

    public Adressplatspunkt getAdressplatspunkt() {
        return adressplatspunkt;
    }

    public void setAdressplatspunkt(Adressplatspunkt adressplatspunkt) {
        this.adressplatspunkt = adressplatspunkt;
    }

    public Adressplatsattribut withAdressplatspunkt(Adressplatspunkt adressplatspunkt) {
        this.adressplatspunkt = adressplatspunkt;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, adressplatspunkt);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Adressplatsattribut other = (Adressplatsattribut) obj;
        return Objects.equals(type, other.type) && Objects.equals(adressplatspunkt, other.adressplatspunkt);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Adressplatsattribut [type=").append(type).append(" adressplatspunkt=").append(adressplatspunkt).append("]");
        return builder.toString();
    }
}
