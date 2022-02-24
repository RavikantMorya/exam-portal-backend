package com.exam.controllers;

import com.exam.models.Role;
import com.exam.models.User;
import com.exam.models.UserRole;
import com.exam.repo.UserRepository;
import com.exam.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    //object user has all the data related to the user except its role
    // we want that the user should register as only normal user.We will manually add admin user
    @PostMapping("/")
    public User createUser(@RequestBody User user) throws Exception {
        user.setProfile("default.png");
        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));

        //createUser of service need two para user and a set of UserRole type
        //to create an object of userRole we need object of Role and User type
        //we already have User object in formal para and here we create object of Role

        Role role=new Role();
        role.setRole_id(344);
        role.setRoleName("NORMAL");

        System.out.println("Check1");
        UserRole userRole=new UserRole();
        userRole.setRole(role);
        userRole.setUser(user);

        Set<UserRole> userRoles=new HashSet<>();
        userRoles.add(userRole);
        System.out.println("Check2");
        User user1=this.userService.createUser(user,userRoles);
        System.out.println("Check3");
        System.out.println(user1);
        return user1;

    }

    @RequestMapping("/{username}")
    public User getUser(@PathVariable("username") String username)
    {
    //this.userRepository.findByUsername(username);
        User user=this.userService.getUser(username);
        System.out.println(user.getUsername()+"   "+user.getPassword());
        return user;
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id)
    {
            this.userService.deleteUser(id);
            return "User deleted";
    }

    @GetMapping("/test")
    public  String get()
    {
        return  "Hello! I am working fine\nI am backend API.";
    }

}
