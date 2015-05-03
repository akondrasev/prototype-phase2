package inc.webapp.action.base;

import inc.XConstants;
import inc.webapp.action.BaseAction;
import org.apache.log4j.Logger;

public class LogoutAction extends BaseAction{
    private Logger logger = Logger.getLogger(LogoutAction.class);

    public String execute(){

        if(logger.isDebugEnabled()){
            logger.debug(String.format("'%s' logout", user.getUserName()));
        }

        session.remove(XConstants.SESSION_ATTRIBUTE_KEY_USER);
        return SUCCESS;
    }
}
