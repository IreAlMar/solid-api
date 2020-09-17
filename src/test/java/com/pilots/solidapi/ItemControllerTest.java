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
        mvc.perform(MockMvcRequestBuilders.get("/saveItem?name=Cactus&price=3.5")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().string(equalTo("{\"id\":10,\"name\":{\"itemName\":\"Cactus\"}," +
                        "\"price\":{\"itemPrice\":3.5}}")));
    }

    @Test
    public void saveItemNoName() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/saveItem?price=3.5")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void saveItemInvalidName() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/saveItem?name=&price=3.5")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void saveItemNullPrice() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/saveItem?name=Cactus&price=null")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void saveItemInvalidPrice() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/saveItem?name=Cactus&price=-3.5")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(equalTo("Invalid price -3.5")));
    }

    @Test
    public void saveItemNoPrice() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/saveItem?name=Cactus")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
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
    public void createItemByPrice() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/createItem?price=50")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json("{\"id\":11,\"name\":{\"itemName\":\"Pot\"},\"price\":{\"itemPrice\":50" +
                        ".0}}", false));
    }

    @Test
    public void createItemByPriceOverMaxPrice() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/createItem?price=50000")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(equalTo("Invalid price 50000.0")));
    }

    @Test
    public void createItemByPriceUnderMinPrice() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/createItem?price=-8")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(equalTo("Invalid price -8.0")));
    }

    @Test
    public void createItemInvalidPrice() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/createItem?price=price")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(equalTo("")));
    }

    //TODO
    @Test
    public void createItemNameRequesterOff() throws Exception {
        Mockito.when(nameRequestService.getName())
                .thenReturn(null);
        mvc.perform(MockMvcRequestBuilders.get("/createItem?price=50")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }
}
