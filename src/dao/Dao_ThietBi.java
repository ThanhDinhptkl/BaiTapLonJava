package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.Phong;
import entity.ThietBi;
import interfaces.I_ThietBi;

public class Dao_ThietBi implements I_ThietBi {
	public Dao_ThietBi() {

	}

	@Override
	public ArrayList<ThietBi> getAllThietBi() {
		ArrayList<ThietBi> dsTB = new ArrayList<ThietBi>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();

			String sql = "Select * from ThietBi";
			Statement sta = con.createStatement();

			ResultSet rs = sta.executeQuery(sql);

			while (rs.next()) {
				String maTB = rs.getString("MATB");
				Phong phong = new Phong(rs.getString("MAPHONG"));
				String tenTB = rs.getString("TENTB");
				String moTa = rs.getString("MOTA");

				ThietBi tb = new ThietBi(maTB, phong, tenTB, moTa);
				dsTB.add(tb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsTB;
	}

	@Override
	public ThietBi getThietBiTheoMaTB(String id) {
		ThietBi tb = null;
		PreparedStatement sta = null;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from ThietBi where MATB = ?";
			sta = con.prepareStatement(sql);
			sta.setString(1, id);

			ResultSet rs = sta.executeQuery();
			while (rs.next()) {
				String maTB = rs.getString("MATB");
				Phong phong = new Phong(rs.getString("MAPHONG"));
				String tenTB = rs.getString("TENTB");
				String moTa = rs.getString("MOTA");

				tb = new ThietBi(maTB, phong, tenTB, moTa);
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
		return tb;
	}

	@Override
	public boolean them(ThietBi tb) {
		PreparedStatement sta = null;
		int n = 0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "insert into ThietBi values(?,?,?,?)";
			sta = con.prepareStatement(sql);

			sta.setString(1, tb.getMaTB());
			sta.setString(2, tb.getPhong().getMaPhong());
			sta.setString(3, tb.getTenTB());
			sta.setString(4, tb.getMoTa());
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
	public boolean capNhat(ThietBi tb) {
		PreparedStatement sta = null;
		int n = 0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "update ThietBi set MAPHONG = ?, TENTB = ?, MOTA = ? where MATB = ?";
			sta = con.prepareStatement(sql);

			sta.setString(1, tb.getPhong().getMaPhong());
			sta.setString(2, tb.getTenTB());
			sta.setString(3, tb.getMoTa());
			sta.setString(4, tb.getMaTB());
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
	public boolean xoa(String id) {
		PreparedStatement sta = null;
		int n = 0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();

			String sql = "delete from ThietBi where MATB = ?";
			sta = con.prepareStatement(sql);

			sta.setString(1, id);
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

}
