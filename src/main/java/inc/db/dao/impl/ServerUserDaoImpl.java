package inc.db.dao.impl;

        import inc.Utils;
        import inc.db.dao.UserDao;
        import inc.db.model.NewsCounts;
        import inc.db.model.Party;
        import inc.db.model.User;
        import org.apache.log4j.Logger;

        import javax.sql.DataSource;
        import java.lang.reflect.Field;
        import java.sql.*;
        import java.util.ArrayList;
        import java.util.Date;
        import java.util.List;

public class ServerUserDaoImpl implements UserDao{
    private DataSource dataSource;
    public void setDataSource(DataSource dataSource){
        this.dataSource = dataSource;
    }
    private static Logger logger = Logger.getLogger(ServerUserDaoImpl.class);

    @Override
    public Long getAllUsersCount() {
        String sql = "SELECT Count(USER_ID) AS usersCount FROM person";
        Connection conn = null;

        try{
            conn = dataSource.getConnection();
            Statement s = conn.createStatement();
            Long count = null;
            ResultSet rs = s.executeQuery(sql);
            if(rs.next()){
                count = rs.getLong("usersCount");
            }
            rs.close();
            s.close();
            return count;
        }catch(SQLException ex){
            throw new RuntimeException(ex);
        }finally{
            if(conn != null){
                try{
                    conn.close();
                }catch(SQLException ex){}
            }
        }
    }

    @Override
    public List<User> getPartyOrganizers(Long partyId) {
        String sql = "SELECT  USER_NAME, USER_ID, USER_EMAIL, USER_BANK, i.invCount, r.reqCount, g.guestCount, n.newsCount" +
        "FROM (((person INNER JOIN party ON PARTY_ORGANIZER = USER_ID" +
                "        LEFT JOIN (SELECT party_id, Count(party_id) AS invCount FROM (SELECT * FROM guest WHERE state_id = 1) AS t GROUP BY party_id) AS i ON party.PARTY_ID = i.party_id)" +
                "LEFT JOIN (SELECT party_id, Count(party_id) AS reqCount FROM (SELECT * FROM guest WHERE state_id = 2) AS t GROUP BY party_id) AS r ON party.PARTY_ID = r.party_id)" +
                "LEFT JOIN (SELECT party_id, Count(party_id) AS guestCount FROM (SELECT * FROM guest WHERE state_id = 3) AS t GROUP BY party_id) AS g ON party.PARTY_ID = g.party_id)" +
                "LEFT JOIN (SELECT party_id, Count(party_id) AS newsCount FROM news AS t GROUP BY party_id) AS n ON party.PARTY_ID = n.party_id WHERE party.PARTY_ID = " + partyId;
        Connection conn = null;
        try{
            conn = dataSource.getConnection();
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(sql);
            ArrayList<User> list = new ArrayList<>();
            while(rs.next()){
                User user = new User();
                user.setUserName(rs.getString("USER_NAME"));
                user.setUserId(rs.getLong("USER_ID"));
                user.setIsGuest(rs.getBoolean("false"));
                user.setNewsCount(rs.getLong("newsCount"));
                user.setInvitesCount(rs.getLong("invCount"));
                user.setRequestsCount(rs.getLong("reqCount"));
                user.setGuestsCount(rs.getLong("guestCount"));
                user.setUserEmail(rs.getString("USER_EMAIL"));
                user.setUserBank(rs.getString("USER_BANK"));
                list.add(user);
            }
            rs.close();
            s.close();
            return list;
        }catch(SQLException ex){
            throw new RuntimeException(ex);
        } finally{
            if(conn != null){
                try{
                    conn.close();
                }catch(SQLException ex){}
            }
        }
    }

