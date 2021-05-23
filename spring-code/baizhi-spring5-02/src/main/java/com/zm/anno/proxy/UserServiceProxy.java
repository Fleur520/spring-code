package com.zm.anno.proxy;

import com.zm.anno.proxy.a.UserService;
import com.zm.anno.proxy.a.UserServiceImpl;

public class UserServiceProxy implements UserService {
    private UserServiceImpl userService = new UserServiceImpl();

    @Override
    public void register(User user) {
        System.out.println("----log-----");
        userService.register(user);
    }

    @Override
    public boolean login(String name, String password) {
        System.out.println("----log-----");
        return userService.login(name,password);
    }
}
