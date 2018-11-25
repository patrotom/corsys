package cz.cvut.fit.corsys.bl.service;

import cz.cvut.fit.corsys.dl.entity.Notification;
import cz.cvut.fit.corsys.dl.entity.Role;
import cz.cvut.fit.corsys.dl.entity.User;

import java.util.List;

/**
 * This interface should be used for general operations on user accounts, such as authentication
 * and manipulation with them. All other operations should be performed directly on service
 * interfaces intended for respective roles. These interfaces provide such operations by calling this
 * interface in background.
 */

public interface UserService {

    /**
     * Creates a user.
     * This method is intended to create a new, non-existing user.
     *
     * @param user the user to be created.
     * @return the created user. Use this object for further operations.
     * @throws IllegalArgumentException in case that the specified user already exists.
     */
    User createUser(User user) throws IllegalArgumentException;

    /**
     * Deletes a user.
     *
     * @param user the user to be deleted.
     * @throws IllegalArgumentException in case that the specified user does not exist.
     */
    void deleteUser(User user) throws IllegalArgumentException;

    /**
     * Updates a user.
     * This method is intended to update existing users only.
     *
     * @param user the user to be updated.
     * @return the updated user. Use this object for further operations.
     * @throws IllegalArgumentException in case that the specified user does not exist.
     */
    User updateUser(User user) throws IllegalArgumentException;

    /**
     * Finds all users.
     *
     * @return list of all users.
     */
    List<User> findAllUsers();

    /**
     * Returns user for the specified id.
     *
     * @param id the id for which to find user.
     * @return the corresponding user, null if such user does not exist.
     */
    User getUser(Integer id);

    /**
     * Finds user for specific username.
     *
     * @param username username to be found.
     * @return the found user, null if specified username could not be found.
     */
    User findUserByUsername(String username);

    /**
     * Returns a role object for the given role name.
     *
     * @param role the role name.
     * @return role object, null if the specified role name does not exist.
     */
    Role findRole(String role);

    /**
     * Returns the user currently logged on.
     *
     * @return the user currently logged on, null otherwise.
     */
    User getLoggedUser();

    /**
     * Finds notifications belonging to the specified user.
     *
     * @param user the user for which to find notifications.
     * @return list of all notifications for the specified user.
     */
    List<Notification> findNotifications(User user);

}