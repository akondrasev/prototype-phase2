package inc.db.model;

public class User {
    private Long userId;
    private String userName;
    private String userEmail;
    private String userPassword;
    private String userBank;

    private Long invitesCount;
    private Long guestsCount;
    private Long requestsCount;

    private Long newsCount;
    private Boolean isGuest;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserBank() {
        return userBank;
    }

    public void setUserBank(String userBank) {
        this.userBank = userBank;
    }

    public Boolean getIsGuest() {
        return isGuest;
    }

    public void setIsGuest(Boolean isGuest) {
        this.isGuest = isGuest;
    }

    public Long getNewsCount() {
        return newsCount;
    }

    public void setNewsCount(Long newsCount) {
        this.newsCount = newsCount;
    }

    public Long getInvitesCount() {
        return invitesCount;
    }

    public void setInvitesCount(Long invitesCount) {
        this.invitesCount = invitesCount;
    }

    public Long getGuestsCount() {
        return guestsCount;
    }

    public void setGuestsCount(Long guestsCount) {
        this.guestsCount = guestsCount;
    }

    public Long getRequestsCount() {
        return requestsCount;
    }

    public void setRequestsCount(Long requestsCount) {
        this.requestsCount = requestsCount;
    }
}
