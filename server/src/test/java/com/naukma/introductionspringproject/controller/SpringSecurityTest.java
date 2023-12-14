package com.naukma.introductionspringproject.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringSecurityTest {
    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
    }

    @WithMockUser(username = "admin_username", roles = "ADMIN")
    @Test
    public void testAdminEndpointAccess() throws Exception {
        mvc.perform(get("/users/{id}", 1L))
                .andExpect(status().isOk());
    }

    @WithMockUser(username = "user_username", roles = "USER")
    @Test
    public void testUnauthorizedAccess() throws Exception {
        mvc.perform(get("/users/{id}", 1L))
                .andExpect(status().isForbidden());
    }

    @WithMockUser(username = "user_username", roles = "USER")
    @Test
    public void testUserEndpointAccess() throws Exception {
        mvc.perform(get("/meals"))
                .andExpect(status().isOk());
    }

    @Test
    public void testUnauthenticatedAccessToLogin() throws Exception {
        mvc.perform(get("/login"))
                .andExpect(status().isOk());
    }

    @Test
    public void testUnauthenticatedAccess() throws Exception {
        mvc.perform(get("/meals"))
                .andExpect(status().is3xxRedirection());
    }

    @WithMockUser(username = "user_username", roles = "USER")
    @Test
    public void testUserAccessToHomePage() throws Exception {
        mvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    @WithMockUser(username = "admin_username", roles = "ADMIN")
    @Test
    public void testLoginAccessToHomePage() throws Exception {
        mvc.perform(get("/"))
                .andExpect(status().isOk());
    }

}

