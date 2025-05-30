package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CurvePointServiceTest {

    @Mock
    private CurvePointRepository curvepointRepository;

    @InjectMocks
    private CurvePointServiceImpl curvepointService;

    private CurvePoint testObject;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        testObject = new CurvePoint();
    }

    @Test
    public void testFindAll() {
        when(curvepointRepository.findAll()).thenReturn(Arrays.asList(testObject));
        assertEquals(1, curvepointService.findAll().size());
    }

    @Test
    public void testFindById() {
        when(curvepointRepository.findById(anyInt())).thenReturn(Optional.of(testObject));
        CurvePoint result = curvepointService.findById(1);
        assertNotNull(result);
        assertEquals(testObject, result);
    }

    @Test
    public void testSave() {
        when(curvepointRepository.save(any(CurvePoint.class))).thenReturn(testObject);
        assertEquals(testObject, curvepointService.save(testObject));
    }

    @Test
    public void testDelete() {
        doNothing().when(curvepointRepository).deleteById(anyInt());
        assertDoesNotThrow(() -> curvepointService.delete(1));
    }
}
