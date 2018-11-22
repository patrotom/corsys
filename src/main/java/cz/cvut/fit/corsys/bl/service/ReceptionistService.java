package cz.cvut.fit.corsys.bl.service;

public interface ReceptionistService {

    void createReceptionist(Receptionist receptionist);

    void updateReceptionist(Receptionist receptionist);

    void deleteReceptionist(Receptionist receptionist);

    List<Receptionist> findAllReceptionists();

    List<Notification> findNotifications(Receptionist receptionist);

    List<Reservation> findReservationsToConfirm(Receptionist receptionist);
}
