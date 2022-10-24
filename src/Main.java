import layoutsReplacing.LayoutReplacement;
import layoutsReplacing.Layouts;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        LayoutReplacement l=new LayoutReplacement();

        inputLayoutReplacementPairs(l);

        System.out.println("Input string which you want to replace layouts:");

        String inputString = scanner.nextLine();

        String outputString = l.initializeReplacing(inputString);

        System.out.println("Result:");
        System.out.println(outputString);
    }

    //if returns true program ends
    static void inputLayoutReplacementPairs(LayoutReplacement l) {
        Layouts changingLayout, replacingLayout;

        do {
            changingLayout=choosingLayout("Choose layout you want to replace:");

            replacingLayout=choosingLayout("Choose layout you want to replace with:");

            l.addReplacement(changingLayout,replacingLayout);

            if(!choosingYesOrNo("Do you want to add any layout replacing or change existing replacements?(y/n)")) {
                return;
            }
            //if user want to add another replacement the cycle continues
        }while (true);
    }

    //input menu; message outputting before input
    static Layouts choosingLayout(String message) {
        Scanner scanner = new Scanner(System.in);

        String userInput;
        do {
            System.out.println(message);    //output message
            for (Layouts i: Layouts.values()) {
                System.out.printf("\t%d. %s.%n", i.ordinal()+1, i);
            }
            System.out.println("\t0. Exit.");

            userInput = scanner.nextLine();

            if(userInput.compareTo("0")==0) {
                programExit();
            }
            for (int i=1; i<=Layouts.values().length; i++) {
                if(userInput.equals(Integer.toString(i))) {
                    return Layouts.values()[i-1];
                }
            }
            //if input is incorrect
            System.out.println("Please input correct option.\n");
        } while (true);
    }

    //input menu (only yes or no); message outputting before input; returns true if yes, returns false if no
    static boolean choosingYesOrNo(String message) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(message);

        String userInput;
        do {
            userInput = scanner.nextLine();
            if(userInput.equals("y") || userInput.equals("yes") || userInput.equals("1")) return true;
            if(userInput.equals("n") || userInput.equals("no") || userInput.equals("0")) return false;
            //if input is incorrect
            System.out.println("Please input correct option.\n");
        } while (true);
    }

    static void programExit() {
        System.out.println("Goodbye!");
        System.exit(0);
    }
}