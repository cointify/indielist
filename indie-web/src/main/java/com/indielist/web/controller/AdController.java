package com.indielist.web.controller;

import com.indielist.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author jsingh on 15-01-23.
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/ads")
public class AdController {
    
    @Autowired
    AdService adService;

    @RequestMapping(method = RequestMethod.GET)
    public void getAllAds() {

    }
    
//    @RequestMapping(method = RequestMethod.GET)
//    public void getAdView() {
//
//    }
    
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public void processAddNewAdRequest() {
                
    }

    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    public void processEditAdRequest() {
        
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void processDeleteAdRequest() {
        
    }
}
