package inc.db.dao.impl;

import inc.db.dao.PresentDao;
import inc.db.model.Present;

public class PresentDaoImpl implements PresentDao {
    @Override
    public Present getMostVotedPresentForParty(Long partyId) {
        Present present = new Present();
        present.setCurrentMoneyState(100L);
        present.setPictureUrl("https://redstarresume.files.wordpress.com/2010/08/present.jpg");
        present.setPresentCost(200L);
        present.setPresentForPartyId(partyId);
        present.setPresentId(1L);
        present.setPresentName("Present Box");

        return present;
    }

    @Override
    public void savePresent(String presentName, Long presentCost, String presentPictureUrl) {

    }
}
