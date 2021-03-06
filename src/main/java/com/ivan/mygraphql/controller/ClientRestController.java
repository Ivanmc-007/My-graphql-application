package com.ivan.mygraphql.controller;

import com.ivan.mygraphql.exception.ClientNotFoundException;
import com.ivan.mygraphql.mapper.ClientMapper;
import com.ivan.mygraphql.model.dto.ClientDto;
import com.ivan.mygraphql.model.entity.Client;
import com.ivan.mygraphql.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
@Log4j2
public class ClientRestController {

    private final ClientService clientService;

    private final ClientMapper clientMapper;

    @Operation(summary = "Get a client by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the client",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Client.class)) }),
            @ApiResponse(responseCode = "404", description = "Client not found",
                    content = @Content) })
    @GetMapping("/{id}")
    @Transactional(readOnly = true)
    public Client getClientById(@PathVariable Long id) {
        log.info("GET Client with ID: " + id);
        return clientService.getClientById(id).orElseThrow(ClientNotFoundException::new);
    }

    @Operation(summary = "Client successfully saved")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Save the client",
                    content = { @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ClientDto.class)
                    ) })
    })
    @PostMapping
    @Transactional(rollbackFor = { Exception.class })
    public Client saveClient(@RequestBody ClientDto clientDto) {
        log.info("TRYING TO SAVE NEW " + clientDto);
        Client client = clientMapper.toEntity(clientDto);
        return clientService.save(client);
    }

    @Operation(summary = "Update the name of client by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Client.class)) }),
            @ApiResponse(responseCode = "404", description = "Client not found",
                    content = @Content) })
    @PutMapping("/update-name/{id}")
    @Transactional(rollbackFor = { Exception.class })
    public Client updateById(@PathVariable Long id, @RequestBody String name) {
        Client oldClient = clientService.getClientById(id).orElseThrow(ClientNotFoundException::new);
        oldClient.setName(name);
        return clientService.save(oldClient);
    }

    @GetMapping
    @Transactional(readOnly = true)
    public List<Client> getAll() {
        return clientService.getAll();
    }

}
