package inc.db.dao.impl;

import inc.db.dao.PresentDao;
import inc.db.model.Present;

import java.util.ArrayList;
import java.util.List;

public class PresentDaoImpl implements PresentDao {

    private static final Present present;

    static {
        present = new Present();
        present.setCurrentMoneyState(100L);
        present.setPictureUrl("https://redstarresume.files.wordpress.com/2010/08/present.jpg");
        present.setPresentCost(200L);

        present.setPresentId(1L);
        present.setPresentName("Present Box");
    }

    @Override
    public Present getMostVotedPresentForParty(Long partyId) {
        present.setPresentForPartyId(partyId);
        return present;
    }

    @Override
    public List<Present> getPresentsForParty(Long partyId) {
        List<Present> presents = new ArrayList<>();

        present.setPresentForPartyId(partyId);

        presents.add(present);

        return presents;
    }

    @Override
    public void savePresent(String presentName, Long presentCost, String presentPictureUrl) {

    }

    @Override
    public Present getPresent(Long presentId) {
        return present;
    }
}
