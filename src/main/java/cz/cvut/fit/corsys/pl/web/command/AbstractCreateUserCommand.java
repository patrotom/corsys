package cz.cvut.fit.corsys.pl.web.command;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public abstract class AbstractCreateUserCommand {
    @NotEmpty
    @Size(max = 20)
    private String username;
    @NotEmpty
    @Size(max = 20)
    private String password;
    @NotEmpty
    @Size(max = 20)
    private String password2;
    @NotEmpty
    @Size(max = 100)
    private String firstName;
    @NotEmpty
    @Size(max = 100)
    private String lastName;
    @NotEmpty
    @Email
    @Size(max = 100)
    private String email;
    @Size(max = 30)
    private String phone;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
