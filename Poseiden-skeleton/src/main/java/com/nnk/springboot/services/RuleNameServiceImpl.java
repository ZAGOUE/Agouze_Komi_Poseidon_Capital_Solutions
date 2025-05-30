package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RuleNameServiceImpl implements RuleNameService {

    // TODO: Inject repository
    @Autowired
    private RuleNameRepository ruleNameRepository;

    // TODO: Implement service methods
    @Override
    public List<RuleName> findAll() {
        return ruleNameRepository.findAll();
    }

    @Override
    public RuleName findById(Integer id) {
        return ruleNameRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid ID"));
    }

    @Override
    public RuleName save(RuleName ruleName) {
        return ruleNameRepository.save(ruleName);
    }

    @Override
    public RuleName update(RuleName ruleName) {
        return ruleNameRepository.save(ruleName);
    }

    @Override
    public void delete(Integer id) {
        ruleNameRepository.deleteById(id);
    }
}
