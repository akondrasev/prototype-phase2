package inc.webapp.action.base;

import inc.XConstants;
import inc.db.dao.UserDao;
import inc.db.model.User;
import inc.webapp.action.BaseAction;
import org.apache.log4j.Logger;

public class LoginAction extends BaseAction {
    private static Logger logger = Logger.getLogger(LoginAction.class);

    private UserDao userDao;

    @Override
    public String execute(){
        return SUCCESS;
    }

    public String processLogin(){
        User foundUser = userDao.getUserByLoginData(user.getUserEmail(), user.getUserPassword());
        //TODO logic
        session.put(XConstants.SESSION_ATTRIBUTE_KEY_USER, foundUser);
        return SUCCESS;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
