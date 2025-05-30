package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@WebMvcTest(RatingController.class)
public class RatingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RatingRepository ratingRepository;

    @WithMockUser(username = "testuser")
    @Test
    public void testListPage() throws Exception {
        mockMvc.perform(get("/rating/list"))
                .andExpect(status().isOk());
    }
    @Test
    @WithMockUser(username = "testuser")
    public void testGetAddRatingForm() throws Exception {
        mockMvc.perform(get("/rating/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("rating/add"));
    }
    @Test
    @WithMockUser(username = "testuser")
    public void testPostValidateRating() throws Exception {
        mockMvc.perform(post("/rating/validate")
                        .param("moodysRating", "A1")
                        .param("sandPRating", "A+")
                        .param("fitchRating", "A-")
                        .param("orderNumber", "1")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/rating/list"));
    }
    @Test
    @WithMockUser(username = "testuser")
    public void testPostUpdateRating() throws Exception {
        Rating rating = new Rating();
        rating.setId(1);
        when(ratingRepository.findById(1)).thenReturn(Optional.of(rating));

        mockMvc.perform(post("/rating/update/1")
                        .param("moodysRating", "A2")
                        .param("sandPRating", "A")
                        .param("fitchRating", "A-")
                        .param("orderNumber", "2")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/rating/list"));
    }
    @Test
    @WithMockUser(username = "testuser")
    public void testDeleteRating() throws Exception {
        Rating rating = new Rating();
        rating.setId(1);
        when(ratingRepository.findById(1)).thenReturn(Optional.of(rating));

        mockMvc.perform(get("/rating/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/rating/list"));
    }


}
