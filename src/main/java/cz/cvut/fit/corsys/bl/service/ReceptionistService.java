package cz.cvut.fit.corsys.bl.service;

import cz.cvut.fit.corsys.dl.entity.Notification;
import cz.cvut.fit.corsys.dl.entity.Receptionist;

import java.util.List;

/**
 * Defines basic interface, which class have to implement, in order to communicate with data layer,
 *  should be used for general operations on user accounts, such as authentication
 *  and manipulation with them. All other operations should be performed directly on service
 *  interfaces intended for respective roles.
 */

public interface ReceptionistService {

    /**
     * Creates a receptionist.
     * This method is intended to create a new, non-existing receptionist.
     *
     * @param receptionist the receptionist to be created.
     * @return the created receptionist. Use this object for further operations.
     * @throws IllegalArgumentException in case that the specified receptionist already exists.
     */
    Receptionist createReceptionist(Receptionist receptionist) throws IllegalArgumentException;

    /**
     * Updates a receptionist.
     * This method is intended to update existing receptionists only.
     *
     * @param receptionist the receptionist to be updated.
     * @return the updated receptionist. Use this object for further operations.
     * @throws IllegalArgumentException in case that the specified receptionist does not exist.
     */
    Receptionist updateReceptionist(Receptionist receptionist) throws IllegalArgumentException;

    /**
     * Deletes a receptionist.
     *
     * @param receptionist the receptionist to be deleted.
     * @throws IllegalArgumentException in case that the specified receptionist does not exist.
     */
    void deleteReceptionist(Receptionist receptionist) throws IllegalArgumentException;

    /**
     * Returns receptionist for the specified id.
     *
     * @param id the id for which to find receptionist.
     * @return the corresponding receptionist, null if such doctor does not exist.
     */
    Receptionist getReceptionist(Integer id);

    /**
     * Finds receptionist for specific username.
     *
     * @param username username to be found.
     * @return the found receptionist, null if specified username could not be found.
     */
    Receptionist findReceptionistByUsername(String username);

    /**
     * Finds all receptionists.
     *
     * @return list of all receptionists.
     */
    List<Receptionist> findAllReceptionists();

    /**
     * Finds notifications belonging to the specified receptionist.
     *
     * @param receptionist the receptionist for which to find notifications.
     * @return list of all notifications for the specified receptionist.
     */
    List<Notification> findNotifications(Receptionist receptionist);

}
