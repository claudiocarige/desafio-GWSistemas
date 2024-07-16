package br.com.claudiocarige.mspersistencedb.core.domain.builders;

import br.com.claudiocarige.mspersistencedb.core.domain.entities.Address;


public class AddressBuilder {

    private Long id;

    private String street;

    private String number;

    private String city;

    private String state;

    private String country;

    private String postalCode;

    private String latitudeCoordinate;

    private String longitudeCoordinate;

    private AddressBuilder() { }

    public static AddressBuilder oneAddress() {

        AddressBuilder builder = new AddressBuilder();
        initializeDefaultData( builder );
        return builder;
    }

    private static void initializeDefaultData( AddressBuilder builder ) {

        builder.id = 1L;
        builder.street = "Rua Salvador";
        builder.number = "10";
        builder.city = "Salvador";
        builder.state = "Bahia";
        builder.country = "Brasil";
        builder.postalCode = "41.000-000";
        builder.latitudeCoordinate = "-23.550520";
        builder.longitudeCoordinate = "-46.633308";
    }

    public AddressBuilder withId( Long id ) {

        this.id = id;
        return this;
    }

    public AddressBuilder withStreet( String street ) {

        this.street = street;
        return this;
    }

    public AddressBuilder withNumber( String number ) {

        this.number = number;
        return this;
    }

    public AddressBuilder withCity( String city ) {

        this.city = city;
        return this;
    }

    public AddressBuilder withState( String state ) {

        this.state = state;
        return this;
    }

    public AddressBuilder withCountry( String country ) {

        this.country = country;
        return this;
    }

    public AddressBuilder withPostalCode( String postalCode ) {

        this.postalCode = postalCode;
        return this;
    }

    public AddressBuilder withLatitudeCoordinate( String latitudeCoordinate ) {

        this.latitudeCoordinate = latitudeCoordinate;
        return this;
    }

    public AddressBuilder withLongitudeCoordinate( String longitudeCoordinate ) {

        this.longitudeCoordinate = longitudeCoordinate;
        return this;
    }

    public Address now() {

        return new Address( id, street, number, city, state,
                            country, postalCode, latitudeCoordinate, longitudeCoordinate );
    }

}
