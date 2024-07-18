package br.com.claudiocarige.mspersistencedb.core.exceptions;

public class DataIntegrityViolationException extends RuntimeException {

    public DataIntegrityViolationException( String message ) {

        super( message );
    }

}
