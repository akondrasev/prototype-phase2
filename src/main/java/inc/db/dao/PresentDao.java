package inc.db.dao;

import inc.db.model.Present;

import java.util.List;

public interface PresentDao {
    Present getMostVotedPresentForParty(Long partyId);
    List<Present> getPresentsForParty(Long partyId);

    void savePresent(String presentName, Long presentCost, String presentPictureUrl);

    Present getPresent(Long presentId);
}
