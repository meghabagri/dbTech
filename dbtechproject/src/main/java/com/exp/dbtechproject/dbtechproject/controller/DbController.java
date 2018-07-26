package com.exp.dbtechproject.dbtechproject.controller;

import com.exp.dbtechproject.dbtechproject.impl.LoginImpl;
import com.exp.dbtechproject.dbtechproject.model.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DbController {

    @Autowired
    LoginImpl loginImpl;

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public String loginCheck(@RequestBody Login login){
        loginImpl.loginCheck(login);
        return "Data saved successfully";
    }


}
