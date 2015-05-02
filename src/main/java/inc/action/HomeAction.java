package inc.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;

public class HomeAction extends ActionSupport{
    private static Logger logger = Logger.getLogger(HomeAction.class);

    public String execute(){
        if(logger.isDebugEnabled()){
            logger.debug(String.format("HomeAction.execute()"));
        }

        return SUCCESS;
    }

}
