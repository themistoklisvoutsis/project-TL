public class ManageRatingsReviews {
    private DBManager dbManager;

    public ManageRatingsReviews(){
        this.dbManager = new DBManager();
    }

    public void checkComment(String clientId, String gymName, String comment) {
        if (checkMembership(clientId)) {
            dbManager.uploadComment(clientId, gymName, comment);
            System.out.println("Comment submitted successfully.");
        } else {
            System.out.println("You cannot leave a comment without participating in a gym program.");
        }
    }

    public void performRate(String clientId, String gymName, double rating) {
        if (checkMembership(clientId)) {
            if (rating >= 1.0 && rating <= 5.0) {
                dbManager.uploadRating(clientId, gymName, rating);
                System.out.println("Rating submitted successfully.");
            } else {
                System.out.println("Rating must be between 1 and 5 stars.");
            }
        } else {
            System.out.println("You cannot leave a rating without participating in a gym program.");
        }
    }

    public boolean checkMembership(String clientId) {
        return dbManager.searchMembership(clientId);
    }
}
