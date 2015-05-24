package inc.webapp.action.base;

import inc.XConstants;
import inc.db.dao.UserDao;
import inc.webapp.action.BaseAction;
import org.apache.log4j.Logger;

import java.util.List;

public class ProfileAction extends BaseAction {
    private static Logger logger = Logger.getLogger(ProfileAction.class);

    private UserDao userDao;

    public String execute(){

        if(logger.isDebugEnabled()){
            logger.debug(String.format("profile page for user '%s'", user.getUserId()));
        }

        if(user.getIsGuest()){
            return INPUT;
        }

        List<String> msgs = (List<String>) session.get(XConstants.SESSION_ATTRIBUTE_KEY_MSG);

        if(msgs != null){
            setActionMessages(msgs);
        }
        session.remove(XConstants.SESSION_ATTRIBUTE_KEY_MSG);

        return SUCCESS;
    }

    public String processEditProfile() throws Exception{

        if(logger.isDebugEnabled()){
            logger.debug(String.format("editing user profile '%s'", user.getUserId()));
        }
        userDao.editUser(user);

        addActionMessage("Profile edited successfully");
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
