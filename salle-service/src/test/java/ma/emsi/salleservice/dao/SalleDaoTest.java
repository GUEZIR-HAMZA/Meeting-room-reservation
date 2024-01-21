package ma.emsi.salleservice.dao;

import ma.emsi.salleservice.bean.Type;
import ma.emsi.salleservice.bean.Salle;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class SalleDaoTest {

    @Autowired
    private SalleDao salleDao;

    @Test
    public void SalleDao_Save_ReturnsSavedSalle() {
        Type type = Type.builder()
                .nom("Réunion")
                .build();
        Salle salle = Salle.builder()
                .numero("Salle A")
                .climatisation(true)
                .equipement("Projecteur")
                .type(type)
                .capacite(12)
                .build();

        Salle savedSalle = salleDao.save(salle);

        Assertions.assertNotNull(savedSalle);
    }

    @Test
    public void SalleDao_FindAll_ReturnsMoreThanOneSalle() {
        Type type1 = Type.builder()
                .nom("Réunion")
                .build();
        Type type2 = Type.builder()
                .nom("Formation")
                .build();

        Salle salle1 = Salle.builder()
                .numero("Salle B")
                .climatisation(true)
                .equipement("Tableau blanc")
                .type(type1)
                .capacite(10)
                .build();
        Salle salle2 = Salle.builder()
                .numero("Salle C")
                .climatisation(false)
                .equipement("Table")
                .type(type2)
                .capacite(15)
                .build();

        salleDao.save(salle1);
        salleDao.save(salle2);

        List<Salle> salleList = salleDao.findAll();

        Assertions.assertNotNull(salleList);
        Assertions.assertEquals(2, salleList.size());

    }

    @Test
    public void SalleDao_FindById_ReturnsSalle() {
        Type type = Type.builder()
                .nom("Formation")
                .build();
        Salle salle = Salle.builder()
                .numero("Salle D")
                .climatisation(true)
                .equipement("Ordinateur")
                .type(type)
                .capacite(20)
                .build();

        salleDao.save(salle);

        Salle returnedSalle = salleDao.findById(salle.getId()).orElse(null);

        Assertions.assertNotNull(returnedSalle);
    }

    @Test
    public void SalleDao_DeleteById_ReturnsNull() {
        Type type = Type.builder()
                .nom("Réunion")
                .build();
        Salle salle = Salle.builder()
                .numero("Salle E")
                .climatisation(true)
                .equipement("Chaises")
                .type(type)
                .capacite(30)
                .build();

        salleDao.save(salle);

        salleDao.deleteById(salle.getId());

        Salle returnedSalle = salleDao.findById(salle.getId()).orElse(null);

        Assertions.assertNull(returnedSalle);
    }
}
