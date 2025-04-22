import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

class GymClientGUI {
    private String id;
    private String qrCode;
    private Date lastCheckIn;

    public GymClientGUI(String id, String qrCode) {
        this.id = id;
        this.qrCode = qrCode;
    }

    public String getId() {
        return id;
    }

    public String getQrCode() {
        return qrCode;
    }

    public Date getLastCheckIn() {
        return lastCheckIn;
    }

    public void setLastCheckIn(Date lastCheckIn) {
        this.lastCheckIn = lastCheckIn;
    }
}

class GymSystemGUI {
    private Map<String, GymClient> clients;

    public GymSystemGUI() {
        clients = new HashMap<>();
    }

    public void registerClient(GymClient client) {
        clients.put(client.getQrCode(), client);
    }

    public String checkIn(String qrCode) {
        if (!clients.containsKey(qrCode)) {
            return "Check-In Failed: Invalid QR Code.";
        }

        GymClient client = clients.get(qrCode);

        Date now = new Date();
        if (client.getLastCheckIn() != null && isSameDay(client.getLastCheckIn(), now)) {
            return "Check-In Failed: Already checked in today.";
        }

        client.setLastCheckIn(now);
        return "Check-In Successful! Welcome, Client ID: " + client.getId();
    }

    private boolean isSameDay(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);

        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
    }
}

public class GymCheckInAppGUI {
    public static void main(String[] args) {
        GymSystem gymSystem = new GymSystem();
        gymSystem.registerClient(new GymClient("001", "QR12345"));
        gymSystem.registerClient(new GymClient("002", "QR67890"));

        JFrame frame = new JFrame("Gym Check-In System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new FlowLayout());

        JLabel qrCodeLabel = new JLabel("Enter QR Code:");
        JTextField qrCodeField = new JTextField(20);
        JButton checkInButton = new JButton("Check-In");
        JLabel resultLabel = new JLabel("");

        frame.add(qrCodeLabel);
        frame.add(qrCodeField);
        frame.add(checkInButton);
        frame.add(resultLabel);

        checkInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String qrCode = qrCodeField.getText();
                String result = gymSystem.checkIn(qrCode);
                resultLabel.setText(result);
            }
        });
        
        frame.setVisible(true);
    }
}
