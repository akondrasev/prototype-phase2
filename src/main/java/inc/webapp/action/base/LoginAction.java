package inc.webapp.action.base;

import inc.XConstants;
import inc.db.dao.UserDao;
import inc.db.model.User;
import inc.webapp.action.BaseAction;
import org.apache.log4j.Logger;

import java.util.Collection;

public class LoginAction extends BaseAction {
    private static Logger logger = Logger.getLogger(LoginAction.class);

    private UserDao userDao;

    @Override
    public String execute(){
        setActionErrors((Collection<String>) session.get(XConstants.SESSION_ATTRIBUTE_KEY_ERROR));

        session.remove(XConstants.SESSION_ATTRIBUTE_KEY_ERROR);

        return SUCCESS;
    }

    public String processLogin(){
        String email = user.getUserEmail();
        String password = user.getUserPassword();

        User foundUser = userDao.getUserByLoginData(email, password);

        if(foundUser == null){

            if(logger.isDebugEnabled()){
                logger.debug(String.format("user '%s' not found", email));
            }

            addActionError(getText("user.not.found"));
            session.put(XConstants.SESSION_ATTRIBUTE_KEY_ERROR, getActionErrors());
            return INPUT;
        }

        if(logger.isDebugEnabled()){
            logger.debug(String.format("user '%s' logged in", email));
        }

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
