package inc.db.dao;

import inc.db.model.Party;

import java.util.List;

public interface PartyDao {
    List<Party> getPartiesWithUser(Long userId);
    Party getPartyById(Long partyId);
    List<Party> getOpenedParties();

    Long getPartiesCount();

    void updateParty(Party party);

    Long createDraftEvent();

    List<Party> getUserParties(Long userId);

    void removeParty(Long partyId);
}
