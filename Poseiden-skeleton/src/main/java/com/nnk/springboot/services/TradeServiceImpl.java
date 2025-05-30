package com.nnk.springboot.services;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TradeServiceImpl implements TradeService {

    @Autowired
    private TradeRepository tradeRepository;

    @Override
    public List<Trade> findAll() {
        return tradeRepository.findAll();
    }

    @Override
    public Trade findById(Integer id) {
        return tradeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ID non trouvé : " + id));
    }

    @Override
    public Trade save(Trade trade) {
        return tradeRepository.save(trade);
    }

    @Override
    public Trade update(Trade trade) {
        return tradeRepository.save(trade);
    }

    @Override
    public void delete(Integer id) {
        tradeRepository.deleteById(id);
    }
}
