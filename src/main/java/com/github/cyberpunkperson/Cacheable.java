package com.github.cyberpunkperson;

import com.google.common.hash.Hashing;
import lombok.SneakyThrows;

import java.io.Serializable;
import java.security.MessageDigest;
import java.util.Collection;

import static com.github.cyberpunkperson.utils.FieldUtils.getFieldValue;
import static com.github.cyberpunkperson.utils.ObjectUtils.toByteArray;
import static org.junit.platform.commons.util.ReflectionUtils.makeAccessible;
import static org.springframework.util.ReflectionUtils.doWithFields;

public interface Cacheable extends Serializable {

    @SneakyThrows
    default String getCacheKey() {

        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        doWithFields(this.getClass(), field -> {
            makeAccessible(field);

            if (!field.isAnnotationPresent(CacheKey.Exclude.class)) {
                Object object = getFieldValue(field, this);
                updateDigest(messageDigest, object);
            }
        });

        return Hashing.sha256()
                .hashBytes(messageDigest.digest())
                .toString();
    }

    default void updateDigest(MessageDigest messageDigest, Object inputObject) {

        if (inputObject instanceof Collection) {
            ((Collection<?>) inputObject).forEach(object -> updateDigest(messageDigest, object));
        } else {
            messageDigest.update(toByteArray(inputObject));
        }
    }

}
