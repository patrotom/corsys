package cz.cvut.fit.corsys.bl.service;

public interface ReceptionistService {

    /**
     * Creates a receptionist.
     * This method is intended to create a new, non-existing receptionist.
     *
     * @param receptionist the receptionist to be created.
     * @return the created receptionist. Use this object for further operations.
     */
    Receptionist createReceptionist(Receptionist receptionist);

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
