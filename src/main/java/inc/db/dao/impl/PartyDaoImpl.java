package inc.db.dao.impl;

import inc.Utils;
import inc.db.dao.PartyDao;
import inc.db.model.Party;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PartyDaoImpl implements PartyDao {

    private static final ArrayList<Party> parties;

    static{
        parties = new ArrayList<Party>();

        Party p1 = new Party();
        p1.setPartyAddress("Tallinn, Akadeemia tee 5 - 51");
        p1.setPartyDate(null);
        p1.setPartyDefaultMoney(100L);
        p1.setPartyId(1L);
        p1.setPartyName("Birthday");
        p1.setPartyOrganizerId(1L);
        p1.setPartyOrganizerName("Anton");

        p1.setPartyDate(Utils.formatDate(new Date()));

        Party p2 = new Party();
        p2.setPartyAddress("Tallinn, Oismae tee 61 - 17");
        p2.setPartyDate(null);
        p2.setPartyDefaultMoney(25L);
        p2.setPartyId(2L);
        p2.setPartyName("Birthday");
        p2.setPartyOrganizerId(1L);
        p2.setPartyOrganizerName("Anton");

        p2.setPartyDate(Utils.formatDate(new Date()));

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

    @Override
    public void createEvent(Party party) {

    }
}
