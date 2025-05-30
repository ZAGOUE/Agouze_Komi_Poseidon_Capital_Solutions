package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BidListServiceTest {

    @Mock
    private BidListRepository bidlistRepository;

    @InjectMocks
    private BidListServiceImpl bidlistService;

    private BidList testObject;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        testObject = new BidList();
    }

    @Test
    public void testFindAll() {
        when(bidlistRepository.findAll()).thenReturn(Arrays.asList(testObject));
        assertEquals(1, bidlistService.findAll().size());
    }

    @Test
    public void testFindById() {
        when(bidlistRepository.findById(anyInt())).thenReturn(Optional.of(testObject));
        BidList result = bidlistService.findById(1);
        assertNotNull(result);
        assertEquals(testObject, result);
    }


    @Test
    public void testSave() {
        when(bidlistRepository.save(any(BidList.class))).thenReturn(testObject);
        assertEquals(testObject, bidlistService.save(testObject));
    }

    @Test
    public void testDelete() {
        doNothing().when(bidlistRepository).deleteById(anyInt());
        assertDoesNotThrow(() -> bidlistService.delete(1));
    }
}
