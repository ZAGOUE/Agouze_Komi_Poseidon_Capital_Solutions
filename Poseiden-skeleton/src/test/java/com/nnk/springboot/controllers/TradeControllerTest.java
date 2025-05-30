package com.nnk.springboot.controllers;

import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.services.TradeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;


@WebMvcTest(TradeController.class)
@ActiveProfiles("test") // utilise application-test.properties
public class TradeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TradeService tradeService;

    @MockBean
    private TradeRepository tradeRepository;

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testTradeListRoute() throws Exception {
        mockMvc.perform(get("/trade/list"))
                .andExpect(status().isOk());
    }
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testGetAddTradeForm() throws Exception {
        mockMvc.perform(get("/trade/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("trade/add"));
    }
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testPostValidateTrade() throws Exception {
        mockMvc.perform(post("/trade/validate")
                        .param("account", "NewAccount")
                        .param("type", "Buy")
                        .param("buyQuantity", "100")
                        .with(csrf())) // Si CSRF activé
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/trade/list"));
    }




}
