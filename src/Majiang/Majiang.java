package Majiang;

/**
 * Majiang class is the parent of all cards
 * this define the basic features of a Majiang card
 * @author Qiyue Zhu
 */

public class Majiang {
    /**
     * Type：Wan, Tiao, Bing： 1  2  3
     *      East Feng, South Feng, West Feng, North Feng, Zhong, Fa Cai, Bai Ban：4、5、6、7、8、9、10
     */
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    // to delete............................................................................
    @Override
    public String toString() {
        return "MaJiang [type=" + type + "]";
    }

}