package inc.webapp.action.ajax.present;

import inc.db.dao.PresentDao;
import inc.db.model.Present;
import inc.webapp.action.AjaxBaseAction;
import org.apache.log4j.Logger;

public class PresentInfoAjaxAction extends AjaxBaseAction {
    private static Logger logger = Logger.getLogger(PresentInfoAjaxAction.class);

    private Long presentId;
    private PresentDao presentDao;

    @Override
    protected void makeJson() {

        if(logger.isDebugEnabled()){
            logger.debug(String.format("getting info for present '%s'", presentId));
        }

        Present present = presentDao.getPresent(presentId);
        jsonResult = gson.toJson(present);
    }

    public PresentDao getPresentDao() {
        return presentDao;
    }

    public void setPresentDao(PresentDao presentDao) {
        this.presentDao = presentDao;
    }

    public Long getPresentId() {
        return presentId;
    }

    public void setPresentId(Long presentId) {
        this.presentId = presentId;
    }
}
