package inc.webapp.action.ajax.user;

import inc.db.dao.UserDao;
import inc.webapp.action.AjaxBaseAction;

import java.io.IOException;

public class InviteRemoveAjaxAction extends AjaxBaseAction {


    private UserDao userDao;

    private Long partyId;
    private Boolean isAccepted;

    @Override
    protected void makeJson() throws IOException {

        userDao.removeInvite(partyId, user.getUserId(), isAccepted);

        jsonResult = "Invite watched";

    }

    public Long getPartyId() {
        return partyId;
    }

    public void setPartyId(Long partyId) {
        this.partyId = partyId;
    }

    public Boolean getIsAccepted() {
        return isAccepted;
    }

    public void setIsAccepted(Boolean isAccepted) {
        this.isAccepted = isAccepted;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
