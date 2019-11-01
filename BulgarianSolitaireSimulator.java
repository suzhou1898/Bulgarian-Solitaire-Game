import java.util.*;
public class BulgarianSolitaireSimulator {

    public static void main(String[] args) {

        boolean singleStep = false;
        boolean userConfig = false;

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-u")) {
                userConfig = true;
            }
            else if (args[i].equals("-s")) {
                singleStep = true;
            }
        }

        modeChoose(userConfig,singleStep);
    }

    /**
     This method is used to choose mode to operate according to two flags, which are userConfig and singleStep.
     */
    private static void modeChoose(boolean userConfig, boolean singleStep){
        Scanner scan=new Scanner(System.in);
        if (userConfig){
            run1(singleStep,scan);
            return;
        }
        run2(singleStep,scan);
    }

    /**
     This method accords to the initial configuration from user
     */
    private static void run1(boolean singleStep,Scanner scan){
        ArrayList<Integer> list=new ArrayList<>();
        System.out.println("Number of total cards is 45");
        System.out.println("You will be entering the initial configuration of the cards (i.e., how many in each pile).");
        System.out.println("Please enter a space-separated list of positive integers followed by newline:");
        String str=scan.nextLine();
        while (checkInvalid(str,list)){
            list.clear();
            System.out.println("ERROR: Each pile must have at least one card and the total number of cards must be 45");
            System.out.println("Please enter a space-separated list of positive integers followed by newline:");
            str=scan.nextLine();
        }
        SolitaireBoard newBoard=new SolitaireBoard(list);
        judgeDone(newBoard,singleStep,scan);
    }

    /**
     This method accords to the random configuration.
     */
    private static void run2(boolean singleStep,Scanner scan){
        SolitaireBoard newBoard=new SolitaireBoard();
        judgeDone(newBoard,singleStep,scan);
    }


    /**
     This method is used to print the result of every round.
     */
    private static void judgeDone(SolitaireBoard newBoard, boolean singleStep, Scanner scan){
        System.out.print("Initial configuration:");
        newBoard.printBoard();
        int round=1;
        while (!newBoard.isDone()){
            System.out.print("["+round+"]"+" Current configuration:");
            newBoard.playRound();
            newBoard.printBoard();
            round++;
            if (singleStep){
                System.out.print("<Type return to continue>");
                scan.nextLine();
            }
        }
        System.out.println("Done!");
    }

    /**
     This method is used to do the error check and judge whether the input is valid.
     */
    private static boolean checkInvalid(String str, ArrayList<Integer> list1){

        Scanner scan=new Scanner(str);
        int sum=0;

        while (scan.hasNextInt()){

            int num=scan.nextInt();
            if (num<=0){
                return true;
            }

            list1.add(num);
            sum+=num;
        }
        if (scan.hasNext()){

            return true;
        }
        if (sum!=45){

            return true;
        }

        return false;
    }

}
