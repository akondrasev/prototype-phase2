package inc.webapp.action.ajax.user;

import inc.db.dao.UserDao;
import inc.webapp.action.AjaxBaseAction;
import org.apache.log4j.Logger;

import java.io.IOException;

public class GuestRemoveAjaxAction extends AjaxBaseAction {
    private static Logger logger = Logger.getLogger(GuestRemoveAjaxAction.class);

    private UserDao userDao;

    private Long personId;
    private Long partyId;

    @Override
    protected void makeJson() throws IOException {

        if(logger.isDebugEnabled()){
            logger.debug(String.format("user '%s' is deleting guest '%s' from party '%s'",
                    user.getUserId(), personId, partyId));
        }

        userDao.removeGuestFromParty(personId, partyId);

        jsonResult = "Guest removed";

    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public Long getPartyId() {
        return partyId;
    }

    public void setPartyId(Long partyId) {
        this.partyId = partyId;
    }
}
