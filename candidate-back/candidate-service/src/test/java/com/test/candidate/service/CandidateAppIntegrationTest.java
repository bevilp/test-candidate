package com.test.candidate.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * candidate
 *
 * @author Ben
 * @since 25/09/2015
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {CandidateServiceApp.class})
@WebAppConfiguration
@Transactional
public class CandidateAppIntegrationTest {

    private static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    public void findAllTest() throws Exception {
        mockMvc.perform(get("/candidate"))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(status().isOk());

    }

    @Test
    public void createCandidateTest() throws Exception {
        mockMvc.perform(post("/candidate")
                .contentType(APPLICATION_JSON_UTF8)
                .content("{\"name\": \"new user\",\"enabled\": true}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("new user")))
                .andExpect(jsonPath("$.enabled", is(true)));
    }

    @Test
    public void updateCandidateTest() throws Exception {
        mockMvc.perform(put("/candidate/1")
                .contentType(APPLICATION_JSON_UTF8)
                .content("{\"name\": \"john the new\",\"enabled\": true}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("john the new")));
    }

    @Test
    public void updateCandidateNotFoundTest() throws Exception {
        mockMvc.perform(put("/candidate/10")
                .contentType(APPLICATION_JSON_UTF8)
                .content("{\"name\": \"john\",\"enabled\": true}"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void createCandidateNameTooLongTest() throws Exception {
        final String tooLongName = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        mockMvc.perform(post("/candidate")
                .contentType(APPLICATION_JSON_UTF8)
                .content("{\"name\": \"" + tooLongName + "\",\"enable\": true}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fieldErrors[0].field", is("name")))
                .andExpect(jsonPath("$.fieldErrors[0].code", is("Size")))
                .andExpect(jsonPath("$.fieldErrors[0].rejectedValue", is(tooLongName)));
    }

    @Test
    public void createCandidateEmptyNameTest() throws Exception {
        mockMvc.perform(post("/candidate")
                .contentType(APPLICATION_JSON_UTF8)
                .content("{\"name\": \"\",\"enable\": true}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fieldErrors[0].field", is("name")))
                .andExpect(jsonPath("$.fieldErrors[0].code", is("Size")))
                .andExpect(jsonPath("$.fieldErrors[0].rejectedValue", is("")));
    }

    @Test
    public void createCandidateNameNullTest() throws Exception {
        mockMvc.perform(post("/candidate")
                .contentType(APPLICATION_JSON_UTF8)
                .content("{\"enabled\": true}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fieldErrors[0].field", is("name")))
                .andExpect(jsonPath("$.fieldErrors[0].code", is("NotNull")))
                .andExpect(jsonPath("$.fieldErrors[0].rejectedValue", is("null")));
    }

    @Test
    public void deleteCandidatesTest() throws Exception {
        mockMvc.perform(delete("/candidate")
                .contentType(APPLICATION_JSON_UTF8)
                .content("{\"ids\":[1,2]}"))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/candidate"))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(status().isOk());

    }
}
