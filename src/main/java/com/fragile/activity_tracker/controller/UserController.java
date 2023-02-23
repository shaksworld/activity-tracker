package com.fragile.activity_tracker.controller;

import com.fragile.activity_tracker.entity.Task;
import com.fragile.activity_tracker.entity.User;
import com.fragile.activity_tracker.error.UserNotFoundException;
import com.fragile.activity_tracker.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/signup")
    public String registerUserForm(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "signup";
    }

    @PostMapping("/signup")
    public String registerUser(@ModelAttribute ("user") User user, Model model) throws UserNotFoundException {
        User newUser = userService.createUser(user);
        model.addAttribute("success", newUser);

        return "redirect:/login?action=success";
    }

    @GetMapping("/login")
    public String loginUserForm(Model model, HttpServletRequest request){
        if(Objects.equals(request.getParameter("action"), "success")){
            model.addAttribute("success", true);
        }
        User user = new User();
        model.addAttribute("user", user);
        return "login";
    }


    @PostMapping("/login")
    public String LoginUser(@ModelAttribute ("user") User user,  HttpServletRequest request, HttpServletResponse response) throws UserNotFoundException {
            User loggedInUser = userService.findUserByEmailAndPassword(user);

            if (loggedInUser != null) {
                request.getSession().invalidate();
                HttpSession newSession = request.getSession(true);
                newSession.setMaxInactiveInterval(300);
                newSession.setAttribute("id", loggedInUser.getId());
                newSession.setAttribute("username", loggedInUser.getUsername());

                return "redirect:/dashboard";
            }
        return "redirect:/login";

        }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();

        return "redirect:/";
    }




}
