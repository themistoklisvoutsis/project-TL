import java.util.*;


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

