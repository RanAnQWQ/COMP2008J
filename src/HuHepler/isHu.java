package HuHepler;
import tiles.Tilemap;
import java.util.Arrays;

public class isHu {
    public static boolean isWinningType(int[] hand) {
        // Implement your winning hand logic here
        // For simplicity, let's assume a winning hand is a hand with 14 tiles
        return hand.length == 14;
    }


    private static int countPairs(int[] hand) {
        int pairCount = 0;
        for (int i = 0; i < hand.length - 1; i++) {
            if (hand[i] == hand[i + 1]) {
                pairCount++;
                i++; // Skip the next tile as it's part of the pair
            }
        }
        return pairCount;
    }

    // Method to count the number of triplets in the hand
    private static int countTriplets(int[] hand) {
        int tripletCount = 0;
        for (int tile : hand) {
            if (countTiles(hand, tile) >= 3) {
                tripletCount++;
            }
        }
        return tripletCount;
    }

    // Method to count the number of sequences in the hand
    private static int countSequences(int[] hand) {
        Arrays.sort(hand);
        int sequenceCount = 0;
        for (int i = 0; i < hand.length - 2; i++) {
            if (hand[i] + 1 == hand[i + 1] && hand[i + 1] + 1 == hand[i + 2]) {
                sequenceCount++;
                i += 2; // Skip the next two tiles as they're part of the sequence
            }
        }
        return sequenceCount;
    }

    // Method to count the occurrences of a specific tile in the hand
    private static int countTiles(int[] hand, int tile) {
        int count = 0;
        for (int t : hand) {
            if (t == tile) {
                count++;
            }
        }
        return count;
    }
    private static boolean isHu(int[] hand){
        if(isWinningType(hand)){
            int pairs=countPairs(hand);
            int triplets=countTriplets(hand);
            int sequence=countSequences(hand);
            if(pairs==1&&triplets+sequence==4){
                return true;
            }else {
                return  false;
            }
        }else {
            return false;
        }

    }

}
