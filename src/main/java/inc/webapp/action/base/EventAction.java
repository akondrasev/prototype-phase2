package inc.webapp.action.base;

import inc.XConstants;
import inc.db.dao.PartyDao;
import inc.db.model.Party;
import inc.webapp.action.BaseAction;
import org.apache.log4j.Logger;

import java.util.Collection;

public class EventAction extends BaseAction{
    private static Logger logger = Logger.getLogger(EventAction.class);

    private PartyDao partyDao;

    private Long partyId;



    private String partyName;
    private String partyAddress;
    private Long partyDefaultMoney;
    private Boolean partyIsOpen;
    private String partyDate;


    public String execute(){

        if(user.getIsGuest()){
            return LOGIN;
        }

        partyId = partyDao.createDraftEvent();

        if(session.get(XConstants.SESSION_ATTRIBUTE_KEY_PARTY_ID) == null){
            session.put(XConstants.SESSION_ATTRIBUTE_KEY_PARTY_ID, partyId);
        }

        setActionMessages((Collection<String>) session.get(XConstants.SESSION_ATTRIBUTE_KEY_MSG));
        session.remove(XConstants.SESSION_ATTRIBUTE_KEY_MSG);
        return SUCCESS;
    }

    public String process(){
        if(user.getIsGuest()){
            return LOGIN;
        }

        if(logger.isDebugEnabled()){
            logger.debug(String.format("partyName = %s, partyAddress = %s, partyDefaultMoney = %s, partyDate = %s, partyIsOpen = %s",
                    partyName, partyAddress, partyDefaultMoney,partyDate, partyIsOpen));
        }

        Long partyId = (Long) session.get(XConstants.SESSION_ATTRIBUTE_KEY_PARTY_ID);
        if(logger.isDebugEnabled()){
            logger.debug(String.format("saving party '%s'", partyId));
        }

        Party party = new Party();
        party.setPartyId(partyId);
        party.setPartyAddress(partyAddress);
        party.setPartyDate(partyDate);
        party.setPartyDefaultMoney(partyDefaultMoney);
        party.setPartyName(partyName);
        party.setPartyOrganizerId(user.getUserId());

        partyDao.updateParty(party);

        addActionMessage("Party is successfully added");

        session.put(XConstants.SESSION_ATTRIBUTE_KEY_MSG, getActionMessages());

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
