package Majiang;

/**
 *
 * @author Administrator
 *  The parent of all cards
 */
public class Majiang {
    /**
     * Type：万、条、筒： 1  2  3
     *      东、南、西、北、中、发、白：4、5、6、7、8、9、10
     */
    private int type;
    
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "MaJiang [type=" + type + "]";
    }

}