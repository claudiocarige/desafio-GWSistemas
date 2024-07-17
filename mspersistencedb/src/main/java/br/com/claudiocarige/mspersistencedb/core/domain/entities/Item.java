package br.com.claudiocarige.mspersistencedb.core.domain.entities;


import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Item {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    private String productName;

    private int quantity;

}
