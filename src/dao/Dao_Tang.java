package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.Tang;
import interfaces.I_Tang;

public class Dao_Tang implements I_Tang {
	public Dao_Tang() {

	}

	@Override
	public ArrayList<Tang> getAllTang() {
		ArrayList<Tang> dsTang = new ArrayList<Tang>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();

			String sql = "Select * from Tang";
			Statement sta = con.createStatement();

			ResultSet rs = sta.executeQuery(sql);

			while (rs.next()) {
				int maTang = rs.getInt("MATANG");
				String tenTang = rs.getString("TENTANG");

				Tang t = new Tang(maTang, tenTang);
				dsTang.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsTang;
	}

	@Override
	public Tang getTangTheoMaTang(int id) {
		Tang t = null;
		PreparedStatement sta = null;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from Tang where MATANG = ?";
			sta = con.prepareStatement(sql);
			sta.setInt(1, id);

			ResultSet rs = sta.executeQuery();
			while (rs.next()) {
				int maTang = rs.getInt("MATANG");
				String tenTang = rs.getString("TENTANG");

				t = new Tang(maTang, tenTang);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				sta.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return t;
	}

	@Override
	public boolean them(Tang t) {
		PreparedStatement sta = null;
		int n = 0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "insert into Tang values(?,?)";
			sta = con.prepareStatement(sql);

			sta.setInt(1, t.getMaTang());
			sta.setString(2, t.getTenTang());
			n = sta.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				sta.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return n > 0;
	}

	@Override
	public boolean capNhat(Tang t) {
		PreparedStatement sta = null;
		int n = 0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();

			String sql = "update Tang set TENTANG = ? where MATANG = ?";
			sta = con.prepareStatement(sql);

			sta.setString(1, t.getTenTang());
			sta.setInt(2, t.getMaTang());
			n = sta.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				sta.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return n > 0;
	}

	@Override
	public boolean xoa(int id) {
		PreparedStatement sta = null;
		int n = 0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();

			String sql = "delete from Tang where MATANG = ?";
			sta = con.prepareStatement(sql);

			sta.setInt(1, id);
			n = sta.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				sta.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return n > 0;
	}

	@Override
	public ArrayList<Tang> getTangTheoTenTang(String tenTang) {
		ArrayList<Tang> dsTang = new ArrayList<Tang>();
		PreparedStatement sta = null;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from Tang where TENTANG = ?";
			sta = con.prepareStatement(sql);
			sta.setString(1, tenTang);

			ResultSet rs = sta.executeQuery();
			while (rs.next()) {
				int maTang = rs.getInt("MATANG");
				String tentang = rs.getString("TENTANG");

				Tang t = new Tang(maTang, tenTang);
				dsTang.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				sta.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dsTang;
	}

	@Override
	public int count() {
		PreparedStatement sta = null;
		int n = 0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();

			String sql = "Select * from Tang";
			sta = con.prepareStatement(sql);

			ResultSet rs = sta.executeQuery();
			while(rs.next()) {
				n++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				sta.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return n;
	}

}
