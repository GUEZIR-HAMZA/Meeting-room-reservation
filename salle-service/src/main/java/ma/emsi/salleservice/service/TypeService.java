package ma.emsi.salleservice.service;

import ma.emsi.salleservice.bean.Type;
import ma.emsi.salleservice.dao.TypeDao;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeService {

    private TypeDao typeDao;

    public TypeService(TypeDao typeDao) {
        this.typeDao = typeDao;
    }

    public List<Type> findAll() {
        return typeDao.findAll();
    }

    public Type save(Type entity) {
        return typeDao.save(entity);
    }

    public Optional<Type> findById(Long aLong) {
        return typeDao.findById(aLong);
    }

    public void deleteById(Long aLong) {
        typeDao.deleteById(aLong);
    }
}
