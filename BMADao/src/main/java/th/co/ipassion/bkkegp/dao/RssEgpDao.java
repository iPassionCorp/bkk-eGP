package th.co.ipassion.bkkegp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import th.co.ipassion.bkkegp.ConnectionHelper;
import th.co.ipassion.bkkegp.model.RssEgp;

public class RssEgpDao {
	public RssEgp create(RssEgp rssEgp) throws DAOException {
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionHelper.getConnection();
			ps = c.prepareStatement("INSERT INTO poc.rssegp VALUES (nextval('poc.rssegp_seq'), ?, ?, ?, ?, ?, ?, ?)");
			ps.setDate(1, rssEgp.getPublish_date());
			ps.setString(2, rssEgp.getTitle());
			ps.setString(3, rssEgp.getEgp_url());			
			ps.setString(4, rssEgp.getDeptid());
			ps.setString(5, rssEgp.getDeptsubid());
			ps.setString(6, rssEgp.getAnouncetype());
			ps.setString(7, rssEgp.getMethodid());
			
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(e);
		} finally {
			ConnectionHelper.close(c);
		}
		return rssEgp;
	}

	/*
	 * public boolean update(RssEgp RssEgp) throws DAOException { Connection c =
	 * null; try { c = ConnectionHelper.getConnection(); PreparedStatement ps =
	 * c.prepareStatement("UPDATE RssEgp SET name=?, description=?, image=?,
	 * category=?, price=?, qty_in_stock=? WHERE RssEgp_id=?"); ps.setString(1,
	 * RssEgp.getName()); ps.setString(2, RssEgp.getDescription()); ps.setString(3,
	 * RssEgp.getImage()); ps.setString(4, RssEgp.getCategory()); ps.setDouble(5,
	 * RssEgp.getPrice()); ps.setInt(6, RssEgp.getQtyInStock()); ps.setInt(7,
	 * RssEgp.getRssEgpId()); return (ps.executeUpdate() == 1); } catch
	 * (SQLException e) { e.printStackTrace(); throw new DAOException(e); } finally
	 * { ConnectionHelper.close(c); } }
	 */

	public boolean remove(RssEgp rssEgp) throws DAOException {
		Connection c = null;
		try {
			c = ConnectionHelper.getConnection();
			PreparedStatement ps = c.prepareStatement("DELETE FROM RssEgp WHERE id=?");
			ps.setLong(1, rssEgp.getId());
			int count = ps.executeUpdate();
			return (count == 1);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(e);
		} finally {
			ConnectionHelper.close(c);
		}
	}

	public boolean delete(RssEgp rssEgp) throws DAOException {
		return remove(rssEgp);
	}

	public List<RssEgp> getAllEgpInfo() throws DAOException {
		List<RssEgp> list = new ArrayList<RssEgp>();
		Connection c = null;
		try {
			c = ConnectionHelper.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM poc.rssegp ORDER BY publish_date DESC");

			while (rs.next()) {
				list.add(new RssEgp(rs.getLong(1), rs.getDate(2), rs.getString(3), rs.getString(4),
									rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e);
		} finally {
			ConnectionHelper.close(c);
		}
		return list;
	}

	public List<RssEgp> getEgpInfoBySubDeptCodeId(String subDeptCodeId) throws DAOException {
		List<RssEgp> list = new ArrayList<RssEgp>();
		Connection c = null;
		StringBuffer sql = new StringBuffer("SELECT * FROM poc.rssegp WHERE 1=1 ");
		try {
			c = ConnectionHelper.getConnection();
			Statement s = c.createStatement();
			
			sql.append(" AND deptsubid = '" + subDeptCodeId + "' ");
			sql.append(" ORDER BY publish_date DESC");
			
			ResultSet rs = s.executeQuery(sql.toString());

			while (rs.next()) {
				list.add(new RssEgp(rs.getLong(1), rs.getDate(2), rs.getString(3), rs.getString(4),
									rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e);
		} finally {
			ConnectionHelper.close(c);
		}
		return list;
	}	
	
}
