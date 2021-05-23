package com.zm.anno.aop;


import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Override
    public void login() {
        System.out.println("login =====");

    }

    @Override
    public void register() {
        System.out.println("register===");
    }
}
