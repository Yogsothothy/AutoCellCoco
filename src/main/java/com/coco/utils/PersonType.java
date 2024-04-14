package com.coco.utils;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * ClassName:PersonType
 * Package:com.coco.utils
 * Description:
 *
 * @Author Coco
 * @Create 2024/3/27 14:31
 * @Version 1.0
 */
public enum PersonType {
    NORMAL("成人","对疾病抵抗力正常"),
    CHILD("儿童","抵抗里一般"),
    OLD("老人","抵抗力较差，无法接种疫苗");
    @JsonValue
    @Getter
    private final String TypeName;
    private final String TypeInfo;

    PersonType(String typeName, String typeInfo) {
        TypeName = typeName;
        TypeInfo = typeInfo;
    }

    @Override
    public String toString() {
        return "PersonType{" +
                "TypeName='" + TypeName + '\'' +
                ", TypeInfo='" + TypeInfo + '\'' +
                '}';
    }
}
