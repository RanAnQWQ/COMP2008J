package Majiang;

/**
 *
 * @author Qiyue Zhu
 * types of Feng：including East Feng, South Feng, West Feng, North Feng, Zhong, Fa Cai, Bai Ban：4、5、6、7、8、9、10
 */
public class MajiangFeng extends Majiang{
    @Override
    public String toString() {
        String maJiangWindStr="[";
        switch (this.getType()) {
            case 4:
                maJiangWindStr+="东风";
                break;
            case 5:
                maJiangWindStr+="南风";
                break;
            case 6:
                maJiangWindStr+="西风";
                break;
            case 7:
                maJiangWindStr+="北风";
                break;
            case 8:
                maJiangWindStr+="红中";
                break;
            case 9:
                maJiangWindStr+="发财";
                break;
            case 10:
                maJiangWindStr+="白板";
                break;
        }
        maJiangWindStr+="]";
        return maJiangWindStr;
    }
}