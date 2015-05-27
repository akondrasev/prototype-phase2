package inc.db.dao;

import inc.db.model.NewsCounts;
import inc.db.model.User;
import inc.db.model.UserAndPartyLink;

import java.util.List;
import java.util.Map;

public interface UserDao {
    Long getAllUsersCount();

    User getUserByLoginData(String email, String password);
    User getUserById(Long userId);

    void editUser(User user) throws IllegalAccessException;

    NewsCounts getNewsCountForUser(Long userId);

    void addUser(String userName, String userPassword, String email, String userBank);

    List<User> getAllUsers(Long notInPartyId);
    List<User> getGuestsForParty(Long partyId);

    void removeGuestFromParty(Long personId, Long partyId);


    void addGuestToParty(Long userId, Long partyId);

    List<UserAndPartyLink> getRequestsForUser(Long userId);

    void sendRequestForParty(Long partyId, Long userId);
    void deleteRequestFromUser(Long partyId, Long userId, Boolean isAccepted);
}
