package br.com.claudiocarige.mspersistencedb.infra.config;

import br.com.claudiocarige.mspersistencedb.core.domain.entities.CompanyCustomer;
import br.com.claudiocarige.mspersistencedb.core.domain.entities.IndividualCustomer;
import br.com.claudiocarige.mspersistencedb.core.dtos.CustomerResponseDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ModelMapperConfig {


    @Bean
    public ModelMapper modelMapper() {

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy( MatchingStrategies.STRICT );

        modelMapper.addMappings( new PropertyMap< IndividualCustomer, CustomerResponseDTO >() {
            @Override
            protected void configure() {

                map().setCpfOrCnpj( source.getCpf() );
            }
        } );

        modelMapper.addMappings( new PropertyMap< CompanyCustomer, CustomerResponseDTO >() {
            @Override
            protected void configure() {

                map().setCpfOrCnpj( source.getCnpj() );
            }
        } );

        return modelMapper;
    }

}
