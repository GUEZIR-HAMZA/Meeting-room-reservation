package ma.emsi.salleservice.dao;

import ma.emsi.salleservice.bean.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeDao extends JpaRepository<Type, Long> {
}
