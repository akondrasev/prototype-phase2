package inc.webapp.action.ajax.user;

import inc.db.dao.UserDao;
import inc.webapp.action.AjaxBaseAction;

import java.io.IOException;

public class InvitesGetForUserAjaxAction extends AjaxBaseAction {

    private UserDao userDao;

    private Long partyId;

    @Override
    protected void makeJson() throws IOException {

        jsonResult = gson.toJson(userDao.getInvites(partyId, user.getUserId()));

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
