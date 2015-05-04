package inc.webapp.action.base;

import inc.webapp.action.BaseAction;
import org.apache.log4j.Logger;

public class RegistrationAction extends BaseAction {
    private static Logger logger = Logger.getLogger(RegistrationAction.class);

    public String execute(){
        return SUCCESS;
    }

    public String processRegistration(){
        //TODO validation
        return SUCCESS;
    }
}
