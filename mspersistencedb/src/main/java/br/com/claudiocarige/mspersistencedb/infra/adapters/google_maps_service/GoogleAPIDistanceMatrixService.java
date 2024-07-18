package br.com.claudiocarige.mspersistencedb.infra.adapters.google_maps_service;

public interface GoogleAPIDistanceMatrixService {

    Double searchFromDistance(String origin, String destination);
}
