package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;
import java.util.List;

public interface RuleNameService {
    // TODO: Declare service methods for RuleName CRUD
    List<RuleName> findAll();
    RuleName findById(Integer id);
    RuleName save(RuleName ruleName);
    RuleName update(RuleName ruleName);
    void delete(Integer id);
}
