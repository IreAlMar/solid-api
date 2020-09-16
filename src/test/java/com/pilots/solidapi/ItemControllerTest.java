package com.pilots.solidapi;

import com.pilots.solidapi.application.item.NameRequestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ItemControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private NameRequestService nameRequestService;

    @BeforeEach
    public void setUp() {
        Mockito.when(nameRequestService.getName())
                .thenReturn("Pot");
    }

    @Test
    public void getItemByName() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/getItem?name=Pot")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"id\":1,\"name\":{\"itemName\":\"Pot\"},\"price\":{\"itemPrice\":20.0}}")));
    }

    @Test
    public void getItemInvalidName() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/getItem?name=Potato")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(equalTo("Invalid name Potato")));
    }

    @Test
    public void getItemById() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/getItem?id=2")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"id\":2,\"name\":{\"itemName\":\"Aloe vera\"},\"price\":{\"itemPrice\":4.2}}")));
    }

    @Test
    public void getItemInvalidId() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/getItem?id=500")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(equalTo("Invalid id 500")));
    }

    @Test
    public void saveItem() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/saveItem")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":null,\"price\":3.5,\"name\":\"Cactus\"}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void saveRickItem() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/saveItem")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":null,\"price\":3.5,\"name\":null}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void updateItemPrice() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/updateItemPrice?id=1&price=20")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"id\":1,\"name\":{\"itemName\":\"Pot\"},\"price\":{\"itemPrice\":20.0}}")));
    }

    @Test
    public void updateItemPriceItemNotFound() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/updateItemPrice?id=505&price=20")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string(equalTo("")));

    }

    @Test
    public void updateInvalidItemPrice() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/updateItemPrice?id=5&price=-8")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(equalTo("Invalid price -8.0")));

    }

    @Test
    public void saveItemByPrice() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/saveItem?price=50")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json("{\"id\":12,\"name\":{\"itemName\":\"Pot\"},\"price\":{\"itemPrice\":50" +
                        ".0}}", false));
    }

    @Test
    public void saveItemByPriceOverMaxPrice() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/saveItem?price=50000")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(equalTo("Invalid price 50000.0")));
    }

    @Test
    public void saveItemByPriceUnderMinPrice() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/saveItem?price=-8")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(equalTo("Invalid price -8.0")));
    }

    @Test
    public void saveItemInvalidPrice() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/saveItem")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":null,\"price\":-3.5,\"name\":null}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(equalTo("")));
    }
}
