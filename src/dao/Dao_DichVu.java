package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.DichVu;

import interfaces.I_DichVu;

public class Dao_DichVu implements I_DichVu {
	public Dao_DichVu() {
		
	}

	@Override
	public ArrayList<DichVu> getAllDichVu() {
		ArrayList<DichVu> dsDV = new ArrayList<DichVu>();
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = "Select * from DichVu";
			Statement sta = con.createStatement();

			ResultSet rs = sta.executeQuery(sql);

			while (rs.next()) {
				String maDV = rs.getString("MADV");
				String tenDV = rs.getString("TENDV");
				double gia = rs.getDouble("GIA");

				DichVu dv = new DichVu(maDV, tenDV, gia);
				dsDV.add(dv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsDV;
	}

	@Override
	public boolean them(DichVu dv) {
		PreparedStatement sta = null;
		int n = 0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "insert into DichVu values(?,?,?)";
			sta = con.prepareStatement(sql);

			sta.setString(1, dv.getMaDV());
			sta.setString(2, dv.getTenDV());
			sta.setDouble(3, dv.getGia());
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
	public boolean capNhat(DichVu dv) {
		PreparedStatement sta = null;
		int n = 0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "update DichVu set TENDV = ?, GIA= ? where MADV = ?";
			sta = con.prepareStatement(sql);

			sta.setString(3, dv.getMaDV());
			sta.setString(1, dv.getTenDV());
			sta.setDouble(2, dv.getGia());
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

			String sql = "delete from DichVu where MADV = ?";
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
