package com.nnk.springboot.services;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;


    @InjectMocks
    private UserServiceImpl userService;

    private User testObject;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        testObject = new User();
        testObject.setId(1);
        testObject.setUsername("testuser");
        testObject.setPassword("plaintext");
        testObject.setRole("USER");
    }

    @Test
    public void testFindAll() {
        when(userRepository.findAll()).thenReturn(Arrays.asList(testObject));
        assertEquals(1, userService.findAll().size());
    }

    @Test
    public void testFindById() {
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(testObject));
        User result = userService.findById(1);
        assertNotNull(result);
        assertEquals(testObject, result);
    }

    @Test
    public void testSave() {
        when(passwordEncoder.encode(any(CharSequence.class))).thenReturn("encoded-password");
        when(userRepository.save(any(User.class))).thenReturn(testObject);

        User result = userService.save(testObject);
        assertNotNull(result);
        verify(passwordEncoder).encode("plaintext");
    }

    @Test
    public void testDelete() {
        doNothing().when(userRepository).deleteById(anyInt());
        assertDoesNotThrow(() -> userService.delete(1));
    }
    @Test
    public void testFindById_NotFound() {
        when(userRepository.findById(99)).thenReturn(Optional.empty());
        Exception exception = assertThrows(IllegalArgumentException.class, () -> userService.findById(99));
        assertEquals("ID non trouvé : 99", exception.getMessage());
    }

}
