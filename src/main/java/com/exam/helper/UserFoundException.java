package com.exam.helper;

public class UserFoundException extends Exception {
    public UserFoundException() {
            super("User Already Found with this user name!!!!");
    }
    public UserFoundException(String s){super(s);}
}
