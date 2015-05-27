package inc.webapp.action.ajax.request;

import inc.db.dao.UserDao;
import inc.webapp.action.AjaxBaseAction;
import org.apache.log4j.Logger;

import java.io.IOException;

public class RequestsGetAjaxAction extends AjaxBaseAction{
    private static Logger logger = Logger.getLogger(RequestsGetAjaxAction.class);


    private UserDao userDao;

    @Override
    protected void makeJson() throws IOException {
        if (logger.isDebugEnabled()){
            logger.debug(String.format("user '%s' is getting requests to his parties", user.getUserId()));
        }

        jsonResult = gson.toJson(userDao.getRequestsForUser(user.getUserId()));

    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
