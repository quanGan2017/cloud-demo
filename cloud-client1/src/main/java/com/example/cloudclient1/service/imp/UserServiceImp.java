package com.example.cloudclient1.service.imp;


import com.example.cloudclient1.dao.UserDao;
import com.example.cloudclient1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public List getUserList() {
        return userDao.getUserList();
    }
}
