package inc.webapp.action.ajax.user;

import inc.db.dao.UserDao;
import inc.webapp.action.AjaxBaseAction;
import org.apache.log4j.Logger;

import java.io.IOException;

public class GuestAddAjaxAction extends AjaxBaseAction {
    private static Logger logger = Logger.getLogger(GuestAddAjaxAction.class);

    private UserDao userDao;

    private Long personId;
    private Long partyId;

    @Override
    protected void makeJson() throws IOException {

        if(logger.isDebugEnabled()){
            logger.debug(String.format("user '%s' is adding guest '%s' to party '%s'",
                    user.getUserId(), personId, partyId));
        }

        userDao.addGuestToParty(personId, partyId);

        jsonResult = "Guest added";

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
