package com.example.cloudclient1.dao;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserDao {
    List getUserList();
}
