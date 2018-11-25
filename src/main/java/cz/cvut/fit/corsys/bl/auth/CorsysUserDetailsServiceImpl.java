package cz.cvut.fit.corsys.bl.auth;

import cz.cvut.fit.corsys.dl.dao.UserDao;
import cz.cvut.fit.corsys.dl.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("corsysUserDetailsService")
public class CorsysUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User usr = userDao.findUserByUsername(username);
        if (usr == null) {
            throw new UsernameNotFoundException("User " + username + " not found.");
        }
        return new CorsysUserDetails(usr);
    }

}
