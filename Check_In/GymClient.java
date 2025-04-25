import java.util.Date;

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