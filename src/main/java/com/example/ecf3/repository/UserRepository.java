package com.example.ecf3.repository;

import com.example.ecf3.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    public User findByEmailAndPassword(String email, String password);

    public User findByEmail(String email);
    public User getById(int id);


}
