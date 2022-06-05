package org.zhanghao.graduationproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.zhanghao.graduationproject.bean.User;
import org.zhanghao.graduationproject.mapper.UserMapper;

import java.util.List;
@Service
public class AuthService implements UserDetailsService {
    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User byNameDetail = userMapper.getByNameDetail(s);

        return org.springframework.security.core.userdetails.User
                .withUsername(byNameDetail.getName())
                .password(byNameDetail.getPassword())
                .roles(String.valueOf(byNameDetail.getRole()))
                .build();
    }
}
