package inc.db.dao.impl;

import inc.db.dao.PresentDao;
        import inc.db.model.Present;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServerPresentDaoImpl implements PresentDao {
    private DataSource dataSource;
    public void setDataSource(javax.sql.DataSource dataSource){
        this.dataSource = dataSource;
    }
    @Override
    public Present getMostVotedPresentForParty(Long partyId) {
        String sql = "SELECT present.*, Count(vote.user_id) AS voteCount FROM present LEFT JOIN vote ON present.PRESENT_ID = vote.present_id\n" +
                "GROUP BY present.PRESENT_ID\n" +
                "HAVING PRESENT_FOR_PARTY = "+partyId+"\n" +
                "ORDER BY voteCount DESC limit 1";
        Connection conn = null;
        try{
            conn = dataSource.getConnection();
            Statement s = conn.createStatement();
            Present p = null;
            ResultSet rs = s.executeQuery(sql);
            if(rs.next()){
                p = new Present();
                p.setPictureUrl(rs.getString("PRESENT_PICTURE_URL"));
                p.setPresentCost(rs.getLong("PRESENT_COST"));
                p.setPresentForPartyId(rs.getLong("PRESENT_FOR_PARTY"));
                p.setPresentId(rs.getLong("PRESENT_ID"));
                p.setPresentName(rs.getString("PRESENT_NAME"));
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
    public List<Present> getPresentsForParty(Long partyId) {
        String sql = "SELECT * FROM present WHERE PRESENT_FOR_PARTY = "+partyId;
        Connection conn = null;
        try{
            conn = dataSource.getConnection();
            Statement s = conn.createStatement();
            Present p = null;
            ResultSet rs = s.executeQuery(sql);
            ArrayList<Present> list = new ArrayList<>();
            if(rs.next()){
                p = new Present();
                p.setPictureUrl(rs.getString("PRESENT_PICTURE_URL"));
                p.setPresentCost(rs.getLong("PRESENT_COST"));
                p.setPresentForPartyId(rs.getLong("PRESENT_FOR_PARTY"));
                p.setPresentId(rs.getLong("PRESENT_ID"));
                p.setPresentName(rs.getString("PRESENT_NAME"));
                list.add(p);
            }
            rs.close();
            s.close();
            return list;
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
    public void savePresent(String presentName, Long presentCost, String presentPictureUrl, Long partyId) {
        String sql = "INSERT INTO present (PRESENT_NAME, PRESENT_COST, PRESENT_FOR_PARTY, PRESENT_PICTURE_URL) VALUES (?,?,?,?)";
        Connection conn = null;

        try{
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "PRESENT_NAME");
            ps.setInt(2, Integer.parseInt("PRESENT_COST"));
            ps.setInt(3, Integer.parseInt("PRESENT_FOR_PARTY"));
            ps.setString(4, "PRESENT_PICTURE_URL");
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
    public Present getPresent(Long presentId) {
        String sql = "SELECT * FROM present WHERE PRESENT_ID = " + presentId;
        Connection conn = null;
        try{
            conn = dataSource.getConnection();
            Statement s = conn.createStatement();
            Present p = null;
            ResultSet rs = s.executeQuery(sql);
            if(rs.next()){
                p = new Present();
                p.setPictureUrl(rs.getString("PRESENT_PICTURE_URL"));
                p.setPresentCost(rs.getLong("PRESENT_COST"));
                p.setPresentForPartyId(rs.getLong("PRESENT_FOR_PARTY"));
                p.setPresentId(rs.getLong("PRESENT_ID"));
                p.setPresentName(rs.getString("PRESENT_NAME"));
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
    public void deletePresent(Long presentId) {
        String sql = "DELETE FROM present WHERE PARTY_ID = ?";
        Connection conn = null;
        try{
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, presentId);
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
    public void voteForPresent(Long presentId, Long userId) {
        /*String sql = "INSERT INTO vote (present_id, user_id) VALUES (?,?)";
        Connection conn = null;
        try{
            conn = dataSource.getConnection();

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, presentId);
            ps.setLong(2, userId);
            System.out.println(sql);
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
        }*/
        //TODO voteForPresent
    }

    @Override
    public Long getVotesForPresent(Long presentId) {
        String sql = "SELECT present.PRESENT_ID , Count(vote.user_id) AS voteCount FROM present LEFT JOIN vote ON present.PRESENT_ID = vote.present_id\n" +
                "GROUP BY present.PRESENT_ID\n" +
                "HAVING present.PRESENT_ID = "+presentId;
        Connection conn = null;
        try{
            conn = dataSource.getConnection();
            Statement s = conn.createStatement();
            int count = 0;
            ResultSet rs = s.executeQuery(sql);
            if(rs.next()){
                count = rs.getInt("voteCount");
            }
            rs.close();
            s.close();
            return Long.valueOf(count);
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
