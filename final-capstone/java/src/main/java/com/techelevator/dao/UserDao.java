package com.techelevator.dao;

import com.techelevator.model.User;

import java.util.List;

public interface UserDao {

    List<User> findAll();

    User getUserById(Long userId);

    User findByUsername(String username);

    int findIdByUsername(String username);

//    boolean create(String username, String password, String role,String firstName);

    int createMember(int userId, String firstName, int familyId, String memberType);

    int createFamily(String username);

    int createUser(String username, String password, String role);

}
