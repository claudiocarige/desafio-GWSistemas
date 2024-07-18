package br.com.claudiocarige.mspersistencedb.core.domain.entities;


import br.com.claudiocarige.mspersistencedb.core.domain.enums.DeliveryStatus;
import br.com.claudiocarige.mspersistencedb.core.exceptions.NoSuchElementException;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Delivery {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @ManyToOne
    @JoinColumn( name = "sender_id" )
    private Customers sender;

    @ManyToOne
    @JoinColumn( name = "recipient_id" )
    private Customers recipient;

    @Enumerated( EnumType.STRING )
    private DeliveryStatus statusDelivery;

    @Column( unique = true )
    private String passwordDelivery;

    private LocalDate dateSolicitation = LocalDate.now();

    @Column(precision = 10, scale = 2)
    private BigDecimal freightValue;

    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER )
    @JoinColumn( name = "delivery_id" )
    List< Item > itemsList = new ArrayList<>();

    public void addItemInList( Item item ) {

        this.itemsList.add( item );
    }

    public void removeItemOfList( Long id ) {

        this.itemsList.remove( id );
    }

    public void updateItemInList( String productName, Integer quantity ) {

        for( Item item : itemsList ) {
            if( item.getProductName().equals( productName ) ) {
                item.setQuantity( quantity );
                break;
            }else{
                throw new NoSuchElementException("Item not found in List of items.");
            }
        }
    }

    @Override
    public boolean equals( Object o ) {

        if( this == o ) return true;
        if( o == null || getClass() != o.getClass() ) return false;
        Delivery delivery = ( Delivery ) o;
        return Objects.equals( id, delivery.id );
    }

    @Override
    public int hashCode() {

        return Objects.hashCode( id );
    }


}
