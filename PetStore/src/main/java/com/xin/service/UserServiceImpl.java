package com.xin.service;

import com.xin.dao.HobbyMapper;
import com.xin.dao.UserMapper;
import com.xin.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by golden on 2016/9/6 0006.
 */
@Service
public class UserServiceImpl implements IUserService{
    @Autowired
    private UserMapper user_dao;
    @Autowired
    private HobbyMapper hobby_dao;

    public User login(User user) {
        return user_dao.login(user);
    }

    public boolean  register(User user) {
          System.out.println(user.getHobby().getUsername());
          System.out.println(user.getHobby().getKindid());
          return user_dao.insert(user)>0  &&  hobby_dao.insert(user.getHobby())>0;
    }
}
