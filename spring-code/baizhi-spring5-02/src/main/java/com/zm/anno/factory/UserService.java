package com.zm.anno.factory;

import com.zm.anno.proxy.User;

public interface UserService {
    public void login(String name, String password);

    public void register(User user);
}
