package com.naukma.introductionspringproject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.naukma.introductionspringproject.dto.CategoryDTO;
import com.naukma.introductionspringproject.model.Category;
import com.naukma.introductionspringproject.service.CategoryService;
import com.naukma.introductionspringproject.util.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CategoryController.class)
//@Import(LoginConfig.class)
public class CategoryControllerTest {

    CategoryDTO categoryDTO;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private WebApplicationContext context;
    @MockBean
    private CategoryService categoryService;
    @MockBean
    private JwtService jwtService;

    @BeforeEach
    public void setup() {
        categoryDTO = new CategoryDTO();
        categoryDTO.setId(1L);
        categoryDTO.setName("milk");
        categoryDTO.setMealIds(Arrays.asList(1L, 2L));
        mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
        objectMapper = new ObjectMapper();
    }

    @WithMockUser(username = "123@gmail.com", roles = {"ADMIN"})
    @Test
    void whenGetCategories_thenReturn200() throws Exception {
        mockMvc.perform(get("/categories/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @WithMockUser(username = "123@gmail.com", roles = {"ADMIN"})
    @Test
    void whenValidInput_thenReturns200() throws Exception {
        when(categoryService.createCategory(any(Category.class))).thenReturn(objectMapper.convertValue(categoryDTO, Category.class));

        mockMvc.perform(post("/categories")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(categoryDTO)))
                .andExpect(status().isOk());

    }

    @WithMockUser(username = "123@gmail.com", roles = {"ADMIN"})
    @Test
    public void shouldUpdateCategoryTest() throws Exception {
        when(categoryService.readCategory(categoryDTO.getId())).thenReturn(objectMapper.convertValue(categoryDTO, Category.class));
        mockMvc.perform(put("/categories").contentType(MediaType.APPLICATION_JSON)
                        .with(csrf())
                        .content(objectMapper.writeValueAsString(categoryDTO)))
                        .andExpect(status().isOk());
    }

    @WithMockUser(username = "123@gmail.com", roles = {"ADMIN"})
    @Test
    public void whenDeleteCategories_thenReturns200() throws Exception {
        mockMvc.perform(delete("/categories/{id}", 1L)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());
    }
}
