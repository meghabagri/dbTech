package com.exp.dbtechproject.dbtechproject.controller;

import com.exp.dbtechproject.dbtechproject.impl.LoginImpl;
import com.exp.dbtechproject.dbtechproject.impl.TradeOrderImpl;
import com.exp.dbtechproject.dbtechproject.model.Login;
import com.exp.dbtechproject.dbtechproject.model.TradeOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"}, methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})

public class DbController {

    @Autowired
    LoginImpl loginImpl;

    @Autowired
    TradeOrderImpl tradeImpl;

    @RequestMapping(value="/login")
    public String loginCheck(@RequestBody Login login){
        String result=loginImpl.loginCheck(login);
        System.out.println("DBController.java: "+result);
        return result;
    }

    @RequestMapping(value="/company")
    public List<Map<String,Object>> companyPage(@RequestParam String symbol){
        System.out.println("company name: "+symbol);
        List<Map<String,Object>> list=tradeImpl.topFiveBuy(symbol);
        List<Map<String,Object>> listSell=tradeImpl.topFiveSell(symbol);
        System.out.println(list);
        System.out.println(listSell);
        List<Map<String,Object>> listFinal = new ArrayList<Map<String,Object>>();
        listFinal.addAll(list);
        listFinal.addAll(listSell);
        return listFinal;
    }

    @RequestMapping(value="/insertBuyOrder",  method = RequestMethod.POST)
    public String saveData(@RequestBody TradeOrder trade){
        tradeImpl.insertBuyOrder(trade);
        return "Data saved successfully";
    }
    @RequestMapping(value="/customerOrders/{name}")
    public List<TradeOrder> getCustomerOrders(@PathVariable String name){
        System.out.println("megha");
        return tradeImpl.getOrders(name);
    }
}
