package th.co.ipassion.bkkegp.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import th.co.ipassion.bkkegp.ConnectionHelper;
import th.co.ipassion.bkkegp.model.MapValue;

public class MethodIDTypeDao {
	public List<MapValue> getDataAll() throws DAOException{
		Connection c = null;
		List<MapValue> output = new ArrayList<MapValue>();
		try {
			c = ConnectionHelper.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM poc.method_id ORDER BY param ASC");
			while (rs.next()) {
				output.add(new MapValue(rs.getString(1), rs.getString(2)));
			}
		}catch (Exception e) {
			throw new DAOException(e);
		} finally {
			ConnectionHelper.close(c);
		}
		return output;
	}
}
