
package com.nnk.springboot.controllers;

import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.services.CurvePointService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CurveController.class)
public class CurveControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CurvePointRepository curvePointRepository;

    @MockBean
    private CurvePointService curvePointService;



    @Test
    @WithMockUser(username = "admin")
    public void testDefaultRoute() throws Exception {
        mockMvc.perform(get("/curvePoint/list"))
               .andExpect(status().isOk());
    }
    @Test
    @WithMockUser(username = "admin")
    public void testGetAddCurveForm() throws Exception {
        mockMvc.perform(get("/curvePoint/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("curvePoint/add"));
    }
    @Test
    @WithMockUser(username = "admin")
    public void testPostValidateCurvePoint() throws Exception {
        mockMvc.perform(post("/curvePoint/validate")
                        .param("curveId", "10")
                        .param("term", "1.0")
                        .param("value", "100.5")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/curvePoint/list"));
    }



}
