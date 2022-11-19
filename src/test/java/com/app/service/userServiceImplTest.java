package com.app.service;


import com.app.model.User;
import com.app.repo.UserRepo;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class userServiceImplTest {


    @Mock
    private UserRepo userRepo;
    @InjectMocks
    private userServiceImpl userService;

    @Test
    void checkLoginValid() throws Exception {
        User user = new User();
        user.setId(1);
        user.setUserName("MHELALY");
        user.setPassword("{noop}admin01@123#");
//        User returnUser = userRepo.findByUserNameAndPassword("MHELALY","{noop}admin01@123#");
        given(userRepo.findByUserNameAndPassword("MHELALY", "{noop}admin01@123#")).willReturn(user);
        User returnUser = userService.checkLogin("MHELALY", "{noop}admin01@123#");

        assertThat(returnUser).isNotNull();
    }

    @Test
    void getEmployees() {
        User user = new User();
        user.setId(1);
        user.setUserName("MHELALY");
        user.setPassword("{noop}admin01@123#");
        given(this.userRepo.findById(1)).willReturn(Optional.of(user));

        User employees = userService.getEmployees(1);
        assertThat(employees).isNotNull();
    }

    @Test
    void getUser() throws Exception {
        User user = new User();
        user.setId(2);
        user.setUserName("MHELALY");
        user.setPassword("{noop}admin01@123#");
        given(this.userRepo.findById(1)).willReturn(Optional.of(user));
        User user1 = userService.getUser(1);
        assertThat(user1).isNotNull();


    }

    @Test
    void getExceptionUser() throws Exception {
        given(this.userRepo.findById(1)).willReturn(null);


//        org.junit.jupiter.api.Assertions.assertThrows(Exception.class,()->{this.userService.getUser(1);});
//        this.userService.getUser(1);
        assertThrows(Exception.class,
                () -> this.userService.getUser(1),
                () -> "Different exception thrown!");

//        org.junit.jupiter.api.Assertions.assertThrows(Exception.class, () -> {
//            this.userService.getUser(1);
//        });

    }

    @Test
    void getUsers() {
        User user = new User();
        user.setPassword("");
        user.setUserName("");
        user.setId(1);
        User user1 = new User();
        user1.setPassword("");
        user1.setUserName("");
        user1.setId(1);
        List users = new ArrayList<User>();
given(this.userRepo.findAll()).willReturn(users);

        List<User> usersService = this.userService.getUsers();
       assertThat(usersService).isNotNull();
    }

    @Test
    void updateUser() {
        User user = new User();
        user.setPassword("");
        user.setUserName("");
        user.setId(1);
        given(this.userRepo.save(user)).willReturn(user);
        User user1 = this.userService.updateUser(user);
assertThat(user1).isNotNull();

    }

    @Test
    void checkEmail() {
        User user = new User();
        user.setId(1);
        user.setUserName("Mahmoud");
        user.setPassword("P@ssw0rd");
        given(this.userRepo.findByEmail("")).willReturn(user);
        User newEmail = this.userService.checkEmail("");
        User user1 = new User();
        user1.setId(2);
        user1.setUserName("CCCC");
        user1.setPassword("P@4444");
//        assertThat(newEmail).;
        assertThat(newEmail).hasToString(user.toString());
    }


//    @Test
//    void checkLoginInvalid() {
//        given(userRepo.findByUserNameAndPassword("MHELALY", "{noop}admin01@123#")).willReturn(null);
//
//        assertThrows(Exception.class, () -> {
//            userService.checkLogin("","");
//
//            });
//}
}