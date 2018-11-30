package cz.cvut.fit.corsys.dl.dao;

import cz.cvut.fit.corsys.dl.entity.Notification;
import cz.cvut.fit.corsys.dl.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationDao extends JpaRepository<Notification, Integer> {

    /**
     * @param id
     * @return Notification
     */
    Notification findNotificationByNotificationId(Integer id);

    /**
     * Finds all notifications for given user
     *
     * @param user
     * @return List of Notifications
     */
    List<Notification> findNotificationsByUser(User user);

}
