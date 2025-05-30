package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UserService userService;

    private User mockAdmin() {
        User admin = new User();
        admin.setId(1);
        admin.setUsername("admin");
        admin.setFullname("Admin Test");
        admin.setPassword("encodedpassword");
        admin.setRole("ROLE_ADMIN");
        return admin;
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testUserList() throws Exception {
        when(userService.findByUsername("admin")).thenReturn(Optional.of(mockAdmin()));
        mockMvc.perform(get("/user/list"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testGetAddUserForm() throws Exception {
        mockMvc.perform(get("/user/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/add"));
    }



    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testGetChangePasswordForm() throws Exception {
        User user = mockAdmin();
        when(userService.findById(1)).thenReturn(user);
        when(userService.findByUsername("admin")).thenReturn(Optional.of(user));

        mockMvc.perform(get("/user/change-password/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/change-password"));
    }


}
