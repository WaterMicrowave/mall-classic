package com.hlkj.mallclassic.convert;

import com.fasterxml.jackson.core.type.TypeReference;
import com.hlkj.mallclassic.model.Specs;
import com.hlkj.mallclassic.utils.JacksonUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Address实体和String字符串转换
 */
@Converter(autoApply = true)
public class SpecsConverter implements AttributeConverter<Specs[], String> {

    @Override
    public String convertToDatabaseColumn(Specs[] specs) {
        String s = JacksonUtils.toJSon(specs);
        return s;
    }

    @Override
    public Specs[] convertToEntityAttribute(String s) {
        Specs[] specs = JacksonUtils.readValue(s, new TypeReference<Specs[]>() {
        });
        return specs;
    }
}