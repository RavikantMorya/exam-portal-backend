package com.exam.services.impl;

import com.exam.helper.UserFoundException;
import com.exam.models.User;
import com.exam.models.UserRole;
import com.exam.repo.RoleRepository;
import com.exam.repo.UserRepository;
import com.exam.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User createUser(User user, Set<UserRole> set) throws Exception {

        //fetch all the users from databases
        User user1=this.userRepository.findByUsername(user.getUsername());

        if(user1!=null)
        {
            System.out.println("User already exist.. ");
            throw  new UserFoundException("Please try with another username as it already exists...");
        }

        else
        {
            //save all the roles in UserRole table
            for (UserRole ur:set)
            {
                this.roleRepository.save(ur.getRole());
            }
            // assign all the roles to the user
            user.getUserRoles().addAll(set);
            //now save the user
            user1= this.userRepository.save(user);
        }

        return user1;
    }

    @Override
    public User getUser(String userName) {
        return  this.userRepository.findByUsername(userName);
    }

    @Override
    public void deleteUser(Long userId) {
        this.userRepository.deleteById(userId);
    }
}
