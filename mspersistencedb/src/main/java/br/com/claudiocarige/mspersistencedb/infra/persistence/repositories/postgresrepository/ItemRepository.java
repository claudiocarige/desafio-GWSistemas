package br.com.claudiocarige.mspersistencedb.infra.persistence.repositories.postgresrepository;

import br.com.claudiocarige.mspersistencedb.core.domain.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ItemRepository extends JpaRepository< Item, Long> {
}
