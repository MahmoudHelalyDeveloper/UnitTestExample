package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.User;
import com.app.repo.UserRepo;

import java.util.List;

@Service
public class userServiceImpl extends userService {

    @Autowired
    private UserRepo userRepo;


    @Override
    public User checkLogin(String userName, String password) throws Exception {
        // TODO Auto-generated method stub
        User user = this.userRepo.findByUserNameAndPassword(userName, password);

        if (user == null) {
            throw new Exception("invalid UserName and Password");
        } else {
            return user;
        }

    }

    @Override
    public User getEmployees(Integer id) {
        return this.userRepo.findById(id).get();
    }

    @Override
    public User getUser(Integer userId) throws Exception {
        User user = null;
        try {
            user =   this.userRepo.findById(userId).get();
        } catch (Exception e) {
            throw new Exception("Invalid User");}
        return user;
    }

    @Override
    public List<User> getUsers() {
        return this.userRepo.findAll();
    }

    @Override
    public User updateUser(User user) {
        return this.userRepo.save(user);
    }

    @Override
    public User checkEmail(String email) {
        return this.userRepo.findByEmail(email);
    }
}
