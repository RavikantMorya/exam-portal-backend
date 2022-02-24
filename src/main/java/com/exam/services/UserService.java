package com.exam.services;

import com.exam.models.User;
import com.exam.models.UserRole;

import java.util.Set;

public interface UserService {
    //create a user
    public  User createUser(User user, Set<UserRole> set) throws Exception;
    //get the users
    public User getUser(String userName);
    //delete an  user
    public void deleteUser(Long userId);
}
