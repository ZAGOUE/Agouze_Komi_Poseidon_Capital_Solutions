package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;
import java.util.List;

public interface CurvePointService {
    // TODO: Declare service methods for CurvePoint CRUD
    List<CurvePoint> findAll();
    CurvePoint findById(Integer id);
    CurvePoint save(CurvePoint curvePoint);
    CurvePoint update(CurvePoint curvePoint);
    void delete(Integer id);
}
