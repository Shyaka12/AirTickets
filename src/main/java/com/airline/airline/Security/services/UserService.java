package com.airline.airline.Security.services;

import com.airline.airline.Security.models.User;
import com.airline.airline.Security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public void delete(int id) {
        userRepository.deleteById(id);
    }

    public void save(User user){
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
    }

}
