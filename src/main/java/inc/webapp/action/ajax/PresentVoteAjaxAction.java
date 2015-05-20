package inc.webapp.action.ajax;

import inc.db.dao.PresentDao;
import inc.webapp.action.AjaxBaseAction;
import org.apache.log4j.Logger;

import java.io.IOException;

public class PresentVoteAjaxAction extends AjaxBaseAction {
    private static Logger logger = Logger.getLogger(PresentVoteAjaxAction.class);

    private PresentDao presentDao;

    private Long presentId;

    @Override
    protected void makeJson() throws IOException {

        if(logger.isDebugEnabled()){
            logger.debug(String.format("user '%s' voted for present '%s'", user.getUserId(), presentId));
        }

        presentDao.voteForPresent(presentId);

        jsonResult = "You have voted for present";
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
