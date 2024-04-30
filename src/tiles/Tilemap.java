package tiles;
import java.util.HashMap;
public class Tilemap {
    private HashMap<Integer,String> tilemap;
    public Tilemap() {
        tilemap = new HashMap<>();
        tilemap.put(11,"src/tiles/11-19_tiao/1tiao.png");
        tilemap.put(12,"src/tiles/11-19_tiao/2tiao.png");
        tilemap.put(13,"src/tiles/11-19_tiao/3tiao.png");
        tilemap.put(14,"src/tiles/11-19_tiao/4tiao.png");
        tilemap.put(15,"src/tiles/11-19_tiao/5tiao.png");
        tilemap.put(16,"src/tiles/11-19_tiao/6tiao.png");
        tilemap.put(17,"src/tiles/11-19_tiao/7tiao.png");
        tilemap.put(18,"src/tiles/11-19_tiao/8tiao.png");
        tilemap.put(19,"src/tiles/11-19_tiao/9tiao.png");

        tilemap.put(21,"src/tiles/21-29_bing/1bing.png");
        tilemap.put(22,"src/tiles/21-29_bing/2bing.png");
        tilemap.put(23,"src/tiles/21-29_bing/3bing.png");
        tilemap.put(24,"src/tiles/21-29_bing/4bing.png");
        tilemap.put(25,"src/tiles/21-29_bing/5bing.png");
        tilemap.put(26,"src/tiles/21-29_bing/6bing.png");
        tilemap.put(27,"src/tiles/21-29_bing/7bing.png");
        tilemap.put(28,"src/tiles/21-29_bing/8bing.png");
        tilemap.put(29,"src/tiles/21-29_bing/9bing.png");

        tilemap.put(31,"src/tiles/31-39_wan/1wan.png");
        tilemap.put(32,"src/tiles/31-39_wan/2wan.png");
        tilemap.put(33,"src/tiles/31-39_wan/3wan.png");
        tilemap.put(34,"src/tiles/31-39_wan/4wan.png");
        tilemap.put(35,"src/tiles/31-39_wan/5wan.png");
        tilemap.put(36,"src/tiles/31-39_wan/6wan.png");
        tilemap.put(37,"src/tiles/31-39_wan/7wan.png");
        tilemap.put(38,"src/tiles/31-39_wan/8wan.png");
        tilemap.put(39,"src/tiles/31-39_wan/9wan.png");

        tilemap.put(41,"src/tiles/41-47_zi/41esat.png");
        tilemap.put(42,"src/tiles/41-47_zi/42west.png");
        tilemap.put(43,"src/tiles/41-47_zi/43south.png");
        tilemap.put(44,"src/tiles/41-47_zi/44north.png");
        tilemap.put(45,"src/tiles/41-47_zi/45center.png");
        tilemap.put(46,"src/tiles/41-47_zi/46fa.png");
        tilemap.put(47,"src/tiles/41-47_zi/47blank.png");
    }

    public String getTilePath(int tileNum) {
        return String.valueOf(tilemap.get(tileNum));
    }


}
