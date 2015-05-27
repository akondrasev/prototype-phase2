package inc.webapp.action.ajax.user;

import inc.db.dao.UserDao;
import inc.db.model.User;
import inc.webapp.action.AjaxBaseAction;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.List;

public class GuestsForPartyAjaxAction extends AjaxBaseAction{
    private static Logger logger = Logger.getLogger(GuestsForPartyAjaxAction.class);

    private UserDao userDao;
    private Long partyId;

    @Override
    protected void makeJson() throws IOException {

        if(logger.isDebugEnabled()){
            logger.debug(String.format("user '%s' is getting guests list for party '%s'", user.getUserId(), partyId));
        }

        List<User> userList = userDao.getGuestsForParty(partyId);

        jsonResult = gson.toJson(userList);

    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public Long getPartyId() {
        return partyId;
    }

    public void setPartyId(Long partyId) {
        this.partyId = partyId;
    }
}
