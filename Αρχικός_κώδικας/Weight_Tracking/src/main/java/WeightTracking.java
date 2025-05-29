import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class WeightTracking extends JFrame {
    private JTextField weightField;
    private JTextField heightField;
    private JTextArea resultArea;
    private ArrayList<String> history = new ArrayList<>();

    //Ο constructor για την κλάση WeightTracking
    public WeightTracking() {
        setTitle("Υπολογισμός ΔΜΣ");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Περιοχή Εισόδου τιμών
        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        inputPanel.add(new JLabel("Βάρος (kg):"));
        weightField = new JTextField();
        inputPanel.add(weightField);

        inputPanel.add(new JLabel("Ύψος (m):"));
        heightField = new JTextField();
        inputPanel.add(heightField);

        JButton calculateButton = new JButton("Υπολογισμός ΔΜΣ");
        inputPanel.add(calculateButton);

        JButton historyButton = new JButton("Ιστορικό");
        inputPanel.add(historyButton);

        add(inputPanel, BorderLayout.NORTH);

        // Πεδίο Αποτελεσμάτων
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        add(new JScrollPane(resultArea), BorderLayout.CENTER);

        // Υπολογισμός BMI
        calculateButton.addActionListener(e -> calculateBMI());

        // Ιστορικό
        historyButton.addActionListener(e -> showHistory());
    }

    private void calculateBMI() {
        try {
            double weight = Double.parseDouble(weightField.getText());
            double height = Double.parseDouble(heightField.getText());

            if (weight <= 0 || height <= 0) {
                throw new NumberFormatException();
            }

            double bmi = weight / (height * height);
            String formattedBMI = String.format("%.2f", bmi);
            String date = LocalDate.now().toString();
            String entry = "Ημ/νία: " + date + " | BMI: " + formattedBMI;

            resultArea.setText("Ο Δείκτης Μάζας Σώματος είναι: " + formattedBMI);
            history.add(entry);
        } catch (NumberFormatException ex) {
            resultArea.setText("Παρακαλώ εισάγετε έγκυρα αριθμητικά στοιχεία για βάρος και ύψος.");
        }
    }

    private void showHistory() {
        if (history.isEmpty()) {
            resultArea.setText("Δεν υπάρχει ιστορικό.");
        } else {
            StringBuilder sb = new StringBuilder("Ιστορικό ΔΜΣ:\n");
            for (String entry : history) {
                sb.append(entry).append("\n");
            }
            resultArea.setText(sb.toString());
        }
    }

    // Κλάση Main
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new WeightTracking().setVisible(true);
        });
    }
}
