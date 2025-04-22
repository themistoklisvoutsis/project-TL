

import java.util.*;

class GymClient {
    private String id;
    private String qrCode;
    private Date lastCheckIn;

    public GymClient(String id, String qrCode) {
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

class GymSystem {
    private Map<String, GymClient> clients;

    public GymSystem() {
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

public class GymCheckInApp {
    public static void main(String[] args) {
        GymSystem gymSystem = new GymSystem();

        GymClient client1 = new GymClient("001", "QR12345");
        GymClient client2 = new GymClient("002", "QR67890");
        gymSystem.registerClient(client1);
        gymSystem.registerClient(client2);


    }
}