package br.com.claudiocarige.mspersistencedb.infra.persistence.repositories.postgresrepository;

import br.com.claudiocarige.mspersistencedb.core.domain.entities.Delivery;
import br.com.claudiocarige.mspersistencedb.core.domain.enums.DeliveryStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface DeliveryRepository extends JpaRepository< Delivery, Long > {

    @Modifying
    @Query( "UPDATE Delivery d SET d.statusDelivery = :statusDelivery WHERE d.id = :id" )
    int updateStatusDelivery( @Param( "id" ) Long id, @Param( "statusDelivery" ) DeliveryStatus statusDelivery );

    @Query( "SELECT d.statusDelivery FROM Delivery d WHERE d.id = :id" )
    DeliveryStatus findStatusDeliveryById( @Param( "id" ) Long id );

}
