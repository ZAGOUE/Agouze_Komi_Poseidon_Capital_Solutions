package com.nnk.springboot.services;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TradeServiceTest {

    @Mock
    private TradeRepository tradeRepository;

    @InjectMocks
    private TradeServiceImpl tradeService;

    private Trade testObject;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        testObject = new Trade();
    }

    @Test
    public void testFindAll() {
        when(tradeRepository.findAll()).thenReturn(Arrays.asList(testObject));
        assertEquals(1, tradeService.findAll().size());
    }

    @Test
    public void testFindById() {
        when(tradeRepository.findById(anyInt())).thenReturn(Optional.of(testObject));
        Trade result = tradeService.findById(1);
        assertNotNull(result);
        assertEquals(testObject, result);
    }

    @Test
    public void testSave() {
        when(tradeRepository.save(any(Trade.class))).thenReturn(testObject);
        assertEquals(testObject, tradeService.save(testObject));
    }

    @Test
    public void testDelete() {
        doNothing().when(tradeRepository).deleteById(anyInt());
        assertDoesNotThrow(() -> tradeService.delete(1));
    }
}
