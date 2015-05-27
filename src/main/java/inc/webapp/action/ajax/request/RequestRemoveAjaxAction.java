package inc.webapp.action.ajax.request;

import inc.db.dao.UserDao;
import inc.webapp.action.AjaxBaseAction;
import org.apache.log4j.Logger;

import java.io.IOException;

public class RequestRemoveAjaxAction extends AjaxBaseAction {
    private static Logger logger = Logger.getLogger(RequestRemoveAjaxAction.class);

    private UserDao userDao;

    private Boolean isAccepted;
    private Long personId;
    private Long partyId;

    @Override
    protected void makeJson() throws IOException {
        if(logger.isDebugEnabled()){
            logger.debug(String.format("user '%s' accepted request from '%s' for party '%s'", user.getUserId(), personId, partyId));
        }

        userDao.deleteRequestFromUser(partyId, personId, isAccepted);

        jsonResult = gson.toJson("Request watched");

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

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public Boolean getIsAccepted() {
        return isAccepted;
    }

    public void setIsAccepted(Boolean isAccepted) {
        this.isAccepted = isAccepted;
    }
}
