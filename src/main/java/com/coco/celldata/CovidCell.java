package com.coco.celldata;

import com.coco.utils.LocationType;
import com.coco.utils.PersonStatus;
import com.coco.utils.PersonType;
import lombok.Getter;
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
public class CovidCell extends Cell {
    /**
     * TODO 不要每个Cell都new一个random
     */
    private final Random random = new Random();
    /**
     * 所有被标记为公共场所的地点都被储存在这里
     */
    private static final ArrayList<CovidCell> plazas = new ArrayList<>();
    /**
     * 用于标识这个细胞代表什么场所
     */
    @Getter
    @Setter
    private LocationType location;
    /**
     * 一个场所里的人员列表。住宅里的人员是固定的，而过道和公共场所里的人员则是通过每天运算得出
     */
    @Getter
    private final ArrayList<Person> people = new ArrayList<>();

    public CovidCell(int x, int y, LocationType location) {
        super(x, y);
        this.location = location;
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
            if (getStatus() == 1) {
                for (Person person : people) {
                    if (random.nextDouble() < 0.4) {
                        person.setStatus(PersonStatus.E);
                    }
                }
            }
        }
        if (location == LocationType.AISLE) {
            //存在感染者
            //过道中的感染暂定为每天5%概率
            if (getStatus() == 1) {
                for (Person person : people) {
                    if (random.nextDouble() < 0.05) {
                        person.setStatus(PersonStatus.E);
                    }
                }
            }
        }
        if (location == LocationType.PLAZA) {
            //存在感染者
            //公共场合中的感染暂定为每天10%概率
            if (getStatus() == 1) {
                for (Person person : people) {
                    if (random.nextDouble() < 0.1) {
                        person.setStatus(PersonStatus.E);
                    }
                }
            }
        }
        return false;
    }

    /**
     * 计算公共场所人员列表,这一步不产生影响界面显示的改变
     */
    @Override
    public void beforeRoundStrategy() {
        for (int x = 0; x < field.getWidth(); x++) {
            for (int y = 0; y < field.getHeight(); y++) {
                if (location == LocationType.HOUSE) {
                    CovidCell aisle = searchAisle();
                    ArrayList<Person> list = ((CovidCell) field.getTempCell(x, y)).getPeople();
                    for (Person person : list) {
                        //假设每个人有0.2的机率出门去某个广场
                        if (random.nextDouble() < 0.2) {
                            assert aisle != null;
                            aisle.getPeople().add(person);
                            CovidCell plaza = plazas.get(random.nextInt(0, plazas.size()));
                            plaza.getPeople().add(person);
                        }
                    }
                }
            }
        }
    }

    /**
     * TODO 清除所有公共场合的人员列表
     */
    @Override
    public void afterRoundStrategy() {
        for (int x = 0; x < field.getWidth(); x++) {
            for (int y = 0; y < field.getHeight(); y++) {
                if (location != LocationType.HOUSE) {
                    ArrayList<Person> list = ((CovidCell) field.getTempCell(x, y)).getPeople();
                    list.clear();
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
        this.getPeople().clear();
        this.getPeople().addAll(covidCell.getPeople());
    }

    /**
     * 暂定混合为3，只有感染者为2，没有感染者为1
     *
     * @return 状态
     */
    @Override
    public int getStatus() {
        int status = 0;
        for (Person person : people) {
            if (person.getStatus() == PersonStatus.S || person.getStatus() == PersonStatus.R) {
                status++;
                break;
            }
        }
        for (Person person : people) {
            if (person.getStatus() == PersonStatus.E || person.getStatus() == PersonStatus.I) {
                status++;
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
     * home{0:无，1:独居青年，2：恋人同居，3：三口之家，4：老人小孩，5：留守老人，6：四世同堂}
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
        if (status / 10 == 1) {
            addPerson(new Person(PersonType.NORMAL, PersonStatus.S));
        }
        if (status / 10 == 2) {
            addPerson(new Person(PersonType.NORMAL, PersonStatus.S));
            addPerson(new Person(PersonType.NORMAL, PersonStatus.S));
        }
        if (status / 10 == 3) {
            addPerson(new Person(PersonType.NORMAL, PersonStatus.S));
            addPerson(new Person(PersonType.NORMAL, PersonStatus.S));
            addPerson(new Person(PersonType.CHILD, PersonStatus.S));
        }
        if (status / 10 == 4) {
            addPerson(new Person(PersonType.OLD, PersonStatus.S));
            addPerson(new Person(PersonType.CHILD, PersonStatus.S));
        }
        if (status / 10 == 5) {
            addPerson(new Person(PersonType.OLD, PersonStatus.S));
            addPerson(new Person(PersonType.OLD, PersonStatus.S));

        }
        if (status / 10 == 6) {
            addPerson(new Person(PersonType.NORMAL, PersonStatus.S));
            addPerson(new Person(PersonType.CHILD, PersonStatus.S));
            addPerson(new Person(PersonType.NORMAL, PersonStatus.S));
            addPerson(new Person(PersonType.OLD, PersonStatus.S));
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
            for (int x = -n; x < n; x++) {
                for (int y = -n; y < n; y++) {
                    if (!(x >= -(n - 1) && x <= (n - 1)) && (y >= -(n - 1) && y <= (n - 1))) {
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
