package inc.db.dao;

import inc.Utils;
import inc.db.model.Party;

import java.util.List;

public interface PartyDao {
    List<Party> getPartiesWithUser(Long userId);
    Party getPartyById(Long partyId);
    List<Party> getOpenedParties();
}
