package com.fiatalis.repositories;

import java.util.List;

public interface UserDao {

    User findById(Long id);

    List<User> findAll();

    User findByName(String name);

    void save(User user);

    void update(Long id, String name, String password);


}
