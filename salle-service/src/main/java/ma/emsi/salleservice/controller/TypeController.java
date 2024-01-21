package ma.emsi.salleservice.controller;

import ma.emsi.salleservice.bean.Type;
import ma.emsi.salleservice.service.TypeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/types") // Mettez à jour le chemin du mapping
@CrossOrigin("*")
public class TypeController { // Renommez la classe en TypeController

    private TypeService typeService;

    public TypeController(TypeService typeService) {
        this.typeService = typeService;
    }

    @GetMapping
    public List<Type> findAll() {
        return typeService.findAll();
    }

    @GetMapping("id/{id}")
    public Type findById(@PathVariable Long id) {
        return typeService.findById(id).orElse(null);
    }

    @PostMapping
    public Type save(@RequestBody Type entity) {
        return typeService.save(entity);
    }

    @DeleteMapping("id/{id}")
    public void deleteById(@PathVariable Long id) {
        typeService.deleteById(id);
    }
}
