package inc.webapp.action.ajax;

import inc.db.dao.PresentDao;
import inc.db.model.Present;
import inc.webapp.action.AjaxBaseAction;
import org.apache.log4j.Logger;

public class MostWantedPresentForPartyAjaxAction extends AjaxBaseAction {
    private static Logger logger = Logger.getLogger(MostWantedPresentForPartyAjaxAction.class);

    private Long partyId;
    private PresentDao presentDao;

    @Override
    protected void makeJson() {

        if(logger.isDebugEnabled()){
            logger.debug(String.format("partyId = %s", partyId));
        }

        Present present = presentDao.getMostVotedPresentForParty(partyId);
        jsonResult = gson.toJson(present);
    }

    public PresentDao getPresentDao() {
        return presentDao;
    }

    public void setPresentDao(PresentDao presentDao) {
        this.presentDao = presentDao;
    }

    public Long getPartyId() {
        return partyId;
    }

    public void setPartyId(Long partyId) {
        this.partyId = partyId;
    }
}
