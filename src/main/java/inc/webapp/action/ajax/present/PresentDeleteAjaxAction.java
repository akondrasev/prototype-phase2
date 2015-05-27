package inc.webapp.action.ajax.present;

import inc.db.dao.PresentDao;
import inc.webapp.action.AjaxBaseAction;
import org.apache.log4j.Logger;

import java.io.IOException;

public class PresentDeleteAjaxAction extends AjaxBaseAction{
    private static Logger logger = Logger.getLogger(PresentDeleteAjaxAction.class);

    private PresentDao presentDao;

    private Long presentId;

    @Override
    protected void makeJson() throws IOException {

        if(logger.isDebugEnabled()){
            logger.debug(String.format("deleting present '%s'", presentId));
        }

        presentDao.deletePresent(presentId);
        jsonResult = "Present removed";
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
