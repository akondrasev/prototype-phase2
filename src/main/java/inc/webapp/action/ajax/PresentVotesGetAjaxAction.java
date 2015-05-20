package inc.webapp.action.ajax;

import inc.db.dao.PresentDao;
import inc.webapp.action.AjaxBaseAction;
import org.apache.log4j.Logger;

import java.io.IOException;

public class PresentVotesGetAjaxAction extends AjaxBaseAction{
    private static Logger logger = Logger.getLogger(PresentVotesGetAjaxAction.class);

    private PresentDao presentDao;

    private Long presentId;

    @Override
    protected void makeJson() throws IOException {


        Long votes = presentDao.getVotesForPresent(presentId);

        if(logger.isDebugEnabled()){
            logger.debug(String.format("got %s votes for present '%s'", votes, presentId));
        }

        jsonResult = votes.toString();

    }

    public Long getPresentId() {
        return presentId;
    }

    public void setPresentId(Long presentId) {
        this.presentId = presentId;
    }

    public PresentDao getPresentDao() {
        return presentDao;
    }

    public void setPresentDao(PresentDao presentDao) {
        this.presentDao = presentDao;
    }
}
