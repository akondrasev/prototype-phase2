package inc.webapp.action.ajax;

import inc.db.dao.PresentDao;
import inc.db.model.Present;
import inc.webapp.action.AjaxBaseAction;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.List;

public class PresentsForPartyAjaxAction extends AjaxBaseAction {
    private static Logger logger = Logger.getLogger(PresentsForPartyAjaxAction.class);

    private PresentDao presentDao;

    private Long partyId;

    @Override
    protected void makeJson() throws IOException {
        if(logger.isDebugEnabled()){
            logger.debug(String.format("getting presents for party '%s'", partyId));
        }

        List<Present> presentList = presentDao.getPresentsForParty(partyId);

        jsonResult = gson.toJson(presentList);
    }

    public Long getPartyId() {
        return partyId;
    }

    public void setPartyId(Long partyId) {
        this.partyId = partyId;
    }

    public PresentDao getPresentDao() {
        return presentDao;
    }

    public void setPresentDao(PresentDao presentDao) {
        this.presentDao = presentDao;
    }
}
