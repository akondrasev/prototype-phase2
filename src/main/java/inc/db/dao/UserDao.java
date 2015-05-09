package inc.db.dao;

import inc.db.model.User;

import java.util.List;

public interface UserDao {
    Long getAllUsersCount();
    List<User> getPartyOrganizers(Long partyId);
    List<User> getPartyParticipants(Long partyId);
    User getUserByLoginData(String email, String password);
    User getUserById(Long userId);

    void addUser(String userName, String userPassword, String email, String userBank);
}
