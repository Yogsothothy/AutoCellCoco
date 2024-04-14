package com.coco.utils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

import java.util.Objects;

/**
 * ClassName:LocationType
 * Package:com.coco.utils
 * Description:
 *
 * @Author Coco
 * @Create 2024/3/27 14:37
 * @Version 1.0
 */
@AllArgsConstructor
public enum LocationType {
    HOUSE("住宅", "人们居住于此", 0),
    AISLE("过道", "人们去往公共场所时必须经过至少一个过道", 1),
    PLAZA("广场", "人们聚集于此", 2);
    private final String TYPENAME;
    private final String TYPEINFO;
    public final Integer CODE;

    @JsonValue
    public Integer getCODE() {
        return CODE;
    }

    @Override
    public String toString() {
        return TYPENAME;
    }

    @JsonCreator
    public static LocationType fromValue(Integer CODE) {
        for (LocationType location : LocationType.values()) {
            if (Objects.equals(location.CODE,CODE)){
                return location;
            }
        }
        return null;
    }

}
