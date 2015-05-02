package inc.db.dao.impl;

import inc.db.dao.UserDao;
import inc.db.model.User;

import java.util.List;

public class UserDaoImpl implements UserDao{
    @Override
    public Long getAllUsersCount() {
        return null;
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
        User user = new User();
        user.setUserName("Anton");
        user.setIsGuest(false);
        user.setNewsCount(3L);
        return user;
    }

    @Override
    public void addUser(String userName, String userPassword, String email, String userBank) {

    }
}
