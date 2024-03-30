package com.coco.utils;

import lombok.AllArgsConstructor;

import javax.naming.Name;

/**
 * ClassName:PersonStatus
 * Package:com.coco.utils
 * Description:
 *
 * @Author Coco
 * @Create 2024/3/27 14:51
 * @Version 1.0
 */
@AllArgsConstructor
public enum PersonStatus {
    S("S",1),
    E("E",2),
    I("I",3),
    R("R",4);
    private final String NAME;
    public final Integer CODE;

    @Override
    public String toString() {
        return NAME;
    }
}
