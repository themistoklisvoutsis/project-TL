import java.math.BigInteger;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        // Διαθέσιμα Είδη
        System.out.println("Οι διαθέσιμες επιλογές είναι: ");
        System.out.println("1. Yoga");
        System.out.println("2. CrossFit");
        System.out.println("3. PersonalTraining");
        System.out.println("4. BodyBuilding");

        // Επιλογή Είδους
        Scanner input = new Scanner(System.in);
        System.out.println("Για να επιλέξεις το επιθυμιτό πρόγραμμα πληκτρολόγησε τον αντίστοιχο αριθμό.");
        int choice = input.nextInt();

        // Εκτύπωση Λεπτομερειών
        switch (choice){
            case 1:
                System.out.println("Επέλεξες Yoga");
                System.out.println("Περιγραφή");
                System.out.println("Τιμή");
                System.out.println("Ώρες");
                System.out.println("Προπονητής");
                break;
            case 2:
                System.out.println("Επέλεξες CrossFit");
                System.out.println("Περιγραφή");
                System.out.println("Τιμή");
                System.out.println("Ώρες");
                System.out.println("Προπονητής");
                break;
            case 3:
                System.out.println("Επέλεξες PersonalTraining");
                System.out.println("Περιγραφή");
                System.out.println("Τιμή");
                System.out.println("Ώρες");
                System.out.println("Προπονητής");
                break;
            case 4:
                System.out.println("Επέλεξες BodyBuilding");
                System.out.println("Περιγραφή");
                System.out.println("Τιμή");
                System.out.println("Ώρες");
                System.out.println("Προπονητής");
                break;
        }

        // Εισαγωγή Στοιχείων GymClient

        // Εισαγωγή Ονόματος
        System.out.println("Συμπλήρωσε τα στοιχεία σου.(Όνομα,Επίθετο,Τηλέφωνο,διεύθυνση)");
        System.out.println("Όνομα:");
        Scanner clientname = new Scanner(System.in);
        String name = clientname.nextLine();

        // Εισαγωγή Επιθέτου
        System.out.println("Επίθετο:");
        Scanner clientlastname = new Scanner(System.in);
        String lastname = clientlastname.nextLine();

        // Εισαγωγή τηλεφώνου
        System.out.println("Τηλέφωνο:");
        Scanner clienttel = new Scanner(System.in);
        BigInteger tel = clienttel.nextBigInteger();

        // Εισαγωγή Διεύθυνσης
        System.out.println("Διεύθυνση:");
        Scanner clientaddress = new Scanner(System.in);
        String address = clientaddress.nextLine();

        // Εκτύπωση Στοιχείων GymClient
        System.out.println("Όνομα:"+name);
        System.out.println("Επίθετο:"+lastname);
        System.out.println("Αριθμός Τηλεφώνου:"+tel);
        System.out.println("Διεύθυνση:"+address);

        // Πληρωμή
        System.out.println("Επιθυμείς συνέχεια στην πληρωμή(Yes/No)");
        Scanner input2 = new Scanner(System.in);
        String choice2 = input2.nextLine();

        if(choice2.equalsIgnoreCase("Yes")){

            System.out.println("Συμπλήρωσε τα στοιχεία της κάρτας σου");

            Scanner input3 = new Scanner(System.in);
            System.out.println("Ονοματεπώνυμο:");
            String fullname = input3.nextLine();
            System.out.println("Ημερομηνία:");
            int date = input3.nextInt();
            System.out.println("CVV");
            int cvv = input3.nextInt();

            System.out.println("Η πληρωμή πραγματοποιήθηκε με επιτυχία.");

        }else System.out.println("Η πληρωμή δεν πραγματοποιήθηκε.");

    }
}