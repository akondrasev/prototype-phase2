package inc.webapp.action.base;

import inc.db.dao.UserDao;
import inc.webapp.action.BaseAction;
import org.apache.log4j.Logger;

public class ProfileAction extends BaseAction {
    private static Logger logger = Logger.getLogger(ProfileAction.class);

    private UserDao userDao;

    public String execute(){
        if(user.getIsGuest()){
            return INPUT;
        }
        return SUCCESS;
    }

    public String processEditProfile() throws Exception{

        userDao.editUser(user);

        return SUCCESS;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
