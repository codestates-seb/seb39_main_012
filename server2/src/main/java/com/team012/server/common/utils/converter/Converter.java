package com.team012.server.common.utils.converter;

import java.util.List;

public interface Converter<Entity, DTO> {

    DTO toDTO(Entity entity);

    Entity toEntity(DTO dto);

    List<DTO> toListDTO(List<Entity> entityList);
}
