package Majiang;

/**
 * MajiangNumber class defines the features of a Maiiang number card.
 * A card should be able to compare (to place them in order)
 *
 */

import java.util.Objects;

public class MajiangNumber extends Majiang implements Comparable<MajiangNumber>  {
    private String color;    // color/type of cards, contains "Tiao", "Bing", "Wan", "Feng"
    private int number;    // number of each color of cards, contains 1~9
    private Integer index;     // index of each card in 4 packs of cards to differ from each other

    // constructor without paras
    public MajiangNumber() { }

    public MajiangNumber(String color, int num,Integer index) {
        this.color = color;
        this.number = num;
        this.index = index;
    }


    // redefine the compare rules to sort cards
    @Override
    public int compareTo(MajiangNumber o) {
        return this.index - o.index;
    }



    // print the cards out
    @Override
    public String toString() {
        return number +  "-" + color;
    }

    // check if the cards are the same
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MajiangNumber majiang = (MajiangNumber) o;
        return Objects.equals(color, majiang.color) &&
                Objects.equals(number, majiang.number) &&
                Objects.equals(index, majiang.index);
    }

    // rewrite the hash code
    @Override
    public int hashCode() {
        return Objects.hash(color, number, index);
    }



// setter and getter
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int num) {
        this.number = num;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

}


