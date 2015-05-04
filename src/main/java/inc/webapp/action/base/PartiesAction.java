package inc.webapp.action.base;

import inc.db.dao.UserDao;
import inc.webapp.action.BaseAction;
import org.apache.log4j.Logger;

public class PartiesAction extends BaseAction {
    private static Logger logger = Logger.getLogger(PartiesAction.class);

    private UserDao userDao;

    public String execute(){
        return SUCCESS;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
