package com.github.cyberpunkperson.dto;

import com.github.cyberpunkperson.Cacheable;
import com.github.cyberpunkperson.CacheKey;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RequestObject implements Cacheable {

    private boolean toggle;

    @CacheKey.Exclude
    private boolean excludedToggle;

    private String stringParam;

    @CacheKey.Exclude
    private String excludedStringParam;

    private List<InnerObject> innerObjects;

    private int intParam;

    @CacheKey.Exclude
    private int excludedIntParam;

    private float floatParam;

    private double doubleParam;

    private State state;

}
