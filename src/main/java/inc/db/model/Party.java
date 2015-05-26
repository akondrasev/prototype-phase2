package inc.db.model;

public class Party {
    private Long partyId;
    private String partyName;
    private String partyOrganizerName;
    private Long partyOrganizerId;
    private String partyDate;
    private Long partyChosenPresentId;
    private boolean partyIsOpen;

    public boolean getPartyIsOpen() {
        return partyIsOpen;
    }

    public void setPartyIsOpen(boolean partyIsOpen) {
        this.partyIsOpen = partyIsOpen;
    }

    private String partyAddress;
    private Long partyDefaultMoney;

    public String getPartyAddress() {
        return partyAddress;
    }

    public void setPartyAddress(String partyAddress) {
        this.partyAddress = partyAddress;
    }

    public Long getPartyDefaultMoney() {
        return partyDefaultMoney;
    }

    public void setPartyDefaultMoney(Long partyDefaultMoney) {
        this.partyDefaultMoney = partyDefaultMoney;
    }

    public Long getPartyId() {
        return partyId;
    }

    public void setPartyId(Long partyId) {
        this.partyId = partyId;
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    public Long getPartyOrganizerId() {
        return partyOrganizerId;
    }

    public void setPartyOrganizerId(Long partyOrganizerId) {
        this.partyOrganizerId = partyOrganizerId;
    }

    public String getPartyOrganizerName() {
        return partyOrganizerName;
    }

    public void setPartyOrganizerName(String partyOrganizerName) {
        this.partyOrganizerName = partyOrganizerName;
    }

    public String getPartyDate() {
        return partyDate;
    }

    public void setPartyDate(String partyDate) {
        this.partyDate = partyDate;
    }

    public Long getPartyChosenPresentId() {
        return partyChosenPresentId;
    }

    public void setPartyChosenPresentId(Long partyChosenPresentId) {
        this.partyChosenPresentId = partyChosenPresentId;
    }
}
