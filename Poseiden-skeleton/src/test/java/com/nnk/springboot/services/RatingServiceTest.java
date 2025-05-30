package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RatingServiceTest {

    @Mock
    private RatingRepository ratingRepository;

    @InjectMocks
    private RatingServiceImpl ratingService;

    private Rating testObject;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        testObject = new Rating();
    }

    @Test
    public void testFindAll() {
        when(ratingRepository.findAll()).thenReturn(Arrays.asList(testObject));
        assertEquals(1, ratingService.findAll().size());
    }

    @Test
    public void testFindById() {
        when(ratingRepository.findById(anyInt())).thenReturn(Optional.of(testObject));
        Rating result = ratingService.findById(1);
        assertNotNull(result);
        assertEquals(testObject, result);
    }

    @Test
    public void testSave() {
        when(ratingRepository.save(any(Rating.class))).thenReturn(testObject);
        assertEquals(testObject, ratingService.save(testObject));
    }

    @Test
    public void testDelete() {
        doNothing().when(ratingRepository).deleteById(anyInt());
        assertDoesNotThrow(() -> ratingService.delete(1));
    }
}
