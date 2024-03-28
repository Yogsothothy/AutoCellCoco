package com.coco.celldata;

import com.coco.utils.PersonStatus;
import com.coco.utils.PersonType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * ClassName:Person
 * Package:com.coco.celldata
 * Description:
 *
 * @Author Coco
 * @Create 2024/3/26 23:46
 * @Version 1.0
 */
@Getter
@AllArgsConstructor
public class Person {
    private final PersonType personType;
    @Setter
    private PersonStatus status;
}
