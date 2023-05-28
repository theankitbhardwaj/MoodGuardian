package com.bhardwaj.MoodGuardian.utils;

import com.bhardwaj.MoodGuardian.model.EMood;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EMoodConverter implements Converter<String, EMood> {

    @Override
    public EMood convert(String value) {
        try {
            return EMood.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
