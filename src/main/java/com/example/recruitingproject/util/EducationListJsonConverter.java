package com.example.recruitingproject.util;

import com.example.recruitingproject.entity.Education;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;

@Converter
@RequiredArgsConstructor
public class EducationListJsonConverter implements AttributeConverter<List<Education>, String> {
    private final ObjectMapper objectMapper;

    @Override
    public String convertToDatabaseColumn(List<Education> attribute) {
        if (Objects.isNull(attribute))
            return Strings.EMPTY;
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            // TODO: Error handler 구현 예정
            throw new RuntimeException("ERROR: CANNOT CONVERT LIST TO JSON", e);
        }
    }

    @Override
    public List<Education> convertToEntityAttribute(String dbData) {
        if (Strings.isBlank(dbData))
            return Collections.emptyList();
        try {
            return objectMapper.readValue(dbData, new TypeReference<>(){});
        } catch (JsonProcessingException e) {
            // TODO: Error handler 구현 예정
            throw new RuntimeException("ERROR: CANNOT CONVERT JSON TO LIST", e);
        }
    }
}
