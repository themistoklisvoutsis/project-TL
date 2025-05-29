public class QRScreen {
    public void create(String qrCode) {
        System.out.println("QR Code created: " + qrCode);
    }
        public boolean qrRead(String qrCode) {
            return qrCode != null && !qrCode.isEmpty();
        }



    public void display(String qrCode) {
        System.out.println("Displaying QR Code: " + qrCode);
    }

}

