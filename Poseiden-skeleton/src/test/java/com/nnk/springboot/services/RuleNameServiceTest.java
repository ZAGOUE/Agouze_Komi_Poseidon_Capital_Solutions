package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RuleNameServiceTest {

    @Mock
    private RuleNameRepository rulenameRepository;

    @InjectMocks
    private RuleNameServiceImpl rulenameService;

    private RuleName testObject;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        testObject = new RuleName();
    }

    @Test
    public void testFindAll() {
        when(rulenameRepository.findAll()).thenReturn(Arrays.asList(testObject));
        assertEquals(1, rulenameService.findAll().size());
    }

    @Test
    public void testFindById() {
        when(rulenameRepository.findById(anyInt())).thenReturn(Optional.of(testObject));
        RuleName result = rulenameService.findById(1);
        assertNotNull(result);
        assertEquals(testObject, result);
    }


    @Test
    public void testSave() {
        when(rulenameRepository.save(any(RuleName.class))).thenReturn(testObject);
        assertEquals(testObject, rulenameService.save(testObject));
    }

    @Test
    public void testDelete() {
        doNothing().when(rulenameRepository).deleteById(anyInt());
        assertDoesNotThrow(() -> rulenameService.delete(1));
    }
}
