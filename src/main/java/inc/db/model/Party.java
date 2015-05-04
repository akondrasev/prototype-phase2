package inc.db.model;

import java.security.Timestamp;

public class Party {
    private Long partyId;
    private String partyName;
    private String partyOrganizerName;
    private Long partyOrganizerId;
    private Timestamp partyDate;

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

    public Timestamp getPartyDate() {
        return partyDate;
    }

    public void setPartyDate(Timestamp partyDate) {
        this.partyDate = partyDate;
    }
}
