package com.airline.airline.Security.services;

import com.airline.airline.Security.models.Role;
import com.airline.airline.Security.models.User;
import com.airline.airline.Security.repositories.RoleRepository;
import com.airline.airline.Security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Set;
@Controller
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;


    public List<Role> findAll() {
        return roleRepository.findAll();
    }


    public Role findById(int id) {
        return roleRepository.findById(id).orElse(null);
    }


    public void delete(int id) {
        roleRepository.deleteById(id);
    }


    public void save(Role role) {
        roleRepository.save(role);
    }

    public void assignUserRole(Integer userId, Integer roleId) {
        User user = userRepository.findById(userId).orElse(null);
        Role role = roleRepository.findById(roleId).orElse(null);
        Set<Role> userRoles = user.getRoles();
        userRoles.add(role);
        user.setRoles(userRoles);
        userRepository.save(user);
    }
    public void unassignUserRole(Integer userId, Integer roleId) {
        User user = userRepository.findById(userId).orElse(null);
        user.getRoles().removeIf(x -> x.getId() == roleId);
        userRepository.save(user);
    }
    public Set<Role> getUserRoles(User user) {
        return user.getRoles();
    }
    public List<Role> getUserNotRoles(User user){
        return roleRepository.getUserNotRoles(user.getId());
    }


}
