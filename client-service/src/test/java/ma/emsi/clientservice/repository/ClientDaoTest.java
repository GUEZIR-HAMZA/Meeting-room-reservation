package ma.emsi.clientservice.repository;

import ma.emsi.clientservice.bean.Client;
import ma.emsi.clientservice.dao.ClientDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ClientDaoTest {

    @Autowired
    private ClientDao clientDao;

    @Test
    public void testSave() {
        Client client = new Client(null, "John", "Doe", "123 Street", "123456789", "john@example.com");

        Client savedClient = clientDao.save(client);

        Assertions.assertNotNull(savedClient.getId());
    }

    @Test
    public void testFindAll() {
        Client client1 = new Client(null, "John", "Doe", "123 Street", "123456789", "john@example.com");
        Client client2 = new Client(null, "Jane", "Doe", "456 Avenue", "987654321", "jane@example.com");

        clientDao.save(client1);
        clientDao.save(client2);

        List<Client> clientList = clientDao.findAll();

        Assertions.assertNotNull(clientList);
        Assertions.assertEquals(2, clientList.size());
    }

    @Test
    public void testFindById() {
        Client client = new Client(null, "John", "Doe", "123 Street", "123456789", "john@example.com");

        clientDao.save(client);

        Optional<Client> returnedClient = clientDao.findById(client.getId());

        Assertions.assertTrue(returnedClient.isPresent());
        Assertions.assertEquals(client.getId(), returnedClient.get().getId());
    }

    @Test
    public void testDeleteById() {
        Client client = new Client(null, "John", "Doe", "123 Street", "123456789", "john@example.com");

        clientDao.save(client);

        clientDao.deleteById(client.getId());

        Optional<Client> returnedClient = clientDao.findById(client.getId());

        Assertions.assertFalse(returnedClient.isPresent());
    }
}
