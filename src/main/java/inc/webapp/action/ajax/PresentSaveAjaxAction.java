package inc.webapp.action.ajax;

import inc.db.dao.PresentDao;
import inc.webapp.action.AjaxBaseAction;
import org.apache.log4j.Logger;

import java.io.IOException;

public class PresentSaveAjaxAction extends AjaxBaseAction {
    private static Logger logger = Logger.getLogger(PresentSaveAjaxAction.class);

    private PresentDao presentDao;

    private Long presentCost;
    private String presentName;
    private String presentPictureUrl;

    @Override
    protected void makeJson() throws IOException {
        if(logger.isDebugEnabled()){
            logger.debug(String.format("presentName = '%s', presentCost = '%s', presentPictureUrl = '%s'"
                    ,presentName
                    ,presentCost
                    ,presentPictureUrl));
        }

        presentDao.savePresent(presentName, presentCost, presentPictureUrl);

        jsonResult = "Present saved successfully";
    }


    public Long getPresentCost() {
        return presentCost;
    }

    public void setPresentCost(Long presentCost) {
        this.presentCost = presentCost;
    }

    public String getPresentName() {
        return presentName;
    }

    public void setPresentName(String presentName) {
        this.presentName = presentName;
    }

    public String getPresentPictureUrl() {
        return presentPictureUrl;
    }

    public void setPresentPictureUrl(String presentPictureUrl) {
        this.presentPictureUrl = presentPictureUrl;
    }

    public PresentDao getPresentDao() {
        return presentDao;
    }

    public void setPresentDao(PresentDao presentDao) {
        this.presentDao = presentDao;
    }
}
