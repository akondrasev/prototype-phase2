package inc.webapp.action.ajax;

import inc.db.dao.UserDao;
import inc.webapp.action.AjaxBaseAction;
import org.apache.log4j.Logger;

public class UserDataAjaxAction extends AjaxBaseAction {
    private Logger logger = Logger.getLogger(UserDataAjaxAction.class);

    private UserDao userDao;

    private Long personId;

    @Override
    protected void makeJson() {

        if (logger.isDebugEnabled()) {
            logger.debug(String.format("personId = %s", personId));
        }

        user = userDao.getUserById(personId);
        jsonResult = gson.toJson(user);
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
