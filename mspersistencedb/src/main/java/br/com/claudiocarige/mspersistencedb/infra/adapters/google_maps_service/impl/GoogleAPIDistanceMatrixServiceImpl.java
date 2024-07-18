package br.com.claudiocarige.mspersistencedb.infra.adapters.google_maps_service.impl;

import br.com.claudiocarige.mspersistencedb.infra.adapters.google_maps_service.GoogleAPIDistanceMatrixService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


@Slf4j
@Service
public class GoogleAPIDistanceMatrixServiceImpl implements GoogleAPIDistanceMatrixService {

    private static final String URL =
            "https://maps.googleapis.com/maps/api/distancematrix/json?origins=%s&destinations=%s&key=%s";

    @Value( "${google.maps.api.key}" )
    String apiKey;

    private final RestTemplate restTemplate;


    public GoogleAPIDistanceMatrixServiceImpl( RestTemplate restTemplate ) {

        this.restTemplate = restTemplate;
    }

    @Override
    public Double searchFromDistance( String origin, String destination ) {

        String url = String.format( URL, origin, destination, apiKey );

        try {
            ResponseEntity< String > response = restTemplate.exchange( url, HttpMethod.GET,
                    null, String.class );
            log.info( "RESPONSE FROM GOOGLE MAPS API   :  {}", response.getBody() );
            return getJsonResponse( response.getBody() );

        } catch( HttpClientErrorException e ) {
            throw new RuntimeException( "Error in request to Google Maps API: " + e.getMessage(), e );
        }
    }

    private Double getJsonResponse( String jsonResponse ) {

        System.out.println( jsonResponse );
        try {
            JSONObject jsonObject = new JSONObject( jsonResponse );
            JSONArray rows = jsonObject.getJSONArray( "rows" );
            if( ! rows.isEmpty() ) {
                JSONObject firstRow = rows.getJSONObject( 0 );
                JSONArray elements = firstRow.getJSONArray( "elements" );
                if( ! elements.isEmpty() ) {
                    JSONObject firstElement = elements.getJSONObject( 0 );
                    JSONObject distance = firstElement.getJSONObject( "distance" );
                    double distanceValue = distance.getDouble( "value" );
                    return distanceValue / 1000;
                }
            }
        } catch( JSONException e ) {
            throw new JSONException( "Invalid response from Google Maps API: " + e.getMessage() );
        }
        return 0.0;
    }

}