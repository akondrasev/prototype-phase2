package inc.webapp.action.ajax;

import inc.db.dao.PartyDao;
import inc.db.model.Party;
import inc.webapp.action.AjaxBaseAction;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class UserPartiesAjaxAction extends AjaxBaseAction {
    private static Logger logger = Logger.getLogger(UserPartiesAjaxAction.class);

    private PartyDao partyDao;

    @Override
    protected void makeJson() throws IOException {

        if(logger.isDebugEnabled()){
            logger.debug(String.format("user '%s' is getting parties json", user.getUserName()));
        }

        List<Party> parties = partyDao.getUserParties(user.getUserId());

        Map<String, Object> response = new TreeMap<>();
        response.put("data", parties);

        jsonResult = gson.toJson(parties);
    }

    public PartyDao getPartyDao() {
        return partyDao;
    }

    public void setPartyDao(PartyDao partyDao) {
        this.partyDao = partyDao;
    }
}