    @Override
    public List<User> getPartyParticipants(Long partyId) {
        String sql = "SELECT  USER_NAME, person.USER_ID, USER_EMAIL, USER_BANK\n" +
                "FROM person INNER JOIN(SELECT user_id FROM guest WHERE state_id = 3 AND party_id = " + partyId +"1) AS g ON g.user_id = person.USER_ID";
        Connection conn = null;
        try{
            conn = dataSource.getConnection();
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(sql);
            ArrayList<User> list = new ArrayList<User>();
            while(rs.next()){
                User user = new User();
                user.setUserName(rs.getString("USER_NAME"));
                user.setUserId(rs.getLong("USER_ID"));
                user.setIsGuest(rs.getBoolean("false"));
                user.setUserEmail(rs.getString("USER_EMAIL"));
                user.setUserBank(rs.getString("USER_BANK"));
                list.add(user);
            }
            rs.close();
            s.close();
            return list;
        }catch(SQLException ex){
            throw new RuntimeException(ex);
        } finally{
            if(conn != null){
                try{
                    conn.close();
                }catch(SQLException ex){}
            }
        }
    }

    @Override
    public User getUserByLoginData(String email, String password) {
        if(logger.isDebugEnabled()){
            logger.debug(String.format("email = %s", email));
            logger.debug(String.format("password = %s", password));
        }

        String sql = "SELECT USER_NAME, USER_ID, USER_EMAIL, USER_BANK, Count(i.invCount) AS allInv, Count(r.reqCount) AS allReq, Count(g.guestCount) AS allGuests, Count(n.newsCount) AS allNews" +
        " FROM person LEFT JOIN party ON PARTY_ORGANIZER = USER_ID" +
        " LEFT JOIN (SELECT party_id, Count(party_id) AS invCount FROM (SELECT * FROM guest WHERE state_id = 1) AS t GROUP BY party_id) AS i ON party.PARTY_ID = i.party_id" +
        " LEFT JOIN (SELECT party_id, Count(party_id) AS reqCount FROM (SELECT * FROM guest WHERE state_id = 2) AS t GROUP BY party_id) AS r ON party.PARTY_ID = r.party_id" +
        " LEFT JOIN (SELECT party_id, Count(party_id) AS guestCount FROM (SELECT * FROM guest WHERE state_id = 3) AS t GROUP BY party_id) AS g ON party.PARTY_ID = g.party_id" +
        " LEFT JOIN (SELECT party_id, Count(party_id) AS newsCount FROM news AS t GROUP BY party_id) AS n ON party.PARTY_ID = n.party_id" +
        " GROUP BY USER_ID" +
        " HAVING USER_EMAIL = '" + email + "' AND USER_PASSWORD = '" + password + "'";
        Connection conn = null;
        try{
            conn = dataSource.getConnection();
            Statement s = conn.createStatement();
            User user = null;
            ResultSet rs = s.executeQuery(sql);
            if(rs.next()){
                user = new User();
                user.setUserName(rs.getString("USER_NAME"));
                user.setUserId(rs.getLong("USER_ID"));
                user.setIsGuest(false);
                user.setNewsCount(rs.getLong("allNews"));
                user.setInvitesCount(rs.getLong("allInv"));
                user.setRequestsCount(rs.getLong("allReq"));
                user.setGuestsCount(rs.getLong("allGuests"));
                user.setUserEmail(rs.getString("USER_EMAIL"));
                user.setUserBank(rs.getString("USER_BANK"));
            }
            rs.close();
            s.close();
            return user;
        }catch(SQLException ex){
            throw new RuntimeException(ex);
        }finally{
            if(conn != null){
                try{
                    conn.close();
                }catch(SQLException ex){}
            }
        }
    }

