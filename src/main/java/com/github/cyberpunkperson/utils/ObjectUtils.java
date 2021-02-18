package com.github.cyberpunkperson.utils;

import lombok.SneakyThrows;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ObjectUtils {

    @SneakyThrows(IOException.class)
    public static byte[] toByteArray(Object object) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(baos)) {

            oos.writeObject(object);
            return baos.toByteArray();
        }
    }
}
