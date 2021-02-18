package com.github.cyberpunkperson;


import com.github.cyberpunkperson.dto.InnerObject;
import com.github.cyberpunkperson.dto.NotSerializableInnerObject;
import com.github.cyberpunkperson.dto.NotSerializableRequestObject;
import com.github.cyberpunkperson.dto.RequestObject;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.io.NotSerializableException;
import java.util.List;

import static com.github.cyberpunkperson.dto.State.DISABLE;
import static com.github.cyberpunkperson.dto.State.ENABLE;
import static org.junit.jupiter.api.Assertions.*;

public class CacheableTest {


    @RepeatedTest(10000)
    public void getCacheKeyWithSameDataEqualityExpected() {
        RequestObject originalRequest = RequestObject.builder()
                .toggle(true)
                .excludedToggle(false)
                .stringParam("String")
                .excludedStringParam("Excluded String")
                .innerObjects(List.of(InnerObject.builder()
                        .toggle(false)
                        .stringParam("String")
                        .intParam(4)
                        .floatParam(2f)
                        .doubleParam(7f)
                        .state(DISABLE)
                        .build()))
                .intParam(2)
                .excludedIntParam(6)
                .floatParam(4f)
                .doubleParam(5f)
                .state(ENABLE)
                .build();

        RequestObject secondaryRequest = RequestObject.builder()
                .toggle(true)
                .excludedToggle(false)
                .stringParam("String")
                .excludedStringParam("Excluded String")
                .innerObjects(List.of(InnerObject.builder()
                        .toggle(false)
                        .stringParam("String")
                        .intParam(4)
                        .floatParam(2f)
                        .doubleParam(7f)
                        .state(DISABLE)
                        .build()))
                .intParam(2)
                .excludedIntParam(6)
                .floatParam(4f)
                .doubleParam(5f)
                .state(ENABLE)
                .build();

        String originalHash = originalRequest.getCacheKey();
        String secondaryHash = secondaryRequest.getCacheKey();
        assertEquals(originalHash, secondaryHash);
    }

    @RepeatedTest(10000)
    public void getCacheKeyWithSameDataDifferenceExpected() {
        RequestObject originalRequest = RequestObject.builder()
                .toggle(true)
                .excludedToggle(false)
                .stringParam("String")
                .excludedStringParam("Excluded String")
                .innerObjects(List.of(InnerObject.builder()
                        .toggle(false)
                        .stringParam("String")
                        .intParam(4)
                        .floatParam(2f)
                        .doubleParam(7f)
                        .state(DISABLE)
                        .build()))
                .intParam(2)
                .excludedIntParam(6)
                .floatParam(4f)
                .doubleParam(5f)
                .state(ENABLE)
                .build();

        RequestObject secondaryRequest = RequestObject.builder()
                .toggle(true)
                .excludedToggle(false)
                .stringParam("String")
                .excludedStringParam("Excluded String")
                .innerObjects(List.of(InnerObject.builder()
                        .toggle(false)
                        .stringParam("String")
                        .intParam(4)
                        .floatParam(2f)
                        .doubleParam(7f)
                        .state(ENABLE)
                        .build()))
                .intParam(2)
                .excludedIntParam(6)
                .floatParam(4f)
                .doubleParam(5f)
                .state(ENABLE)
                .build();

        String originalHash = originalRequest.getCacheKey();
        String secondaryHash = secondaryRequest.getCacheKey();
        assertNotEquals(originalHash, secondaryHash);
    }

    @Test
    public void getCacheKeyWithNotSerializableInnerObjectThrowsException() {
        NotSerializableRequestObject notSerializableRequestObject = NotSerializableRequestObject.builder()
                .toggle(false)
                .stringParam("String")
                .innerObjects(List.of(NotSerializableInnerObject.builder()
                        .toggle(false)
                        .stringParam("String")
                        .intParam(4)
                        .floatParam(2f)
                        .doubleParam(7f)
                        .state(ENABLE)
                        .build()))
                .intParam(4)
                .floatParam(2f)
                .doubleParam(7f)
                .state(ENABLE)
                .build();

        assertThrows(NotSerializableException.class, notSerializableRequestObject::getCacheKey);
    }
}
