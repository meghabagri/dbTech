package com.exp.dbtechproject.dbtechproject.impl;

import com.exp.dbtechproject.dbtechproject.model.Login;
import com.exp.dbtechproject.dbtechproject.repository.LoginInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMethod;

@Repository
public class LoginImpl implements LoginInterface {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public String loginCheck(Login login) {
        String sql = "Select * from login where email=? and password=?";
        Login data = null;
        try {
            data = jdbcTemplate.queryForObject(sql, new Object[]{login.getEmail(), login.getPassword()},
                    new BeanPropertyRowMapper<>(Login.class));

        } catch (Exception e) {
            System.out.println("error in login");

        }
        return data!=null?data.getEmail():"invalid user";
    }



}