    @Override
    public User getUserById(Long userId) {
        String sql = "SELECT USER_NAME, USER_ID, USER_EMAIL, USER_BANK, Count(i.invCount) AS allInv, Count(r.reqCount) AS allReq, Count(g.guestCount) AS allCount, Count(n.newsCount) AS allNews" +
                "FROM person LEFT JOIN party ON PARTY_ORGANIZER = USER_ID" +
                "LEFT JOIN (SELECT party_id, Count(party_id) AS invCount FROM (SELECT * FROM guest WHERE state_id = 1) AS t GROUP BY party_id) AS i ON party.PARTY_ID = i.party_id" +
                "LEFT JOIN (SELECT party_id, Count(party_id) AS reqCount FROM (SELECT * FROM guest WHERE state_id = 2) AS t GROUP BY party_id) AS r ON party.PARTY_ID = r.party_id" +
                "LEFT JOIN (SELECT party_id, Count(party_id) AS guestCount FROM (SELECT * FROM guest WHERE state_id = 3) AS t GROUP BY party_id) AS g ON party.PARTY_ID = g.party_id" +
                "LEFT JOIN (SELECT party_id, Count(party_id) AS newsCount FROM news AS t GROUP BY party_id) AS n ON party.PARTY_ID = n.party_id" +
                "GROUP BY USER_ID HAVING USER_ID = "+userId;
        Connection conn = null;

        try{
            conn = dataSource.getConnection();
            Statement s = conn.createStatement();
            User user = null;
            ResultSet rs = s.executeQuery(sql);
            if(rs.next()){
                user = new User();
                user.setUserName(rs.getString("USER_NAME"));
                user.setUserId(rs.getLong("USER_ID"));
                user.setIsGuest(rs.getBoolean("false"));
                user.setNewsCount(rs.getLong("allNews"));
                user.setInvitesCount(rs.getLong("allInv"));
                user.setRequestsCount(rs.getLong("allReq"));
                user.setGuestsCount(rs.getLong("allGuest"));
                user.setUserEmail(rs.getString("USER_EMAIL"));
                user.setUserBank(rs.getString("USER_BANK"));
            }
            rs.close();
            s.close();
            return user;
        }catch(SQLException ex){
            throw new RuntimeException(ex);
        }finally{
            if(conn != null){
                try{
                    conn.close();
                }catch(SQLException ex){}
            }
        }
    }

