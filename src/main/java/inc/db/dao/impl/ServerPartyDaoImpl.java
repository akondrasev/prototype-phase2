package inc.db.dao.impl;

import inc.db.dao.PartyDao;
import inc.db.model.Party;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServerPartyDaoImpl implements PartyDao {
    private static Logger logger = Logger.getLogger(ServerPartyDaoImpl.class);

    private DataSource dataSource;
    public void setDataSource(javax.sql.DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public List<Party> getPartiesWithUser(Long userId) {
        String sql = "SELECT party.*, user_name FROM party INNER JOIN guest ON party.PARTY_ID = guest.party_id " +
        "LEFT JOIN person ON person.user_id = PARTY_ORGANIZER WHERE (guest.user_id = " + userId + " AND state_id = 3) OR party.PARTY_ORGANIZER =" + userId;
        Connection conn = null;
        try{
            conn = dataSource.getConnection();
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(sql);
            ArrayList<Party> list = new ArrayList<>();
            while(rs.next()){
                Party p = new Party();
                p.setPartyAddress(rs.getString("PARTY_ADDRESS"));
                p.setPartyDate(rs.getString("PARTY_DATE"));
                p.setPartyDefaultMoney(rs.getLong("PARTY_DEFAULT_MONEY"));
                p.setPartyId(rs.getLong("PARTY_ID"));
                p.setPartyName(rs.getString("PARTY_NAME"));
                p.setPartyOrganizerId(rs.getLong("PARTY_ORGANIZER"));
                p.setPartyOrganizerName(rs.getString("USER_NAME"));
                list.add(p);
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
    public Party getPartyById(Long partyId) {
        String sql = "SELECT party.*, user_name FROM party LEFT JOIN person ON user_id = PARTY_ORGANIZER WHERE party.party_id = "+partyId;
        Connection conn = null;

        try{
            conn = dataSource.getConnection();
            Statement s = conn.createStatement();
            Party p = null;
            ResultSet rs = s.executeQuery(sql);
            if(rs.next()){
                p = new Party();
                p.setPartyAddress(rs.getString("PARTY_ADDRESS"));
                p.setPartyDate(rs.getString("PARTY_DATE"));
                p.setPartyDefaultMoney(rs.getLong("PARTY_DEFAULT_MONEY"));
                p.setPartyId(rs.getLong("PARTY_ID"));
                p.setPartyName(rs.getString("PARTY_NAME"));
                p.setPartyOrganizerId(rs.getLong("PARTY_ORGANIZER"));
                p.setPartyOrganizerName(rs.getString("USER_NAME"));
            }
            rs.close();
            s.close();
            return p;
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
    public List<Party> getOpenedParties() {
        String sql = "SELECT party.*, USER_NAME FROM party LEFT JOIN person ON person.USER_ID = party.PARTY_ORGANIZER WHERE PARTY_IS_OPEN = true";
        Connection conn = null;
        try{
            conn = dataSource.getConnection();
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(sql);
            ArrayList<Party> list = new ArrayList<Party>();
            while(rs.next()){
                Party p = new Party();
                p.setPartyAddress(rs.getString("PARTY_ADDRESS"));
                p.setPartyDate(rs.getString("PARTY_DATE"));
                p.setPartyDefaultMoney(rs.getLong("PARTY_DEFAULT_MONEY"));
                p.setPartyId(rs.getLong("PARTY_ID"));
                p.setPartyName(rs.getString("PARTY_NAME"));
                p.setPartyOrganizerId(rs.getLong("PARTY_ORGANIZER"));
                p.setPartyOrganizerName(rs.getString("USER_NAME"));
                list.add(p);
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
        //return parties;
    }

    @Override
    public void updateParty(Party party) {
        String sql = "UPDATE party SET PARTY_NAME = ?, PARTY_ORGANIZER = ?, PARTY_ADDRESS = ?, PARTY_DEFAULT_MONEY = ?, PARTY_IS_OPEN = ?, PARTY_DATE = ?  WHERE PARTY_ID = " + party.getPartyId();
        Connection conn = null;
        try{
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, party.getPartyName());
            ps.setLong(2, party.getPartyOrganizerId());
            ps.setString(3, party.getPartyAddress());
            ps.setLong(4, party.getPartyDefaultMoney());
            ps.setBoolean(5, party.getPartyIsOpen());
            ps.setString(6, party.getPartyDate());
            ps.executeUpdate();
            ps.close();
        }catch(SQLException ex){
            throw new RuntimeException("SQL exception", ex);
        }finally{
            if(conn != null){
                try{
                    conn.close();
                }catch(SQLException ex){}
            }
        }
    }

    @Override
    public Long createDraftEvent() {
        String sql = "INSERT INTO party (PARTY_NAME) VALUES (?)";
        Connection conn = null;
        try{
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "draft");
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
        sql = String.format("SELECT PARTY_ID FROM party where party_name = '%s'", "draft");
        conn = null;

        try{
            conn = dataSource.getConnection();
            Statement s = conn.createStatement();
            long party_id = 0;
            ResultSet rs = s.executeQuery(sql);
            if(rs.next()){
                party_id = rs.getLong("PARTY_ID");
            }
            rs.close();
            s.close();
            return party_id;
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
    public List<Party> getUserParties(Long userId) {
        String sql = String.format(
                "SELECT party.*, user_name FROM party LEFT JOIN person ON person.user_id = party.PARTY_ORGANIZER WHERE party.PARTY_ORGANIZER = %s", userId);
        Connection conn = null;

        try{
            conn = dataSource.getConnection();
            Statement s = conn.createStatement();

            if(logger.isDebugEnabled()){
                logger.debug(String.format("Executing sql: '%s'", sql));
            }

            ResultSet rs = s.executeQuery(sql);
            ArrayList<Party> list = new ArrayList<>();
            while(rs.next()){
                Party p = new Party();
                p.setPartyAddress(rs.getString("PARTY_ADDRESS"));
                p.setPartyDate(rs.getString("PARTY_DATE"));
                p.setPartyDefaultMoney(rs.getLong("PARTY_DEFAULT_MONEY"));
                p.setPartyId(rs.getLong("PARTY_ID"));
                p.setPartyName(rs.getString("PARTY_NAME"));
                p.setPartyOrganizerId(rs.getLong("PARTY_ORGANIZER"));
                p.setPartyOrganizerName(rs.getString("USER_NAME"));
                list.add(p);
            }
            rs.close();
            s.close();
            return list;
        }catch(SQLException ex){
            throw new RuntimeException("Error while loading user parties",ex);
        } finally{
            if(conn != null){
                try{
                    conn.close();
                }catch(SQLException ex){
                    throw new RuntimeException("Error while closing connection",ex);
                }
            }
        }
    }

    @Override
    public void removeParty(Long partyId) {
        String sql = "DELETE FROM party WHERE PARTY_ID = ?";
        Connection conn = null;
        try{
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, partyId);
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
    public Long getPartiesCount() {
        String sql = "SELECT Count(party.PARTY_ID) from party";
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
}
