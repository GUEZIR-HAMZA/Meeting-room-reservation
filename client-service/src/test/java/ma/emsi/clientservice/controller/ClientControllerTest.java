package ma.emsi.clientservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ma.emsi.clientservice.bean.Client;
import ma.emsi.clientservice.controllers.ClientController;
import ma.emsi.clientservice.service.ClientService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@WebMvcTest(controllers = ClientController.class)
@AutoConfigureMockMvc(addFilters = false)
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientService clientService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testFindAll() throws Exception {
        List<Client> clients = Arrays.asList(
                new Client(1L, "John", "Doe", "123 Street", "123456789", "john@example.com"),
                new Client(2L, "Jane", "Doe", "456 Avenue", "987654321", "jane@example.com")
        );

        when(clientService.findAll()).thenReturn(clients);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/clients")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(clients)));
    }

    @Test
    void testFindById() throws Exception {
        Long clientId = 1L;
        Client client = new Client(clientId, "John", "Doe", "123 Street", "123456789", "john@example.com");

        when(clientService.findById(clientId)).thenReturn(client);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/clients/id/{id}", clientId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(client)));
    }

    @Test
    void testSave() throws Exception {
        Client clientToSave = new Client(null, "John", "Doe", "123 Street", "123456789", "john@example.com");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clientToSave)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void testDeleteAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/clients")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    void testDeleteById() throws Exception {
        Long clientId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/clients/id/{id}", clientId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}
