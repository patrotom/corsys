package cz.cvut.fit.corsys.bl.auth;

import cz.cvut.fit.corsys.dl.dao.UserDao;
import cz.cvut.fit.corsys.dl.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Implementation of UserDetailsInterface defined by Spring security.
 */
@Service("corsysUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    /**
     * @param username
     * @return UserDetails
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User usr = userDao.findUserByUsername(username);
        if (usr == null) {
            throw new UsernameNotFoundException("User " + username + " not found.");
        }
        return new UserDetailsImpl(usr);
    }

}
