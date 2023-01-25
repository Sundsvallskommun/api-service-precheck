package se.sundsvall.precheck.integration.lmv.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Properties model")
public class Properties {

    private String objektstatus;
    private Adressplatsattribut adressplatsattribut;

    public static Properties create() {return new Properties();}

    public String getObjektstatus() {
        return objektstatus;
    }

    public void setObjektstatus(String objektstatus) {
        this.objektstatus = objektstatus;
    }

    public Properties withObjektstatus(String objektstatus) {
        this.objektstatus = objektstatus;
        return this;
    }

    public Adressplatsattribut getAdressplatsattribut() {
        return adressplatsattribut;
    }

    public void setAdressplatsattribut(Adressplatsattribut adressplatsattribut) {
        this.adressplatsattribut = adressplatsattribut;
    }

    public Properties withAdressplatsattribut(Adressplatsattribut adressplatsattribut) {
        this.adressplatsattribut = adressplatsattribut;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(objektstatus, adressplatsattribut);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Properties other = (Properties) obj;
        return Objects.equals(objektstatus, other.objektstatus) && Objects.equals(adressplatsattribut, other.adressplatsattribut);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Properties [objektstatus=").append(objektstatus).append(" adressplatsattribut=").append(adressplatsattribut).append("]");
        return builder.toString();
    }
}


