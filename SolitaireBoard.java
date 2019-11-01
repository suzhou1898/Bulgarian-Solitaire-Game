import java.util.ArrayList;
import java.util.Random;
import java.util.HashSet;
/*
  class SolitaireBoard
  The board for Bulgarian Solitaire.  You can change what the total 
  number of cards is for the game by changing NUM_FINAL_PILES, below.  
  Don't change CARD_TOTAL directly, because there are only some values
  for CARD_TOTAL that result in a game that terminates.  (See comments 
  below next to named constant declarations for more details on this.)
*/


public class SolitaireBoard {

    public static final int NUM_FINAL_PILES = 9;
    // number of piles in a final configuration
    // (note: if NUM_FINAL_PILES is 9, then CARD_TOTAL below will be 45)

    public static final int CARD_TOTAL = NUM_FINAL_PILES * (NUM_FINAL_PILES + 1) / 2;
    // bulgarian solitaire only terminates if CARD_TOTAL is a triangular number.
    // see: http://en.wikipedia.org/wiki/Bulgarian_solitaire for more details
    // the above formula is the closed form for 1 + 2 + 3 + . . . + NUM_FINAL_PILES

    // Note to students: you may not use an ArrayList -- see assgt
    // description for details.


    /**
     Representation invariant: --size is the number of elements in the partially filled array
     --the capacity of the array is NUM_FINAL_PILES+2
     --the value of each element in this partially filled array should be positive
     --the sum of the value from Array[0] to Array[size-1] should be equal to CARD_TOTAL


     <put rep. invar. comment here>
     --size is the number of current piles after each round
     --the maximum number of piles can be NUM_FINAL_PILES+2
     --the number of cards in each pile must larger than 0
     --the total number of cards from all the piles should be equal to CARD_TOTAL

     */


    private int[] array;
    private int size;


    /**
     Creates a solitaire board with the configuration specified in piles.
     piles has the number of cards in the first pile, then the number of
     cards in the second pile, etc.
     PRE: piles contains a sequence of positive numbers that sum to
     SolitaireBoard.CARD_TOTAL
     */
    public SolitaireBoard(ArrayList<Integer> piles) {

        // sample assert statement (you will be adding more of these calls)
        // this statement stays at the end of the constructor.

        array=new int[NUM_FINAL_PILES+2];
        size=piles.size();
        for (int i=0;i<size;i++){
            array[i]=piles.get(i);
        }
        assert(isValidSolitaireBoard());
    }


    /**
     Creates a solitaire board with a random initial configuration.
     */
    public SolitaireBoard() {
        Random rand=new Random();
        array=new int[NUM_FINAL_PILES+2];
        size=1+rand.nextInt(NUM_FINAL_PILES);
        int cardNum=CARD_TOTAL;
        int pileSize=size;
        for (int i=0;i<size-1;i++){
            array[i]=1+rand.nextInt(cardNum-pileSize+1);
            cardNum-=array[i];
            pileSize--;
        }
        array[size-1]=cardNum;
    }


    /**
     Plays one round of Bulgarian solitaire.  Updates the configuration
     according to the rules of Bulgarian solitaire: Takes one card from each
     pile, and puts them all together in a new pile.
     The old piles that are left will be in the same relative order as before,
     and the new pile will be at the end.
     */
    public void playRound() {
        for (int i=0;i<size;i++){
            array[i]--;
        }
        array[size]=size;
        int[] temp=new int[NUM_FINAL_PILES+2];
        int j=0;
        for (int i=0;i<size+1;i++){
            if (array[i]!=0){
                temp[j++]=array[i];
            }
        }
        array=temp;
        size=j;
    }

    /**
     Returns true iff the current board is at the end of the game.  That is,
     there are NUM_FINAL_PILES piles that are of sizes
     1, 2, 3, . . . , NUM_FINAL_PILES,
     in any order.
     */

    public boolean isDone() {
        if (size!=NUM_FINAL_PILES){
            return false;
        }
        HashSet<Integer> set=new HashSet<>();
        for (int i=1;i<NUM_FINAL_PILES+1;i++){
            set.add(i);
        }
        for (int i=0;i<size;i++){
            if (set.contains(array[i])){
                set.remove(array[i]);
            } else{
                return false;
            }
        }
        return true;


    }


    /**
     Returns current board configuration as a string with the format of
     a space-separated list of numbers with no leading or trailing spaces.
     The numbers represent the number of cards in each non-empty pile.
     */
    public String configString() {
        String str="";
        for (int i=0;i<size-1;i++){
            str+=array[i]+" ";
        }
        return str+=array[size-1];
    }


    /**
     Returns true iff the solitaire board data is in a valid state
     (See representation invariant comment for more details.)
     */
    private boolean isValidSolitaireBoard() {
        int sum=0;
        for (int i=0;i<size;i++){
            if (array[i]<=0){
                return false;
            } else{
                sum+=array[i];
            }
        }
        if (sum!=CARD_TOTAL){
            return false;
        }
        return true;

    }

    public void printBoard(){
        for (int i=0;i<size-1;i++){
            System.out.print(" "+array[i]);
        }
        System.out.println(" "+array[size-1]);
    }


}