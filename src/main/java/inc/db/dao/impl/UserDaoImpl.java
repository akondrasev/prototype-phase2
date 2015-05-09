package inc.db.dao.impl;

import inc.db.dao.UserDao;
import inc.db.model.NewsCounts;
import inc.db.model.User;
import org.apache.log4j.Logger;

import java.util.List;

public class UserDaoImpl implements UserDao{
    private static Logger logger = Logger.getLogger(UserDaoImpl.class);

    @Override
    public Long getAllUsersCount() {
        return 10L;
    }

    @Override
    public List<User> getPartyOrganizers(Long partyId) {
        return null;
    }

    @Override
    public List<User> getPartyParticipants(Long partyId) {
        return null;
    }

    @Override
    public User getUserByLoginData(String email, String password) {
        if(logger.isDebugEnabled()){
            logger.debug(String.format("email = %s", email));
            logger.debug(String.format("password = %s", password));
        }

        User user = null;

        if(email.equals("anton@mail.ru")){
            user = new User();
            user.setUserId(1L);
            user.setUserName("Anton");
            user.setIsGuest(false);
            user.setNewsCount(3L);
            user.setInvitesCount(1L);
            user.setRequestsCount(1L);
            user.setGuestsCount(1L);

            user.setUserEmail("anton@mail.ru");
        }
        return user;
    }

    @Override
    public User getUserById(Long userId) {
        User user =new User();
        user.setUserName("Anton");
        user.setUserId(1L);
        user.setIsGuest(false);
        user.setNewsCount(3L);
        user.setInvitesCount(1L);
        user.setRequestsCount(1L);
        user.setGuestsCount(1L);
        user.setUserEmail("anton@mail.ru");

        return user;
    }

    @Override
    public NewsCounts getNewsCountForUser(Long userId) {
        NewsCounts newsCounts = new NewsCounts();


        Long requestsCount =  Math.round(Math.random() * 20);
        Long guestsCount =  Math.round(Math.random() * 20);
        Long invitesCount =  Math.round(Math.random() * 20);

        Long newsCount = requestsCount + guestsCount + invitesCount;

        newsCounts.setNewsCount(newsCount);
        newsCounts.setGuestsCount(guestsCount);
        newsCounts.setInvitesCount(invitesCount);
        newsCounts.setRequestsCount(requestsCount);

        return newsCounts;
    }

    @Override
    public void addUser(String userName, String userPassword, String email, String userBank) {
        if(logger.isDebugEnabled()){
            logger.debug(String.format("userName = %s", userName));
            logger.debug(String.format("userPassword = %s", userPassword));
            logger.debug(String.format("email = %s", email));
            logger.debug(String.format("userBank = %s", userBank));
        }
    }
}
