package com.github.cyberpunkperson.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class InnerObject implements Serializable {

    private boolean toggle;

    private String stringParam;

    private int intParam;

    private float floatParam;

    private double doubleParam;

    private State state;

}
