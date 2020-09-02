package com.pilots.solidapi;

import com.pilots.solidapi.repository.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ItemControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getItemByName() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/getItem?name=Pot")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"id\":1,\"name\":\"Pot\",\"price\":1.5}")));
    }

    @Test
    public void getItemById() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/getItem?id=2")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"id\":2,\"name\":\"Aloe vera\",\"price\":4.2}")));
    }

    @Test
    public void saveItem() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/saveItem")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":null,\"price\":3.5,\"name\":\"Cactus\"}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

    }

    //TODO
    @Test
    public void saveItemInvalid() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/saveItem")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":null,\"price\":3.5,\"name\":15}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}
