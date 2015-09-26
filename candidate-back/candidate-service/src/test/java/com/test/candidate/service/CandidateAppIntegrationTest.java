package com.test.candidate.service;

import com.test.candidate.service.CandidateServiceApp;
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
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
        mockMvc.perform(get("/candidate")).andExpect(status().isOk());
    }

    @Test
    public void createCandidateTest() throws Exception {
        mockMvc.perform(post("/candidate")
                .contentType(APPLICATION_JSON_UTF8)
                .content("{\"name\": \"john\",\"enabled\": true}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("john")))
                .andExpect(jsonPath("$.enabled", is(true)));
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
    public void createCandidateNameNullTest() throws Exception {
        mockMvc.perform(post("/candidate")
                .contentType(APPLICATION_JSON_UTF8)
                .content("{\"enabled\": true}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fieldErrors[0].field", is("name")))
                .andExpect(jsonPath("$.fieldErrors[0].code", is("NotNull")))
                .andExpect(jsonPath("$.fieldErrors[0].rejectedValue", is("null")));
    }
}
