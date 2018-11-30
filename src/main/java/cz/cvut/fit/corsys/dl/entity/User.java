package cz.cvut.fit.corsys.dl.entity;

import javax.persistence.*;

/**
 * Entity class User holds main information about each user.
 * User is defined by its id and its unique username.
 * Each User must have its own predefined Role.
 * We can access User's Role by attribute role.
 *
 * @author fabosamu
 */
@Entity
public class User {

    /**
     * Unique identifier of the entity class.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    /**
     * Some of the users may be removed in the future, we want to know about this fact.
     */
    private boolean active;

    @Column(length = 50, nullable = false, unique = true)
    private String username;

    @Column(length = 50, nullable = false)
    private String password;

    @Column(length = 50, nullable = false)
    private String email;

    @Column(length = 50, nullable = false)
    private String firstName;

    @Column(length = 50, nullable = false)
    private String lastName;

    @Column(length = 15)
    private String phone;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "roleId")
    private Role role;


    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public boolean isActive() {
        return active;
    }

    /**
     * @param active
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Role getRole() {
        return role;
    }

    /**
     * @param role
     */
    public void setRole(Role role) {
        this.role = role;
    }
}