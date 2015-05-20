package inc.webapp.action.base;

import inc.db.dao.PartyDao;
import inc.db.dao.UserDao;
import inc.db.model.Party;
import inc.webapp.action.BaseAction;
import org.apache.log4j.Logger;

import java.util.List;

public class HomeAction extends BaseAction {
    private static Logger logger = Logger.getLogger(HomeAction.class);

    private UserDao userDao;
    private PartyDao partyDao;

    private Long usersCount;
    private List<Party> userParties;

    public String execute(){

        if(logger.isDebugEnabled()){
            logger.debug(String.format("home page for user '%s'", user.getUserName()));
        }

        if(user.getIsGuest()){
            usersCount = userDao.getAllUsersCount();

            return SUCCESS;
        }

        userParties = partyDao.getUserParties(user.getUserId());

        return INPUT;
    }

    public Long getUsersCount() {
        return usersCount;
    }

    public void setUsersCount(Long usersCount) {
        this.usersCount = usersCount;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<Party> getUserParties() {
        return userParties;
    }

    public void setUserParties(List<Party> userParties) {
        this.userParties = userParties;
    }

    public PartyDao getPartyDao() {
        return partyDao;
    }

    public void setPartyDao(PartyDao partyDao) {
        this.partyDao = partyDao;
    }
}
