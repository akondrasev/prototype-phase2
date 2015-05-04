package inc.db.dao.impl;

import inc.db.dao.PartyDao;
import inc.db.model.Party;

import java.util.ArrayList;
import java.util.List;

public class PartyDaoImpl implements PartyDao {

    private static final ArrayList<Party> parties;

    static{
        parties = new ArrayList<Party>();

        Party p1 = new Party();
        p1.setPartyAddress("Tallinn, Aka tee 5");
        p1.setPartyDate(null);
        p1.setPartyDefaultMoney(100L);
        p1.setPartyId(1L);
        p1.setPartyName("Birthday");
        p1.setPartyOrganizerId(1L);
        p1.setPartyOrganizerName("Anton");

        Party p2 = new Party();
        p2.setPartyAddress("Tallinn, Aka tee 5");
        p2.setPartyDate(null);
        p2.setPartyDefaultMoney(100L);
        p2.setPartyId(1L);
        p2.setPartyName("Birthday");
        p2.setPartyOrganizerId(1L);
        p2.setPartyOrganizerName("Anton");

        parties.add(p1);
        parties.add(p2);
    }

    @Override
    public List<Party> getPartiesWithUser(Long userId) {
        return parties;
    }

    @Override
    public Party getPartyById(Long partyId) {
        return parties.get(0);
    }

    @Override
    public List<Party> getOpenedParties() {
        return parties;
    }
}
