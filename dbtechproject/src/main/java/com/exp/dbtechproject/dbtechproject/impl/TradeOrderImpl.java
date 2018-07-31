package com.exp.dbtechproject.dbtechproject.impl;

import com.exp.dbtechproject.dbtechproject.model.TradeOrder;
import com.exp.dbtechproject.dbtechproject.repository.TradeOrderInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Repository
public class TradeOrderImpl implements TradeOrderInterface {

    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public void insertBuyOrder(TradeOrder order) {
        String sql="Insert into buy (CLIENT_NAME, SECURITY, QUANTITY, LIMIT_PRICE, VALUE, hours, mins, secs) "+"values(?,?,?,?,?,?,?,?)";
        Integer hour=LocalDateTime.now().getHour();
        Integer min=LocalDateTime.now().getMinute();
        Integer sec=LocalDateTime.now().getSecond();
        Double netValue=order.getLimit_price()*order.getQuantity();
        jdbcTemplate.update(sql,new Object[]{order.getClientName(),order.getSecurity(),order.getQuantity(),order.getLimit_price(), netValue,hour, min, sec });
        System.out.println("buy data insert successfully");
        return;
    }

    @Override
    public List<Map<String,Object>> topFiveBuy(String companyName) {
        String sql2="SELECT QUANTITY,LIMIT_PRICE FROM buy WHERE SECURITY=? ORDER BY LIMIT_PRICE DESC LIMIT 5";
         List<Map<String,Object>> data = jdbcTemplate.queryForList(sql2, new Object[]{companyName},new int[]{Types.VARCHAR});
         System.out.println("query running " +data);
         return data;
    }
    @Override
    public List<Map<String,Object>> topFiveSell(String companyName) {
        String sql3="SELECT QUANTITY,LIMIT_PRICE FROM sell WHERE SECURITY=? ORDER BY LIMIT_PRICE ASC LIMIT 5";
        List<Map<String,Object>> data = jdbcTemplate.queryForList(sql3, new Object[]{companyName},new int[]{Types.VARCHAR});
        System.out.println("query running " +data);
        return data;
    }

    @Override
    public List<TradeOrder> getOrders(String clientName) {
        String sql4="select * from buy where CLIENT_NAME=?";
        List<TradeOrder> list = jdbcTemplate.query(sql4,new Object[]{clientName}, new BeanPropertyRowMapper<>(TradeOrder.class));
        System.out.print("Customer Order"+ list);
        return list;
    }

}
