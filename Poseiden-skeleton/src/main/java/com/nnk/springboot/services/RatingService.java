package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;
import java.util.List;

public interface RatingService {
    // TODO: Declare service methods for Rating CRUD
    List<Rating> findAll();
    Rating findById(Integer id);
    Rating save(Rating rating);
    Rating update(Rating rating);
    void delete(Integer id);
}
