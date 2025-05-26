package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BidListServiceImpl implements BidListService {

    @Autowired
    private BidListRepository bidListRepository;

    @Override
    public List<BidList> findAll() {
        return bidListRepository.findAll();
    }

    @Override
    public BidList findById(Integer id) {
        return bidListRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid BidList Id:" + id));
    }

    @Override
    public BidList save(BidList bidList) {
        return bidListRepository.save(bidList);
    }

    @Override
    public BidList update(BidList bidList) {
        return bidListRepository.save(bidList);
    }

    @Override
    public void delete(Integer id) {
        bidListRepository.deleteById(id);
    }
}
