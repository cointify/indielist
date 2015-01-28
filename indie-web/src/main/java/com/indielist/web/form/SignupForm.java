package com.indielist.web.form;

import com.indielist.domain.User;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author jsingh on 15-01-19.
 */
public class SignupForm {

    private static final String NOT_BLANK_MESSAGE = "Field cannot be blank";

    @NotBlank(message = NOT_BLANK_MESSAGE)
    private String username;

    @NotBlank(message = NOT_BLANK_MESSAGE)
    private String email;

    @NotBlank(message = NOT_BLANK_MESSAGE)
    private String password;

    private String confirmPassword;

    @NotBlank(message = NOT_BLANK_MESSAGE)
    private String firstName;

    @NotBlank(message = NOT_BLANK_MESSAGE)
    private String lastName;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public User createUser() {
        User user = new User();
        user.setEmail(getEmail());
        user.setUsername(getUsername());
        user.setFirstName(getFirstName());
        user.setLastName(getLastName());
        user.setPassword(getPassword());
        return user;
    }
}
