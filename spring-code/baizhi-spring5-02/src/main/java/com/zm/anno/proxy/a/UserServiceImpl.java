package com.zm.anno.proxy.a;

import com.zm.anno.Log;
import com.zm.anno.proxy.User;

public class UserServiceImpl implements UserService {


    // @Log
    @Override
    public void register(User user) {
        System.out.println("UserServiceImpl.register 业务运算 + DAO ");
        //throw new RuntimeException("测试异常");
    }

    @Override
    public boolean login(String name, String password) {
        System.out.println("UserServiceImpl.login");
        return true;
    }
}
