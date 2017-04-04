package com.xin.service;

import com.xin.model.User;

/**
 * Created by golden on 2016/9/6 0006.
 */
public interface IUserService {
    User login(User user);
    boolean  register(User user);
}
