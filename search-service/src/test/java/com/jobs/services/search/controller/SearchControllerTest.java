package com.jobs.services.search.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jobs.services.search.dto.Filter;
import com.jobs.services.search.interfaces.SearchService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.Assert;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = SearchController.class)
public class SearchControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private SearchService searchService;

    @Test
    public void validateInputParams() throws Exception {
        Filter filter = Filter.builder().location("mumbai").build();
        mockMvc.perform(post("/v1/search/jobs")
                        .contentType("application/json")
                        .param("pageNum", "0")
                        .param("size", "1000")
                        .param("query", "java")
                        .header("correlationId", "12345")
                        .content(objectMapper.writeValueAsString(filter)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void validSearchRequest() throws Exception {
        Filter filter = Filter.builder().location("mumbai").build();
        MvcResult mvcResult = mockMvc.perform(post("/v1/search/jobs")
                        .contentType("application/json")
                        .param("pageNum", "0")
                        .param("size", "10")
                        .param("query", "java")
                        .header("correlationId", "12345")
                        .content(objectMapper.writeValueAsString(filter)))
                .andExpect(status().isOk())
                .andReturn();
        Assert.notNull(mvcResult.getResponse().getContentAsString(),"Invalid response");
    }
}
