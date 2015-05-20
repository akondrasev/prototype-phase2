package inc.webapp.action.base;

import inc.db.dao.PartyDao;
import inc.db.dao.UserDao;
import inc.db.model.Party;
import inc.webapp.action.BaseAction;
import org.apache.log4j.Logger;

import java.util.List;

public class PartiesAction extends BaseAction {
    private static Logger logger = Logger.getLogger(PartiesAction.class);

    private UserDao userDao;
    private PartyDao partyDao;

    private List<Party> openParties;

    public String execute(){

        if(logger.isDebugEnabled()){
            logger.debug(String.format("open parties page for user '%s'", user.getUserName()));
        }

        openParties = partyDao.getOpenedParties();

        return SUCCESS;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public PartyDao getPartyDao() {
        return partyDao;
    }

    public void setPartyDao(PartyDao partyDao) {
        this.partyDao = partyDao;
    }

    public List<Party> getOpenParties() {
        return openParties;
    }

    public void setOpenParties(List<Party> openParties) {
        this.openParties = openParties;
    }
}
