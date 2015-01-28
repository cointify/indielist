package com.indielist.web.controller;

import com.indielist.domain.Role;
import com.indielist.domain.User;
import com.indielist.service.UserService;
import com.indielist.web.form.SignupForm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * @author jsingh on 15-01-19.
 */
@Controller
@Scope("prototype")
public class SignupController {

    private static final Logger log = LogManager.getLogger();
    public static final String SIGNUP_VIEW = "/signup";
    
    @Autowired
    UserService userService;

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String getSignUpView(Model model) {
        model.addAttribute("signupForm", new SignupForm());
        return SIGNUP_VIEW;            
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String processRegisterNewUserRequest(@Valid @ModelAttribute SignupForm signupForm, Errors errors, RedirectAttributes ra) throws Exception {
        if(errors.hasErrors()) {
            return SIGNUP_VIEW;
        }
        User user = signupForm.createUser();
        Role role = new Role();
        role.setName(Role.USER);
        user.getAuthorities().add(role);
        userService.createNewUser(user);
        return "redirect:" + SIGNUP_VIEW + "?success";
    }
}
