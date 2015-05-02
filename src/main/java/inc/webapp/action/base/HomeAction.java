package inc.webapp.action.base;

import inc.db.dao.UserDao;
import inc.webapp.action.BaseAction;
import org.apache.log4j.Logger;

public class HomeAction extends BaseAction {
    private static Logger logger = Logger.getLogger(HomeAction.class);

    private UserDao userDao;

    public String execute(){
        if(logger.isDebugEnabled()){
            logger.debug(String.format("HomeAction.execute()"));
            logger.debug(String.format("user.userName = %s", user.getUserName()));
            logger.debug(String.format("user.isGuest = %s", user.getIsGuest()));
        }

        return SUCCESS;
    }
}
