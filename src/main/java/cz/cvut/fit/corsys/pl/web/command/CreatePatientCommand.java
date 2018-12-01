package cz.cvut.fit.corsys.pl.web.command;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreatePatientCommand extends AbstractCreateUserCommand {

    @NotNull
    @Size(max = 10)
    private String birthNumber;

    @NotNull
    private String insurance;

    @NotNull
    private String city;

    @NotNull
    private String number;

    @NotNull
    private String street;

    @NotNull
    private String zipCode;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getBirthNumber() {
        return birthNumber;
    }

    public void setBirthNumber(String birthNumber) {
        this.birthNumber = birthNumber;
    }

    public String getInsurance() {
        return insurance;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }
}
