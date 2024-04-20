package com.coco.celldata;

import com.coco.utils.PersonStatus;
import com.coco.utils.PersonType;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
public class Person {
    @Setter
    private PersonType personType;
    @Setter
    private PersonStatus status;

    @Override
    public String toString() {
        return "Person{" +
                "personType=" + personType +
                ", status=" + status +
                ", time=" + time +
                '}';
    }

    /**
     * 标记一个人自从上次状态被改变之后已经经过了多少轮
     * （可能没有用
     */
    @Setter
    private int time = 0;

    public Person(PersonType personType, PersonStatus status) {
        this.personType = personType;
        this.status = status;
    }
    public void addTime(){
        time++;
    }
    public void resetTime(){
        time=0;
    }
}
