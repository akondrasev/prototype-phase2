package inc.webapp.action.ajax.party;

import inc.db.dao.PartyDao;
import inc.webapp.action.AjaxBaseAction;
import org.apache.log4j.Logger;

import java.io.IOException;

public class PartyRemoveAjaxAction extends AjaxBaseAction {
    private static Logger logger = Logger.getLogger(PartyRemoveAjaxAction.class);

    private PartyDao partyDao;

    private Long partyId;

    @Override
    protected void makeJson() throws IOException {

        if(logger.isDebugEnabled()){
            logger.debug(String.format("user '%s' is deleting party '%s'", user.getUserId(), partyId));
        }

        partyDao.removeParty(partyId);

        jsonResult = "Party removed";

    }

    public Long getPartyId() {
        return partyId;
    }

    public void setPartyId(Long partyId) {
        this.partyId = partyId;
    }

    public PartyDao getPartyDao() {
        return partyDao;
    }

    public void setPartyDao(PartyDao partyDao) {
        this.partyDao = partyDao;
    }
}
