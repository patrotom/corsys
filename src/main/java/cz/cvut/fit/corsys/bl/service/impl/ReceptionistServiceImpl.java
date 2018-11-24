package cz.cvut.fit.corsys.bl.service.impl;

import cz.cvut.fit.corsys.bl.service.ReceptionistService;
import cz.cvut.fit.corsys.bl.service.UserService;
import cz.cvut.fit.corsys.dl.dao.ReceptionistDao;
import cz.cvut.fit.corsys.dl.entity.Notification;
import cz.cvut.fit.corsys.dl.entity.Receptionist;
import cz.cvut.fit.corsys.dl.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ReceptionistServiceImpl implements ReceptionistService {
    
    @Autowired
    ReceptionistDao receptionistDao;
    
    @Autowired
    UserService userService;
    
    @Override
    public Receptionist createReceptionist(Receptionist receptionist) {
        return receptionistDao.save(receptionist);
    }

    @Override
    public Receptionist updateReceptionist(Receptionist receptionist) throws IllegalArgumentException {
        if (receptionistDao.findReceptionistByReceptionistId(receptionist.getReceptionistId()) == null) {
            throw new IllegalArgumentException();
        }
        return receptionistDao.save(receptionist);
    }

    @Override
    public void deleteReceptionist(Receptionist receptionist) throws IllegalArgumentException {
        if (receptionistDao.findReceptionistByReceptionistId(receptionist.getReceptionistId()) == null) {
            throw new IllegalArgumentException();
        }
        receptionistDao.delete(receptionist);
    }

    @Override
    public Receptionist getReceptionist(Integer id) {
        return receptionistDao.findReceptionistByReceptionistId(id);
    }

    @Override
    public Receptionist findReceptionistByUsername(String username) {
        User user = userService.findUserByUsername(username);
        return receptionistDao.findReceptionistByUser(user);
    }

    @Override
    public List<Receptionist> findAllReceptionists() {
        return receptionistDao.findAll();
    }

    @Override
    public List<Notification> findNotifications(Receptionist receptionist) {
        User user = receptionist.getUser();
        return userService.findNotifications(user);
    }
}
