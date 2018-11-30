package cz.cvut.fit.corsys.dl.entity;

import javax.persistence.*;
import java.time.Instant;

/**
 * Entity class notification holds information and state of the particular notification.
 * This is strictly connected to the specific user.
 *
 * @author fabosamu
 */
@Entity
public class Notification {

    /**
     * Unique identifier of the entity class.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer notificationId;

    @Column(nullable = false)
    private Instant timestamp;

    @Column(nullable = false)
    private String message;

    @Column(length = 50, nullable = false)
    private String state;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "userId")
    private User user;


    public Integer getNotificationId() {
        return notificationId;
    }

    /**
     * @param notificationId
     */
    public void setNotificationId(Integer notificationId) {
        this.notificationId = notificationId;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp
     */
    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    /**
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    public String getState() {
        return state;
    }

    /**
     * @param state
     */
    public void setState(String state) {
        this.state = state;
    }

    public User getUser() {
        return user;
    }

    /**
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
    }
}