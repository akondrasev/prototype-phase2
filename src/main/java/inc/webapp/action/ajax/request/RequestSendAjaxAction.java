package inc.webapp.action.ajax.request;

import inc.db.dao.UserDao;
import inc.webapp.action.AjaxBaseAction;
import org.apache.log4j.Logger;

import java.io.IOException;

public class RequestSendAjaxAction extends AjaxBaseAction {
    private static Logger logger = Logger.getLogger(RequestSendAjaxAction.class);

    private UserDao userDao;

    private Long partyId;

    @Override
    protected void makeJson() throws IOException {
        if(logger.isDebugEnabled()){
            logger.debug(String.format("user '%s' is sending participate request for party '%s'", user.getUserId(), partyId));
        }

        userDao.sendRequestForParty(partyId, user.getUserId());

        jsonResult = "Request Sended";

    }

    public Long getPartyId() {
        return partyId;
    }

    public void setPartyId(Long partyId) {
        this.partyId = partyId;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
