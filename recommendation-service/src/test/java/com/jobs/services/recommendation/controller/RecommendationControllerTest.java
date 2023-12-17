package com.jobs.services.recommendation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jobs.services.recommendation.factory.RecommendationFactory;
import com.jobs.services.recommendation.interfaces.Recommender;
import com.jobs.services.recommendation.interfaces.SearchService;
import com.jobs.services.recommendation.model.RecommendationType;
import com.jobs.services.recommendation.service.UserProfileBasedRecommender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.Assert;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = RecommendationController.class)
public class RecommendationControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Mock
    private SearchService searchService;
    @MockBean
    private RecommendationFactory recommendationFactory;
    @Mock
    private UserProfileBasedRecommender recommender;


    @BeforeEach
    void init() {
        recommendationFactory=new RecommendationFactory(recommender);
    }

    @Test
    public void validateInputParams() throws Exception {
        mockMvc.perform(get("/v1/recommendations")
                        .contentType("application/json")
                        .param("pageNum", "0")
                        .param("size", "1000")
                        .param("type", RecommendationType.PROFILE.name())
                        .header("correlationId", "12345")
                        .header("userId", "test"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void validRecommendationRequest() throws Exception {
        MvcResult mvcResult =  mockMvc.perform(get("/v1/recommendations")
                        .contentType("application/json")
                        .param("pageNum", "0")
                        .param("size", "10")
                        .param("type", RecommendationType.PROFILE.name())
                        .header("correlationId", "12345")
                        .header("userId", "test"))
                .andExpect(status().isOk())
                .andReturn();
        Assert.notNull(mvcResult.getResponse().getContentAsString(),"Invalid response");
    }
}

