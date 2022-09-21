package com.team012.server.utils.validator;

import com.team012.server.company.room.dto.RoomDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ListSizeValidator implements ConstraintValidator<ListSize, List<RoomDto.PostDto>> {


    @Override
    public void initialize(ListSize constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(List<RoomDto.PostDto> value, ConstraintValidatorContext context) {
        return value != null && value.size() > 0;
    }
}
