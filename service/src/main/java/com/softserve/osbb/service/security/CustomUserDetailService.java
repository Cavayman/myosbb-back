//package com.softserve.osbb.service.security;
//
///**
// * Created by cavayman on 19.07.2016.
// */
//
//
//import com.softserve.osbb.model.CustomUserDetails;
//import com.softserve.osbb.model.User;
//import com.softserve.osbb.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service("userDetailsService")
//public class CustomUserDetailService implements UserDetailsService {
//    @Autowired
//    UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        User user=userRepository.findUserByEmail(email);
//        if(user==null)
//        {
//            throw new UsernameNotFoundException("User not found with this email:"+email);
//        }
//        return new CustomUserDetails(user);
//    }
//}
//
