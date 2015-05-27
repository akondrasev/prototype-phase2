package inc.webapp.action.ajax.user;

import inc.db.dao.UserDao;
import inc.db.model.User;
import inc.webapp.action.AjaxBaseAction;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.List;

public class AllUsersAjaxAction extends AjaxBaseAction {
    private static Logger logger = Logger.getLogger(AllUsersAjaxAction.class);

    private UserDao userDao;


    private Long partyId;

    @Override
    protected void makeJson() throws IOException {
        if(logger.isDebugEnabled()){
            logger.debug(String.format("user '%s' is getting all users list (not in party '%s' )", user.getUserId(), partyId));
        }

        List<User> userList = userDao.getAllUsers(partyId);

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
