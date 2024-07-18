package br.com.claudiocarige.mspersistencedb.core.dtos;

import br.com.claudiocarige.mspersistencedb.core.domain.entities.Customers;
import br.com.claudiocarige.mspersistencedb.core.domain.entities.Item;
import br.com.claudiocarige.mspersistencedb.core.domain.enums.DeliveryStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryDTO {

    private Long id;

    private Customers sender;

    private Customers recipient;

    private DeliveryStatus statusDelivery;

    private BigDecimal freightValue;

    List< Item > itemsList = new ArrayList<>();

}
