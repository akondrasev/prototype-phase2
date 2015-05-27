package inc.webapp.action.ajax.party;

import inc.db.dao.PartyDao;
import inc.webapp.action.AjaxBaseAction;

import java.io.IOException;

public class PartiesWithUserAjaxAction extends AjaxBaseAction {

    private PartyDao partyDao;

    @Override
    protected void makeJson() throws IOException {

        jsonResult = gson.toJson(partyDao.getPartiesWithUser(user.getUserId()));
    }

    public PartyDao getPartyDao() {
        return partyDao;
    }

    public void setPartyDao(PartyDao partyDao) {
        this.partyDao = partyDao;
    }
}
