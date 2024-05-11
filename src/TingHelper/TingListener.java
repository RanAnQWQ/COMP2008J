package TingHelper;

import HuHelper.Hu;
import Player.Movement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TingListener {

    Hu hu=new Hu();
    public static HashMap<Integer,ArrayList<Integer>> pairs=new HashMap<>();

    public  boolean isTing(ArrayList<Integer> currenthand, ArrayList<Integer> HuTiles, int Chi, int Peng, int Gang){
        Throw_NeedPairs(currenthand,HuTiles,Chi,Peng,Gang);
        if(!pairs.isEmpty()){
            return true;
        }
        return false;
    }
    public void Throw_NeedPairs(ArrayList<Integer> currenthand, ArrayList<Integer> HuTiles, int Chi, int Peng, int Gang){
        Movement movement=new Movement(currenthand);
        ArrayList<Integer> hand = (ArrayList<Integer>) currenthand.clone();
        if(hand.size()+HuTiles.size()>=14&&!hu.isHu(hand,HuTiles,Chi,Peng,Gang)){
            for(Integer tile:hand){
                hand.remove(tile);
                ArrayList<Integer> TingTiles=new ArrayList();
                for(int i=11;i<=19;i++){
                    ArrayList<Integer> tempCards = (ArrayList<Integer>) hand.clone();
                    tempCards.add(i);
                    movement.Chi(i);
                    movement.Gang(i);
                    movement.Peng(i);
                    if(hu.isHu(tempCards,HuTiles,Chi,Peng,Gang)){
                        TingTiles.add(i);
                    }
                }
                for(int i=21;i<=29;i++){
                    ArrayList<Integer> tempCards = (ArrayList<Integer>) hand.clone();
                    tempCards.add(i);
                    movement.Chi(i);
                    movement.Gang(i);
                    movement.Peng(i);
                    if(hu.isHu(tempCards,HuTiles,Chi,Peng,Gang)){
                        TingTiles.add(i);
                    }
                }
                for(int i=31;i<=39;i++){
                    ArrayList<Integer> tempCards = (ArrayList<Integer>) hand.clone();
                    tempCards.add(i);
                    movement.Chi(i);
                    movement.Gang(i);
                    movement.Peng(i);
                    if(hu.isHu(tempCards,HuTiles,Chi,Peng,Gang)){
                        TingTiles.add(i);
                    }
                }
                for(int i=41;i<=47;i++){
                    ArrayList<Integer> tempCards = (ArrayList<Integer>) hand.clone();
                    tempCards.add(i);
                    movement.Chi(i);
                    movement.Gang(i);
                    movement.Peng(i);
                    if(hu.isHu(tempCards,HuTiles,Chi,Peng,Gang)){
                        TingTiles.add(i);
                    }
                }
                pairs.put(tile,TingTiles);
            }
        }
    }
    public  HashMap<Integer,ArrayList<Integer>> getPairs(){
        return pairs;
    }
}