package com.coco.celldata;

import com.coco.utils.LocationType;
import com.coco.utils.PersonStatus;
import com.coco.utils.PersonType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Random;

/**
 * ClassName:CovidCell
 * Package:com.coco.celldata
 * Description:
 *
 * @Author Coco
 * @Create 2024/3/26 13:09
 * @Version 1.0
 */
@NoArgsConstructor
public class CovidCell extends Cell {
    /**
     * TODO 不要每个Cell都new一个random
     */
    private final Random random = new Random();
    /**
     * 所有被标记为公共场所的地点都被储存在这里
     */
    public static ArrayList<CovidCell> plazas = new ArrayList<>();

    /**
     * 用于标识这个细胞代表什么场所
     */
    @Setter
    @Getter
    private LocationType location;
    /**
     * 一个场所里的人员列表。住宅里的人员是固定的，而过道和公共场所里的人员则是通过每天运算得出
     */
    @Getter
    @Setter
    private ArrayList<Person> people = new ArrayList<>();

    public CovidCell(int x, int y, LocationType location) {
        super(x, y);
        this.location = location;
    }

    public CovidCell(int x, int y, LocationType location, ArrayList<Person> people) {
        super(x, y);
        this.location = location;
        this.people = people;
    }

    /**
     * 创建一个坐标为(x,y)的细胞
     *
     * @param x 横坐标
     * @param y 纵坐标
     */
    public CovidCell(int x, int y) {
        super(x, y);
        this.location = LocationType.HOUSE;
    }

