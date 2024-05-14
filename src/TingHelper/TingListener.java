package TingHelper;

import HuHelper.Hu;
import Player.Player;
import Player.Movement;

import java.util.ArrayList;
import java.util.HashMap;

public class TingListener {

    Hu hu=new Hu();
    public boolean ting=false;

    public boolean getTing() {
        return ting;
    }

    public static HashMap<Integer,ArrayList<Integer>> pairs=new HashMap<>();
    public ArrayList<Integer> TingTiles=new ArrayList();
    public  boolean isTing(Player player){
        Throw_NeedPairs(player);
        if(!pairs.isEmpty()){
            ting=true;
            return true;
        }
        return false;
    }


    public void Throw_NeedPairs(Player player){;
        ArrayList<Integer> hand = (ArrayList<Integer>) player.getPlayerMajiangs().clone();
        ArrayList<Integer> HuTiles = (ArrayList<Integer>) player.getCardsToDisplay().clone();
        int chi=player.ChiNumber;
        int peng=player.PengNumber;
        int gang=player.GangNumber;
        if(hand.size()+HuTiles.size()>=14&&!hu.isHu(hand,HuTiles,chi,peng,gang)){
            Movement movement=new Movement(hand);
            for(Integer tile:hand){
                hand.remove(tile);

                for(int i=11;i<=19;i++){
                    hand.add(i);
                    if(movement.isChi(i)){
                        chi++;
                    }
                    if(movement.Peng(i)){
                        peng++;
                    }
                    if(movement.Gang(i)){
                        gang++;
                    }
                    if(hu.isHu(hand,HuTiles,player.ChiNumber,player.PengNumber,player.GangNumber)){
                        TingTiles.add(i);
                    }
                    hand.remove(i);
                }
                for(int i=21;i<=29;i++){
                    if(movement.isChi(i)){
                        chi++;
                    }
                    if(movement.Peng(i)){
                        peng++;
                    }
                    if(movement.Gang(i)){
                        gang++;
                    }
                    if(hu.isHu(hand,HuTiles,player.ChiNumber,player.PengNumber,player.GangNumber)){
                        TingTiles.add(i);
                    }
                    hand.remove(i);
                }
                for(int i=31;i<=39;i++){
                    if(movement.isChi(i)){
                        chi++;
                    }
                    if(movement.Peng(i)){
                        peng++;
                    }
                    if(movement.Gang(i)){
                        gang++;
                    }
                    if(hu.isHu(hand,HuTiles,player.ChiNumber,player.PengNumber,player.GangNumber)){
                        TingTiles.add(i);
                    }
                    hand.remove(i);
                }
                for(int i=41;i<=47;i++){
                    if(movement.isChi(i)){
                        chi++;
                    }
                    if(movement.Peng(i)){
                        peng++;
                    }
                    if(movement.Gang(i)){
                        gang++;
                    }
                    if(hu.isHu(hand,HuTiles,player.ChiNumber,player.PengNumber,player.GangNumber)){
                        TingTiles.add(i);
                    }
                    hand.remove(i);
                }
                pairs.put(tile,TingTiles);

            }
        }
    }

    public  HashMap<Integer,ArrayList<Integer>> getPairs(){
        return pairs;
    }
    public  ArrayList<Integer> getTiles(){
        return TingTiles;
    }
}