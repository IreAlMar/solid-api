package com.pilots.solidapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class LabelControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getLabelByItemName() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/getItemLabel?itemName=Pot")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"id\":6,\"name\":\"Pot\",\"price\":1.5,\"itemDescription\":\"Plant pot\"}")));
    }

    @Test
    public void getLabelById() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/getItemLabel?id=6")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"id\":6,\"name\":\"Pot\",\"price\":1.5,\"itemDescription\":\"Plant pot\"}")));
    }

    @Test
    public void saveLabel() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/createLabel")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":null,\"name\":\"Pot\",\"price\":1.5,\"itemDescription\":\"Plant pot\"}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

    }

}
