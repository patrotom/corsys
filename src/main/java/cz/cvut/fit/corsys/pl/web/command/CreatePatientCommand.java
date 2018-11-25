package cz.cvut.fit.corsys.pl.web.command;

import javax.validation.constraints.NotNull;

public class CreatePatientCommand extends AbstractCreateUserCommand {

    @NotNull
    private String birthNumber;

    @NotNull
    private String insurance;

    @NotNull
    private Integer addressId;

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

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }
}
