package inc.db.model;

public class NewsCounts {
    private Long newsCount;
    private Long requestsCount;
    private Long guestsCount;
    private Long invitesCount;

    public Long getNewsCount() {
        return newsCount;
    }

    public void setNewsCount(Long newsCount) {
        this.newsCount = newsCount;
    }

    public Long getRequestsCount() {
        return requestsCount;
    }

    public void setRequestsCount(Long requestsCount) {
        this.requestsCount = requestsCount;
    }

    public Long getGuestsCount() {
        return guestsCount;
    }

    public void setGuestsCount(Long guestsCount) {
        this.guestsCount = guestsCount;
    }

    public Long getInvitesCount() {
        return invitesCount;
    }

    public void setInvitesCount(Long invitesCount) {
        this.invitesCount = invitesCount;
    }
}
