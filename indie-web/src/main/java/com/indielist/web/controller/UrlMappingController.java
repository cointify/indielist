package com.indielist.web.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author jsingh on 15-01-17.
 */
@Controller
@Scope("prototype")
public class UrlMappingController {

    @RequestMapping(value = {"/", "/home", "/index"})
    public String getRootView() {
        return "index";
    }

    @RequestMapping(value = "/login")
    public String getLoginView() {
        return "login";
    }

    @RequestMapping(value = "/admin/home")
    public String getAdminView() {
        return "admin/home";
    }
    
    @RequestMapping(value = "/user/home")
    public String getUserView() {
        return "user/home";
    }
}
