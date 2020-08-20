package com.gcx.service;

import com.gcx.entity.JwtUser;
import com.gcx.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by echisan on 2018/6/23
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

   /* @Autowired
    private UserRepository userRepository;
*/
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
      //  User user = userRepository.findByUsername(s);
        User user = null;
        return new JwtUser(user);
    }

}
