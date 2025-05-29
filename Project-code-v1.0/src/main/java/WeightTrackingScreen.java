import java.util.Scanner;

public class WeightTrackingScreen{
    public static int categorySelection(){

        Scanner input=new Scanner(System.in);
        int choice= input.nextInt();

        return choice;
    }

    // Επιλογές χρήστη.
    public static void showWeightTrackingScreen(){
        System.out.println("1.BMI calculation.");
        System.out.println("2.History preview.");
    }
}
