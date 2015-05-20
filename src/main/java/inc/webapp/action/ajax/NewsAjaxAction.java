package inc.webapp.action.ajax;

import inc.db.dao.UserDao;
import inc.db.model.NewsCounts;
import inc.webapp.action.AjaxBaseAction;
import org.apache.log4j.Logger;

public class NewsAjaxAction extends AjaxBaseAction {
    private static Logger logger = Logger.getLogger(NewsAjaxAction.class);

    private UserDao userDao;

    private Long personId;

    @Override
    protected void makeJson() {

        if(logger.isDebugEnabled()){
            logger.debug(String.format("user '%s' is getting info for user '%s'", user.getUserId(), personId));
        }

        /*
        can see only own info
         */
        if(user.getUserId() != null && user.getUserId().equals(personId)){
            NewsCounts newsCounts = userDao.getNewsCountForUser(personId);
            jsonResult = gson.toJson(newsCounts);
        } else {
            jsonResult = getText("permission.denied");
        }
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }
}
