package inc.db.dao;

import inc.db.model.Present;

import java.util.List;

public interface PresentDao {
    Present getMostVotedPresentForParty(Long partyId);
    List<Present> getPresentsForParty(Long partyId);

    void savePresent(String presentName, Long presentCost, String presentPictureUrl, Long partyId);

    Present getPresent(Long presentId);

    void deletePresent(Long presentId);

    void voteForPresent(Long presentId, Long userId);

    Long getVotesForPresent(Long presentId);
}
