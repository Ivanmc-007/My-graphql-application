package com.ivan.mygraphql.mapper;

import com.ivan.mygraphql.model.dto.ClientDto;
import com.ivan.mygraphql.model.entity.Client;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClientMapper implements SimpleMapper<Client, ClientDto> {

    private final ModelMapper modelMapper;

    @Override
    public Client toEntity(ClientDto clientDto) {
        return modelMapper.map(clientDto, Client.class);
    }

    @Override
    public ClientDto toDto(Client client) {
        return modelMapper.map(client, ClientDto.class);
    }
}
