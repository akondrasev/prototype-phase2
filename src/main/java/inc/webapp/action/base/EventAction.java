package inc.webapp.action.base;

import inc.db.dao.PartyDao;
import inc.db.model.Party;
import inc.webapp.action.BaseAction;
import org.apache.log4j.Logger;

public class EventAction extends BaseAction{
    private static Logger logger = Logger.getLogger(EventAction.class);

    private PartyDao partyDao;

    private String partyName;
    private String partyAddress;
    private Long partyDefaultMoney;
    private Boolean partyIsOpen;
    private String partyDate;


    public String execute(){

        if(user.getIsGuest()){
            return INPUT;
        }

        return SUCCESS;
    }

    public String process(){

        if(user.getIsGuest()){
            return INPUT;
        }

        Party party = new Party();
        party.setPartyAddress(partyAddress);
        party.setPartyDate(partyDate);
        party.setPartyDefaultMoney(partyDefaultMoney);
        party.setPartyName(partyName);
        party.setPartyOrganizerId(user.getUserId());

        partyDao.createEvent(party);

        return SUCCESS;
    }

    public PartyDao getPartyDao() {
        return partyDao;
    }

    public void setPartyDao(PartyDao partyDao) {
        this.partyDao = partyDao;
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

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

    public Boolean getPartyIsOpen() {
        return partyIsOpen;
    }

    public String getPartyDate() {
        return partyDate;
    }

    public void setPartyDate(String partyDate) {
        this.partyDate = partyDate;
    }

    public void setPartyIsOpen(Boolean partyIsOpen) {
        this.partyIsOpen = partyIsOpen;
    }
}
