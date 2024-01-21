package ma.emsi.clientservice.service;

import ma.emsi.clientservice.bean.Client;
import ma.emsi.clientservice.dao.ClientDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ClientServiceTest {

    @Mock
    private ClientDao clientDao;

    @InjectMocks
    private ClientService clientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        // Given
        List<Client> clients = Arrays.asList(
                new Client(1L, "John", "Doe", "123 Street", "123456789", "john@example.com"),
                new Client(2L, "Jane", "Doe", "456 Avenue", "987654321", "jane@example.com")
        );

        when(clientDao.findAll()).thenReturn(clients);

        // When
        List<Client> result = clientService.findAll();

        // Then
        Assertions.assertEquals(clients.size(), result.size());
        Assertions.assertTrue(result.containsAll(clients));
    }

    @Test
    void testSave() {
        // Given
        Client clientToSave = new Client(null, "John", "Doe", "123 Street", "123456789", "john@example.com");
        Client savedClient = new Client(1L, "John", "Doe", "123 Street", "123456789", "john@example.com");

        when(clientDao.save(any(Client.class))).thenReturn(savedClient);

        // When
        Client result = clientService.save(clientToSave);

        // Then
        Assertions.assertEquals(savedClient, result);
    }

    @Test
    void testFindById() {
        // Given
        Long clientId = 1L;
        Client client = new Client(clientId, "John", "Doe", "123 Street", "123456789", "john@example.com");

        when(clientDao.findById(clientId)).thenReturn(Optional.of(client));

        // When
        Client result = clientService.findById(clientId);

        // Then
        Assertions.assertEquals(client, result);
    }

    @Test
    void testUpdateClient() {
        // Given
        Long clientId = 1L;
        Client originalClient = new Client(clientId, "John", "Doe", "123 Street", "123456789", "john@example.com");
        Client updatedClient = new Client(clientId, "John", "Doe", "456 Avenue", "987654321", "john_updated@example.com");

        when(clientDao.findById(clientId)).thenReturn(Optional.of(originalClient));
        when(clientDao.save(any(Client.class))).thenReturn(updatedClient);

        // When
        Client result = clientService.updateClient(clientId, updatedClient);

        // Then
        Assertions.assertEquals(updatedClient, result);
    }

    @Test
    void testDeleteById() {
        // Given
        Long clientId = 1L;

        // When
        clientService.deleteById(clientId);

        // Then
        verify(clientDao, times(1)).deleteById(clientId);
    }

    @Test
    void testDeleteAll() {
        // When
        clientService.deleteAll();

        // Then
        verify(clientDao, times(1)).deleteAll();
    }
}
