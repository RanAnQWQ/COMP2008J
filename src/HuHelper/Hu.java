package HuHelper;

import java.util.*;

public class Hu {
    public static boolean isWinningType(ArrayList<Integer> hand, ArrayList<Integer> HuTiles ) {
        // Implement your winning hand logic here
        // For simplicity, let's assume a winning hand is a hand with 14 tiles
        return hand.size()+ HuTiles.size() >= 14;
    }


    private static ArrayList<Integer> getPairs(ArrayList<Integer> hand) {
        ArrayList<Integer> res=new ArrayList<>();
        if (null != hand && hand.size() > 1) {
            for (int i = 0; i < hand.size() - 1; i++) {
                if (hand.get(i) == hand.get(i+1)) {
                    res.add(hand.get(i));
                    i++;
                }
            }
        }
        return res;

    }

    // Method to count the number of triplets in the hand

    private static int countTriplets(ArrayList<Integer> temp) {
        int tripletCount = 0;
        Iterator<Integer> iterator = temp.iterator();

        while (iterator.hasNext()) {
            Integer tile = iterator.next();
            int count = countTiles(temp, tile);

            if (count >= 3) {
                int removals = count >= 4 ? 4 : 3;  // Remove 4 if there are quadruplets, otherwise remove 3
                tripletCount += removals / 3;       // Increment by 1 for triplet, potentially by another 1 for quadruplet
                for (int i = 0; i < removals; i++) {
                    iterator.remove();             // Remove the current element safely
                    if (i < removals - 1) {        // If not the last iteration, move to next element
                        iterator.next();
                    }
                }
                if (iterator.hasNext()) {          // Move to next element if exists, or iterator will automatically end
                    iterator.next();
                }
            }
        }
        return tripletCount;
    }


    // Method to count the number of sequences in the hand
    private static int countSequences(ArrayList<Integer> temp) {
        Collections.sort(temp);
        int sequenceCount = 0;
        //remove Feng tiles
        int[] feng={41,42,43,44,45,47,47};
        for (Integer i:feng){
            if(temp.contains(i)){
                temp.remove(i);
            }
        }
        for (Integer i = 0; i < temp.size() - 2; i++) {
            if (temp.get(i) + 1 == temp.get(i+1)&& temp.get(i+1)+ 1 == temp.get(i+2)) {
                sequenceCount++;
                i += 2; // Skip the next two tiles as they're part of the sequence
            }
        }
        return sequenceCount;
    }

    // Method to count the occurrences of a specific tile in the hand
    private static int countTiles(ArrayList<Integer> hand, int tile) {
        int count = 0;
        for (int t : hand) {
            if (t == tile) {
                count++;
            }
        }
        return count;
    }

    public static boolean isHu(ArrayList<Integer> hand,ArrayList<Integer> HuTiles,int Chi,int Peng,int Gang){
        if(isWinningType(hand,HuTiles)) {
            ArrayList<Integer> js = getPairs(hand);
            if (null == js || js.size() <= 0) {
                return false;
            }else{
                for (Integer j : js) {
                    ArrayList<Integer> tempCards = (ArrayList<Integer>) hand.clone();
                    tempCards.remove(j);
                    tempCards.remove(j);
                    Collections.sort(tempCards);
                    int triplets = countTriplets(tempCards);
                    int sequence= countSequences(tempCards);
                    if (triplets + sequence+ Chi+ Peng+ Gang == 4) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
