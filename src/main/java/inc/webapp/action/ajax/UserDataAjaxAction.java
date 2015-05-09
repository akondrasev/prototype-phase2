package inc.webapp.action.ajax;

import inc.db.dao.UserDao;
import inc.db.model.User;
import inc.webapp.action.AjaxBaseAction;
import org.apache.log4j.Logger;

public class UserDataAjaxAction extends AjaxBaseAction {
    private Logger logger = Logger.getLogger(UserDataAjaxAction.class);

    private UserDao userDao;

    private Long userId;

    @Override
    protected void makeJson() {

        if(logger.isDebugEnabled()){
            logger.debug(String.format("userId = %s", userId));
        }

        User user = userDao.getUserById(userId);
        jsonResult = gson.toJson(user);
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
