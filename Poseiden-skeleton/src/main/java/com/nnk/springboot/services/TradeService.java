package com.nnk.springboot.services;

import com.nnk.springboot.domain.Trade;
import java.util.List;

public interface TradeService {
    List<Trade> findAll();
    Trade findById(Integer id);
    Trade save(Trade trade);
    Trade update(Trade trade);
    void delete(Integer id);
}
