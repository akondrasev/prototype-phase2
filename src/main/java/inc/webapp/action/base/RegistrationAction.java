package inc.webapp.action.base;

import inc.XConstants;
import inc.db.dao.UserDao;
import inc.webapp.action.BaseAction;
import org.apache.log4j.Logger;

import java.util.Collection;

public class RegistrationAction extends BaseAction {
    private static Logger logger = Logger.getLogger(RegistrationAction.class);

    private UserDao userDao;

    public String execute(){

        setActionMessages((Collection<String>) session.get(XConstants.SESSION_ATTRIBUTE_KEY_MSG));

        session.remove(XConstants.SESSION_ATTRIBUTE_KEY_MSG);
        return SUCCESS;
    }

    public String processRegistration(){
        //TODO validation

        userDao.addUser(user.getUserName(),user.getUserPassword(), user.getUserEmail(), user.getUserBank());
        addActionMessage(getText("registration.successful"));
        session.put(XConstants.SESSION_ATTRIBUTE_KEY_MSG, getActionMessages());

        return SUCCESS;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
