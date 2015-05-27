package inc.webapp.action.ajax;

import inc.db.dao.PartyDao;
import inc.webapp.action.AjaxBaseAction;
import org.apache.log4j.Logger;

import java.io.IOException;

public class OpenPartiesAjaxAction extends AjaxBaseAction{
    private static Logger logger = Logger.getLogger(OpenPartiesAjaxAction.class);

    private PartyDao partyDao;

    @Override
    protected void makeJson() throws IOException {
        if(logger.isDebugEnabled()){
            logger.debug(String.format("user '%s' is getting all open parties", user.getUserName()));
        }

        jsonResult = gson.toJson(partyDao.getOpenedParties());

    }

    public PartyDao getPartyDao() {
        return partyDao;
    }

    public void setPartyDao(PartyDao partyDao) {
        this.partyDao = partyDao;
    }
}
