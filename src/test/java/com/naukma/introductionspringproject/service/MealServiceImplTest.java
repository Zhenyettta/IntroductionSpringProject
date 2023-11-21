package com.naukma.introductionspringproject.service;

import com.naukma.introductionspringproject.entity.MealEntity;
import com.naukma.introductionspringproject.model.Meal;
import com.naukma.introductionspringproject.repository.MealRepo;
import com.naukma.introductionspringproject.service.CategoryService;
import com.naukma.introductionspringproject.service.MealService;
import com.naukma.introductionspringproject.service.impl.MealServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class MealServiceImplTest {

    @Autowired
    private MealService mealService;

    @MockBean
    private MealRepo mealRepo;

    @Test
    public void FindingMealByValidIdTest() {
        Long id = 1L;

        Meal mockMeal = new Meal();
        mockMeal.setName("Pizza");
        mockMeal.setId(id);

        MealEntity mockMealEntity = new MealEntity();
        mockMealEntity.setName("Pizza");
        mockMealEntity.setId(id);

        when(mealRepo.findById(id)).thenReturn(Optional.of(mockMealEntity));

        Meal found = mealService.readMeal(id);

        verify(mealRepo, times(1)).findById(eq(id));

        assertNotNull(found);
        assertEquals("Pizza", found.getName());
    }

    @TestConfiguration
    static class MealServiceImplTestConfiguration {
        @Bean
        public MealService mealService(MealRepo mealRepo, CategoryService categoryService) {
            return new MealServiceImpl(new ModelMapper(), categoryService, mealRepo);
        }

        @Bean
        public CategoryService categoryService() {
            return Mockito.mock(CategoryService.class);
        }
    }
}