package inc.db.dao;

import inc.db.model.NewsCounts;
import inc.db.model.User;

import java.util.List;
import java.util.Map;

public interface UserDao {
    Long getAllUsersCount();
    List<User> getPartyOrganizers(Long partyId);
    List<User> getPartyParticipants(Long partyId);
    User getUserByLoginData(String email, String password);
    User getUserById(Long userId);

    void editUser(User user) throws IllegalAccessException;

    NewsCounts getNewsCountForUser(Long userId);

    void addUser(String userName, String userPassword, String email, String userBank);
}
