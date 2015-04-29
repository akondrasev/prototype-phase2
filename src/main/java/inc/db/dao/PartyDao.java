package inc.db.dao;

import inc.db.model.Party;

import java.util.List;

public interface PartyDao {
    //TODO add all necessary methods
    List<Party> getPartiesWithUser(Long userId);
    Party getPartyById(Long partyId);
}
