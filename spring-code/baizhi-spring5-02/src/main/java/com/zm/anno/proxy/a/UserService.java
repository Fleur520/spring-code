package com.zm.anno.proxy.a;

import com.zm.anno.proxy.User;

public interface UserService {
    public void register(User user);

    public boolean login(String name, String password);
}