    @Override
    public void editUser(User user) throws IllegalAccessException {
        Field[] fields = user.getClass().getDeclaredFields();
        if(logger.isDebugEnabled()){
            for(Field field : fields){
                field.setAccessible(true);

                Object value = field.get(user);
                logger.debug(String.format("%s = %s", field.getName(), value));
            }
        }
        System.out.println(user.getUserId() + " " + user.getUserName() + " " + user.getUserPassword());
        String sql = "UPDATE person SET USER_NAME = ?, USER_EMAIL = ?, USER_PASSWORD = ?, USER_BANK = ? WHERE USER_ID = " + user.getUserId();
        Connection conn = null;

        try{
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getUserEmail());
            ps.setString(3, user.getUserPassword());
            ps.setString(4, user.getUserBank());
            ps.executeUpdate();
            ps.close();
        }catch(SQLException ex){
            throw new RuntimeException(ex);
        }finally{
            if(conn != null){
                try{
                    conn.close();
                }catch(SQLException ex){}
            }
        }

    }

    @Override
    public NewsCounts getNewsCountForUser(Long userId) {
        NewsCounts newsCounts = new NewsCounts();
        String sql = "SELECT  USER_ID, Count(i.invCount) as allInv, Count(r.reqCount) as allReq, Count(g.guestCount) as allGuest, Count(n.newsCount) as allNews" +
                "FROM party INNER JOIN person ON PARTY_ORGANIZER = USER_ID" +
        "LEFT JOIN (SELECT party_id, Count(party_id) AS invCount FROM (SELECT * FROM guest WHERE state_id = 1) AS t GROUP BY party_id) AS i ON party.PARTY_ID = i.party_id" +
        "LEFT JOIN (SELECT party_id, Count(party_id) AS reqCount FROM (SELECT * FROM guest WHERE state_id = 2) AS t GROUP BY party_id) AS r ON party.PARTY_ID = r.party_id" +
        "LEFT JOIN (SELECT party_id, Count(party_id) AS guestCount FROM (SELECT * FROM guest WHERE state_id = 3) AS t GROUP BY party_id) AS g ON party.PARTY_ID = g.party_id" +
        "LEFT JOIN (SELECT party_id, Count(party_id) AS newsCount FROM news AS t GROUP BY party_id) AS n ON party.PARTY_ID = n.party_id"+
        " GROUP BY USER_ID" +
        "HAVING USER_ID =" + userId;
        Connection conn = null;
        int requestsCount = 0, guestsCount = 0, invitesCount = 0;
        try{
            conn = dataSource.getConnection();
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(sql);
            ArrayList<User> list = new ArrayList<User>();
            while(rs.next()){
                requestsCount =  rs.getInt("allReq");
                guestsCount =  rs.getInt("allGuest");
                invitesCount = rs.getInt("allInv");
            }
            rs.close();
            s.close();
        }catch(SQLException ex){
            throw new RuntimeException(ex);
        } finally{
            if(conn != null){
                try{
                    conn.close();
                }catch(SQLException ex){}
            }
        }

        int newsCount = requestsCount + guestsCount + invitesCount;

        newsCounts.setNewsCount(Long.valueOf(newsCount));
        newsCounts.setGuestsCount(Long.valueOf(guestsCount));
        newsCounts.setInvitesCount(Long.valueOf(invitesCount));
        newsCounts.setRequestsCount(Long.valueOf(requestsCount));

        return newsCounts;
    }

    @Override
    public void addUser(String userName, String userPassword, String email, String userBank) {
        if(logger.isDebugEnabled()){
            logger.debug(String.format("userName = %s", userName));
            logger.debug(String.format("userPassword = %s", userPassword));
            logger.debug(String.format("email = %s", email));
            logger.debug(String.format("userBank = %s", userBank));
        }
        String sql = "INSERT INTO person (USER_NAME, USER_EMAIL, USER_PASSWORD, USER_BANK) VALUES (?,?,?,?)";
        Connection conn = null;

        try{
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, userName);
            ps.setString(2, email);
            ps.setString(3, userPassword);
            ps.setString(4, userBank);
            ps.executeUpdate();
            ps.close();
        }catch(SQLException ex){
            throw new RuntimeException(ex);
        }finally{
            if(conn != null){
                try{
                    conn.close();
                }catch(SQLException ex){}
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        String sql = String.format("SELECT * FROM person");
        Connection conn = null;
        try{
            conn = dataSource.getConnection();
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(sql);
            ArrayList<User> list = new ArrayList<User>();
            while(rs.next()){
                User user = new User();
                user.setUserName(rs.getString("USER_NAME"));
                user.setUserId(rs.getLong("USER_ID"));
                user.setUserEmail(rs.getString("USER_EMAIL"));
                user.setUserBank(rs.getString("USER_BANK"));
                list.add(user);
            }
            rs.close();
            s.close();
            return list;
        }catch(SQLException ex){
            throw new RuntimeException(ex);
        } finally{
            if(conn != null){
                try{
                    conn.close();
                }catch(SQLException ex){}
            }
        }
    }

    @Override
    public List<User> getGuestsForParty(Long partyId) {
        String sql = "SELECT  USER_NAME, person.USER_ID, USER_EMAIL, USER_BANK\n" +
                "FROM person INNER JOIN(SELECT user_id FROM guest WHERE state_id = 3 AND party_id = " + partyId +"1) AS g ON g.user_id = person.USER_ID";
        Connection conn = null;
        try{
            conn = dataSource.getConnection();
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(sql);
            ArrayList<User> list = new ArrayList<User>();
            while(rs.next()){
                User user = new User();
                user.setUserName(rs.getString("USER_NAME"));
                user.setUserId(rs.getLong("USER_ID"));
                user.setIsGuest(rs.getBoolean("false"));
                user.setUserEmail(rs.getString("USER_EMAIL"));
                user.setUserBank(rs.getString("USER_BANK"));
                list.add(user);
            }
            rs.close();
            s.close();
            return list;
        }catch(SQLException ex){
            throw new RuntimeException(ex);
        } finally{
            if(conn != null){
                try{
                    conn.close();
                }catch(SQLException ex){}
            }
        }
    }

    @Override
    public void removeGuestFromParty(Long personId, Long partyId) {
        String sql = "DELETE FROM guest WHERE party_id = ? AND user_id = ?";
        Connection conn = null;
        try{
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, partyId);
            ps.setLong(2, personId);
            ps.executeUpdate();
            ps.close();
        }catch(SQLException ex){
            throw new RuntimeException(ex);
        }finally{
            if(conn != null){
                try{
                    conn.close();
                }catch(SQLException ex){}
            }
        }
    }

    @Override
    public void addGuestToParty(Long userId, Long partyId) {

    }

}
