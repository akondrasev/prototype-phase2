package inc.db.dao;

import inc.db.model.Present;

public interface PresentDao {
    Present getMostVotedPresentForParty(Long partyId);

    void savePresent(String presentName, Long presentCost, String presentPictureUrl);
}
