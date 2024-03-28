package com.coco.utils;

import lombok.AllArgsConstructor;
import lombok.ToString;

/**
 * ClassName:LocationType
 * Package:com.coco.utils
 * Description:
 *
 * @Author Coco
 * @Create 2024/3/27 14:37
 * @Version 1.0
 */
@ToString
@AllArgsConstructor
public enum LocationType {
    HOUSE("住宅","人们居住于此",0),
    AISLE("过道","人们去往公共场所时必须经过至少一个过道",1),
    PLAZA("广场","人们聚集于此",2);
    private final String TYPENAME;
    private final String TYPEINFO;
    public final Integer CODE;
}
