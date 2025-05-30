package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@WebMvcTest(RuleNameController.class)
public class RuleNameControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private RuleNameRepository ruleNameRepository;

    @Test
    @WithMockUser(username = "user", roles = "USER")
    public void testListPage() throws Exception {
        mockMvc.perform(get("/ruleName/list"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user", roles = "USER")
    public void testGetAddRuleNameForm() throws Exception {
        mockMvc.perform(get("/ruleName/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("ruleName/add"));
    }

    @Test
    @WithMockUser(username = "user", roles = "USER")
    public void testPostValidateRuleName() throws Exception {
        mockMvc.perform(post("/ruleName/validate")
                        .param("name", "Test Rule")
                        .param("description", "Test Description")
                        .param("json", "json test")
                        .param("template", "template test")
                        .param("sqlStr", "SELECT * FROM table")
                        .param("sqlPart", "WHERE id > 0")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/ruleName/list"));
    }
    @Test
    @WithMockUser(username = "user", roles = "USER")
    public void testDeleteRuleName() throws Exception {
        RuleName rule = new RuleName();
        rule.setId(1);
        when(ruleNameRepository.findById(1)).thenReturn(Optional.of(rule));

        mockMvc.perform(get("/ruleName/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/ruleName/list"));
    }


}
