package com.ivan.mygraphql.mapper;

public interface SimpleMapper<ENTITY, DTO> {

    ENTITY toEntity(DTO dto);

    DTO toDto(ENTITY entity);

}
