package inc.webapp.action.base;

import inc.db.dao.UserDao;
import inc.webapp.action.BaseAction;
import org.apache.log4j.Logger;

public class HomeAction extends BaseAction {
    private static Logger logger = Logger.getLogger(HomeAction.class);

    private UserDao userDao;

    private Long usersCount;

    public String execute(){

        if(logger.isDebugEnabled()){
            logger.debug(String.format("home page for user '%s'", user.getUserName()));
        }

        if(user.getIsGuest()){
            usersCount = userDao.getAllUsersCount();

            return SUCCESS;
        }

        return INPUT;
    }

    public Long getUsersCount() {
        return usersCount;
    }

    public void setUsersCount(Long usersCount) {
        this.usersCount = usersCount;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