    /**
     * TODO 先获取公共场所人员列表(放在before里)
     *      随后分别计算两边的感染情况
     */
    @Override
    public boolean cellStrategy() {
        if (location == LocationType.HOUSE) {
            //存在感染者
            //家庭中的感染暂定为每天40%概率
            if (getStatus() == 3) {
                for (Person person : people) {
                    if (random.nextDouble() < person.getPersonType().chanceOfSToEI) {
                        if (person.getStatus() == PersonStatus.S) {
                            person.setStatus(PersonStatus.E);
                        }
                    }
                }
            }
        }
        if (location == LocationType.AISLE) {
            //存在感染者
            //过道中的感染暂定为每天5%概率
            if (getStatus() == 3) {
                for (Person person : people) {
                    if (random.nextDouble() < person.getPersonType().chanceOfSToEI) {
                        if (person.getStatus() == PersonStatus.S) {
                            person.setStatus(PersonStatus.E);
                        }
                    }
                }
            }
        }
        if (location == LocationType.PLAZA) {
            //存在感染者
            //公共场合中的感染暂定为每天10%概率
            if (getStatus() == 3) {
                for (Person person : people) {
                    if (random.nextDouble() < person.getPersonType().chanceOfSToEI) {
                        if (person.getStatus() == PersonStatus.S) {
                            person.setStatus(PersonStatus.E);
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "CovidCell{" +
                "location=" + location +
                ", people=" + people +
                ", x=" + x +
                ", y=" + y +
                ", status=" + status +
                '}';
    }

    /**
     * 计算公共场所人员列表,这一步不产生影响界面显示的改变
     */
    @Override
    public void beforeRoundStrategy() {
        for (int x = 0; x < field.getWidth(); x++) {
            for (int y = 0; y < field.getHeight(); y++) {
                if (((CovidCell) field.getTempCell(x, y)).getLocation() == LocationType.HOUSE) {
                    CovidCell aisle = searchAisle();
                    ArrayList<Person> list = ((CovidCell) field.getTempCell(x, y)).getPeople();
                    for (Person person : list) {
                        //假设每个人有一定的机率出门去某个广场
                        // TODO 这里的机率应该是可修改的，并且和人有关
                        if (random.nextDouble() < person.getPersonType().chanceToGoOut) {
                            assert aisle != null;
                            aisle.getPeople().add(person);
                            if (!plazas.isEmpty()) {
                                CovidCell plaza = plazas.get(random.nextInt(0, plazas.size()));
                                plaza.getPeople().add(person);
                            }
                            else {
                                System.out.println("plazas为空");
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 清除所有公共场合的人员列表
     * 判定感染者的演化状态
     * TODO 区别不同类型的人的状态转化机率
     */
    @Override
    public void afterRoundStrategy() {
        for (int x = 0; x < field.getWidth(); x++) {
            for (int y = 0; y < field.getHeight(); y++) {
                ArrayList<Person> list = ((CovidCell) field.getTempCell(x, y)).getPeople();
                if (((CovidCell) field.getTempCell(x,y)).location != LocationType.HOUSE) {
                    list.clear();
                }
                if (((CovidCell) field.getTempCell(x,y)).location == LocationType.HOUSE) {
                    for (Person person : list) {
                        if (person.getStatus() == PersonStatus.E) {
                            double p = random.nextDouble();
                            //以某个概率转为有症状的感染者,另一个概率痊愈
                            if (p < person.getPersonType().chanceOfEToI) {
                                person.setStatus(PersonStatus.I);
                            }
                            if (p < person.getPersonType().chanceOfEIToR) {
                                person.setStatus(PersonStatus.R);
                            }
                        }
                        if (person.getStatus() == PersonStatus.I) {
                            //以一定概率的概率转为痊愈者或死者
                            if (random.nextDouble() < person.getPersonType().chanceOfEIToR) {
                                person.setStatus(PersonStatus.R);
                            } else if (random.nextDouble() < person.getPersonType().chanceOfIToD) {
                                person.setStatus(PersonStatus.D);
                            }
                        }
                    }
                }
            }
        }
    }


    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void randomIni() {

    }

    //
    @Override
    public void copy(Cell tempCell) {
        CovidCell covidCell = (CovidCell) tempCell;
        this.setLocation(covidCell.getLocation());
        this.getPeople().clear();
        this.getPeople().addAll(covidCell.getPeople());
    }

    /**
     * 暂定混合为3，只有感染者为2，没有感染者为1
     *
     * @return 状态
     */
    @JsonIgnore
    @Override
    public int getStatus() {
        int status = 0;
        for (Person person : people) {
            if (person.getStatus() == PersonStatus.S || person.getStatus() == PersonStatus.R) {
                status += 1;
                break;
            }
        }
        for (Person person : people) {
            if (person.getStatus() == PersonStatus.E || person.getStatus() == PersonStatus.I) {
                status += 2;
                break;
            }
        }
        return status;
    }

    /**
     * 根据参数设置各属性，设置location并预设家庭。
     * 每位数代表一个属性。第一位是location，第二位是预设家庭情况。
     * 注意：setStatus和getStatus里的代码并不相同
     * location{0：住宅，1：过道，2：广场}
     * home{0:清空，1:独居青年，2：恋人同居，3：三口之家，4：老人小孩，5：留守老人，6：四世同堂，7：不变}
     *
     * @param status 状态代码
     */
    @Override
    public void setStatus(int status) {
        if (status % 10 == 0) {
            setLocation(LocationType.HOUSE);
        }
        if (status % 10 == 1) {
            setLocation(LocationType.AISLE);
        }
        if (status % 10 == 2) {
            setLocation(LocationType.PLAZA);
        }
        if (status / 10 == 0) {
            people.clear();
        }
        if (status / 10 == 1) {
            people.clear();
            addPerson(new Person(PersonType.NORMAL, PersonStatus.S));
        }
        if (status / 10 == 2) {
            people.clear();
            addPerson(new Person(PersonType.NORMAL, PersonStatus.S));
            addPerson(new Person(PersonType.NORMAL, PersonStatus.S));
        }
        if (status / 10 == 3) {
            people.clear();
            addPerson(new Person(PersonType.NORMAL, PersonStatus.S));
            addPerson(new Person(PersonType.NORMAL, PersonStatus.S));
            addPerson(new Person(PersonType.CHILD, PersonStatus.S));
        }
        if (status / 10 == 4) {
            people.clear();
            addPerson(new Person(PersonType.OLD, PersonStatus.S));
            addPerson(new Person(PersonType.CHILD, PersonStatus.S));
        }
        if (status / 10 == 5) {
            people.clear();
            addPerson(new Person(PersonType.OLD, PersonStatus.S));
            addPerson(new Person(PersonType.OLD, PersonStatus.S));

        }
        if (status / 10 == 6) {
            people.clear();
            addPerson(new Person(PersonType.NORMAL, PersonStatus.S));
            addPerson(new Person(PersonType.CHILD, PersonStatus.S));
            addPerson(new Person(PersonType.NORMAL, PersonStatus.S));
            addPerson(new Person(PersonType.OLD, PersonStatus.I));
        }
    }

    /**
     * 当一个Person需要前往广场时，调用此方法获取他经过过的过道
     * TODO 设定一个搜索半径最大值，如果没搜索到，抛出异常并给出提示(坐标为xx的住宅在指定范围内不存在过道，请重新编辑地图)
     *     （希望每个住宅都有很好的过道来让人走
     *
     * @return 目标过道
     */
    private CovidCell searchAisle() {
        for (int n = 1; n < field.getWidth(); n++) {
            for (int x = this.x - n; x <= this.x + n; x++) {
                for (int y = this.y - n; y <= this.y + n; y++) {
                    if (x < 0 || x >= field.getWidth() || y < 0 || y >= field.getHeight()) {
                        continue;
                    }
                    if (!(x > this.x - n && x < this.x + n) && (y > this.y - n && y < this.y + n)) {
                        Cell cell = field.getCell(x, y);
                        if (cell instanceof CovidCell covidCell) {
                            if (covidCell.location == LocationType.AISLE) {
                                return covidCell;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    public void addPerson(Person person) {
        this.people.add(person);
    }
}
