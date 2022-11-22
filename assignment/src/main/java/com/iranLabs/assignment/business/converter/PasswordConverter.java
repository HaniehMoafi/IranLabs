package com.iranLabs.assignment.business.converter;


import com.iranLabs.assignment.util.EncryptionUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @Author : Hanieh Moafi
 * @Date : 10/28/2022
 */
@Converter
public class PasswordConverter implements AttributeConverter<String, String> {


    @Override
    public String convertToDatabaseColumn(String s) {
        return EncryptionUtils.encrypt(s);
    }

    @Override
    public String convertToEntityAttribute(String s) {
        return EncryptionUtils.decrypt(s);
    }
}
