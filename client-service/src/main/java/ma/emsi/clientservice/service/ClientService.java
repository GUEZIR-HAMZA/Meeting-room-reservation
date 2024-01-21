package ma.emsi.clientservice.service;

import ma.emsi.clientservice.bean.Client;
import ma.emsi.clientservice.dao.ClientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientDao cd;

    public List<Client> findAll() {
        return cd.findAll();
    }

    public Client save(Client client) {
        // Vous pouvez ajouter ici des validations si nécessaire
        return cd.save(client); // Retourne l'objet Client sauvegardé.
    }

    public Client findById(Long id) {
        return cd.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found for this id :: " + id));
    }

    public Client updateClient(Long id, Client clientDetails) {
        Client client = findById(id);
        client.setNom(clientDetails.getNom());
        client.setPrenom(clientDetails.getPrenom());
        client.setAdresse(clientDetails.getAdresse()); // Corrigé la faute de frappe
        client.setTelephone(clientDetails.getTelephone());
        client.setEmail(clientDetails.getEmail());
        // Vous pouvez ajouter ici des validations si nécessaire
        return cd.save(client); // Sauvegarde et retourne l'objet Client mis à jour.
    }

    public void deleteById(Long id) {
        cd.deleteById(id);
    }

    public void deleteAll() {
        cd.deleteAll();
    }
}
