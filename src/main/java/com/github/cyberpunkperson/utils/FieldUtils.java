package com.github.cyberpunkperson.utils;

import java.lang.reflect.Field;
import java.util.Optional;

public class FieldUtils {

    public static Object getFieldValue(Field field, Object source) throws IllegalAccessException {
        return Optional.ofNullable(field.get(source)).orElseThrow(() ->
                new IllegalArgumentException("Value does not set for %s.%s"
                        .formatted(source.getClass(), field.getName())));
    }

}
