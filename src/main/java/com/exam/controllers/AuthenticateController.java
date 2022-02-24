package com.exam.controllers;

import com.exam.config.JwtUtils;
import com.exam.models.JwtRequest;
import com.exam.models.JwtResponse;
import com.exam.models.User;
import com.exam.services.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin("*")  //it will help to accept any http request from browser
public class AuthenticateController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private JwtUtils jwtUtils;

    @GetMapping("/current-user")
    public User currentUser(Principal principal)
    {
        return (User) this.userDetailsService.loadUserByUsername(principal.getName());
    }
    //this method is used to generate the token
    @PostMapping("/generated-token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        try {
                    authenticate(jwtRequest.getUsername(),jwtRequest.getPassword());
        }catch (UsernameNotFoundException e)
        {
            e.printStackTrace();
            throw  new Exception("User Not Found Exception");
        }

        //*******user is authenticated*****;
        UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getUsername());
      String token=  this.jwtUtils.generateToken(userDetails);
      return ResponseEntity.ok(new JwtResponse(token));
    }

    //this method is used to authenticate the user
    private void authenticate(String username,String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        }catch (DisabledException e)
        {
            System.out.println("DISABLED USER"+e.getMessage());
            throw new Exception("DISABLED USER");
        }catch (BadCredentialsException e)
        {
            System.out.println("Invalid Credentials"+e.getMessage());
            throw new Exception("Invalid Credentials");
        }
    }

}
