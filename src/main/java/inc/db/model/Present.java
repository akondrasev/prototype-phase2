package inc.db.model;

import javax.xml.stream.events.Comment;

public class Present {
    private Long presentId;
    private String presentName;
    private Long presentCost;
    private Long presentForPartyId;
    private String pictureUrl;
    private Long currentMoneyState;
    //TODO comments

    public Long getPresentId() {
        return presentId;
    }

    public void setPresentId(Long presentId) {
        this.presentId = presentId;
    }

    public String getPresentName() {
        return presentName;
    }

    public void setPresentName(String presentName) {
        this.presentName = presentName;
    }

    public Long getPresentCost() {
        return presentCost;
    }

    public void setPresentCost(Long presentCost) {
        this.presentCost = presentCost;
    }

    public Long getPresentForPartyId() {
        return presentForPartyId;
    }

    public void setPresentForPartyId(Long presentForPartyId) {
        this.presentForPartyId = presentForPartyId;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public Long getCurrentMoneyState() {
        return currentMoneyState;
    }

    public void setCurrentMoneyState(Long currentMoneyState) {
        this.currentMoneyState = currentMoneyState;
    }
}
