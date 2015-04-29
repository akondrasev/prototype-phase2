package inc.db.dao;

import inc.db.model.User;

import java.util.List;

public interface UserDao {
    //TODO add all necessary methods
    Long getAllUsersCount();
    List<User> getPartyOrganizers(Long partyId);
    List<User> getPartyParticipants(Long partyId);
    User getUserByLoginData(String login, String password);
}
