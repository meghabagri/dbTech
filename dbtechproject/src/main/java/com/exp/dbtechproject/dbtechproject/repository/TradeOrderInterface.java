package com.exp.dbtechproject.dbtechproject.repository;

import com.exp.dbtechproject.dbtechproject.model.TradeOrder;

import java.util.List;
import java.util.Map;

public interface TradeOrderInterface {
    public void insertBuyOrder (TradeOrder order);

    public List<Map<String,Object>> topFiveBuy (String companyName);

    public List<Map<String,Object>> topFiveSell (String companyName);

    public List<TradeOrder> getOrders (String clientName);

}
