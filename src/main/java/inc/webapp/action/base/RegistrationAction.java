package inc.webapp.action.base;

import inc.db.dao.UserDao;
import inc.webapp.action.BaseAction;
import org.apache.log4j.Logger;

public class RegistrationAction extends BaseAction {
    private static Logger logger = Logger.getLogger(RegistrationAction.class);

    private UserDao userDao;

    public String execute(){
        return SUCCESS;
    }

    public String processRegistration(){
        //TODO validation

        userDao.addUser(user.getUserName(),user.getUserPassword(), user.getUserEmail(), user.getUserBank());
        addActionMessage(getText("registration.successful"));

        return SUCCESS;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
