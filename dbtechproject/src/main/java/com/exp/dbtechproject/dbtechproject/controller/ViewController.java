package com.exp.dbtechproject.dbtechproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

     @RequestMapping("/")
        public String loginMessage(){
            return "index.html";
        }
    }

