package com.airline.airline.Security.controllers;
import com.airline.airline.Security.models.User;
import com.airline.airline.Security.services.RoleService;
import com.airline.airline.Security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;


@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping("/security/users")
    public String getAll(Model model) {
        model.addAttribute("users", userService.findAll());
        return "/security/users";
    }


    @PostMapping("/users/addNew")
    public RedirectView addNew(User user, RedirectAttributes redic){
        userService.save(user);

        RedirectView redirectView = new RedirectView("/login", true);
        redic.addFlashAttribute("message", "You have successfully registered a new user");
        return  redirectView;
    }
    @GetMapping("/security/user/{op}/{id}")
    public String editUser(@PathVariable Integer id, @PathVariable String op, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        model.addAttribute("userRoles", roleService.getUserRoles(user));
        model.addAttribute("userNotRoles", roleService.getUserNotRoles(user));
        return "/security/user" + op; //returns employeeEdit or employeeDetails
    }
    @RequestMapping("/security/role/assign/{userId}/{roleId}")
    public String assignRole(@PathVariable Integer userId,
                             @PathVariable Integer roleId) {
        roleService.assignUserRole(userId, roleId);
        return "redirect:/security/user/Edit/" + userId;
    }

    @RequestMapping("/security/role/unassign/{userId}/{roleId}")
    public String unassignRole(@PathVariable Integer userId,
                               @PathVariable Integer roleId) {
        roleService.unassignUserRole(userId, roleId);
        return "redirect:/security/user/Edit/" + userId;
    }

}
