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
    NORMAL("成人", "对疾病抵抗力正常", 0, 0.2, 0.3, 0.07, 0.01, 0.2),
    CHILD("儿童", "抵抗里一般", 1, 0.2, 0.3, 0.07, 0.01, 0.2),
    OLD("老人", "抵抗力较差，无法接种疫苗", 2, 0.2, 0.3, 0.07, 0.01, 0.2),
    NURSE("医护人员", "不受封控影响，全副武装", 3, 0.5, 0.3, 0.2, 0, 0.2);
    @JsonValue
    @Getter
    private final String TypeName;
    private final String TypeInfo;
    public final int CODE;
    public double chanceToGoOut;
    public double chanceOfSToEI;
    public double chanceOfIToR;
    public double chanceOfIToD;
    public double chanceOfEToI;

    PersonType(String typeName, String typeInfo, int CODE, double chanceToGoOut, double chanceOfSToEI, double chanceOfIToR, double chanceOfIToD, double chanceOfEToI) {
        TypeName = typeName;
        TypeInfo = typeInfo;
        this.CODE = CODE;
        this.chanceToGoOut = chanceToGoOut;
        this.chanceOfSToEI = chanceOfSToEI;
        this.chanceOfIToR = chanceOfIToR;
        this.chanceOfIToD = chanceOfIToD;
        this.chanceOfEToI = chanceOfEToI;
    }

    @Override
    public String toString() {
        return TypeName;
    }
}
