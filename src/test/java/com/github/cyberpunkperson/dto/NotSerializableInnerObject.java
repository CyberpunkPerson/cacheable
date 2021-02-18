package com.github.cyberpunkperson.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotSerializableInnerObject {

    private boolean toggle;

    private String stringParam;

    private int intParam;

    private float floatParam;

    private double doubleParam;

    private State state;

}
