package br.com.claudiocarige.mspersistencedb.core.domain.enums;

public enum ShippingRates {

    TX_SECURITY(0.1, "Rate Security"),
    TX_VOLUME(0.2, "Rate Volume"),
    TX_WEIGHT(0.3, "Rate Weight"),
    TX_ADM(0.3, "Rate Adm"),
    TX_FUEL(6.5, "Rate Fuel");

    private double rate;
    private String name;

    ShippingRates( double rate, String name ) {

        this.rate = rate;
        this.name = name;
    }

    public double getRate() {

        return rate;
    }

    public void setRate( double rate ) {

        this.rate = rate;
    }

    public String getName() {

        return name;
    }

    public void setName( String name ) {

        this.name = name;
    }
}
