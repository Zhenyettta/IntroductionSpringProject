package com.naukma.introductionspringproject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.naukma.introductionspringproject.dto.MealDTO;
import com.naukma.introductionspringproject.model.Meal;
import com.naukma.introductionspringproject.service.MealService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MealControllerTest {
    MealDTO mealDTO;
    @Autowired
    private WebApplicationContext context;
    @MockBean
    private MealService mealService;
    private MockMvc mvc;
    private ObjectMapper mapper;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();

        mealDTO = new MealDTO();
        mealDTO.setId(2L);
        mealDTO.setName("Updated Meal");
        mealDTO.setPrice(123.0);
        mealDTO.setAmount(2);
        mealDTO.setCategoryId(1L);
        mealDTO.setOrderIds(Arrays.asList(1L, 2L));
        mealDTO.setTagIds(Arrays.asList(1L, 2L, 3L));

        mapper = new ObjectMapper();
    }

    @WithMockUser(username = "123@gmail.com", roles = {"ADMIN"})
    @Test
    public void shouldGetMealByIdTest() throws Exception {
        Meal mockedMeal = new Meal();
        when(mealService.readMeal(1L)).thenReturn(mockedMeal);

        mvc.perform(get("/meals/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(mealService, times(1)).readMeal(1L);
    }

    @WithMockUser(username = "123@gmail.com", roles = {"ADMIN"})
    @Test
    public void shouldGetAllMealsTest() throws Exception {
        mvc.perform(get("/meals")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @WithMockUser(username = "123@gmail.com", roles = {"ADMIN"})
    @Test
    public void shouldCreateMealTest() throws Exception {
        when(mealService.createMeal(any(Meal.class))).thenReturn(mapper.convertValue(mealDTO, Meal.class));
        mvc.perform(post("/meals")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(mealDTO)))
                .andExpect(status().isOk());
    }

    @WithMockUser(username = "123@gmail.com", roles = {"ADMIN"})
    @Test
    public void shouldUpdateMealTest() throws Exception {


        mvc.perform(put("/meals").contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(mealDTO)))
                .andExpect(status().isOk());
    }

    @WithMockUser(username = "123@gmail.com", roles = {"ADMIN"})
    @Test
    public void shouldDeleteMealTest() throws Exception {
        mvc.perform(delete("/meals/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}