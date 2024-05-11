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
    NORMAL("成人", "对疾病抵抗力正常", 0, 0.5, 0.5, 0.5, 0.5, 0.5),
    CHILD("儿童", "抵抗里一般", 1, 0.5, 0.5, 0.5, 0.5, 0.5),
    OLD("老人", "抵抗力较差，无法接种疫苗", 2, 0.5, 0.5, 0.5, 0.5, 0.5);
    @JsonValue
    @Getter
    private final String TypeName;
    private final String TypeInfo;
    public final int CODE;
    public double chanceToGoOut;
    public double chanceOfSToEI;
    public double chanceOfEIToR;
    public double chanceOfIToD;
    public double chanceOfEToI;

    PersonType(String typeName, String typeInfo, int CODE, double chanceToGoOut, double chanceOfSToEI, double chanceOfEIToR, double chanceOfIToD, double chanceOfEToI) {
        TypeName = typeName;
        TypeInfo = typeInfo;
        this.CODE = CODE;
        this.chanceToGoOut = chanceToGoOut;
        this.chanceOfSToEI = chanceOfSToEI;
        this.chanceOfEIToR = chanceOfEIToR;
        this.chanceOfIToD = chanceOfIToD;
        this.chanceOfEToI = chanceOfEToI;
    }

    @Override
    public String toString() {
        return TypeName;
    }
}
