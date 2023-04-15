package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.ChiTietSuDungDichVu;
import entity.DichVu;
import entity.HoaDon;
import interfaces.I_ChiTietSuDungDichVu;

public class Dao_ChiTietSuDungDichVu implements I_ChiTietSuDungDichVu {
	public Dao_ChiTietSuDungDichVu() {

	}

	@Override
	public ArrayList<ChiTietSuDungDichVu> getAllChiTietDichVu() {
		ArrayList<ChiTietSuDungDichVu> dsCT = new ArrayList<ChiTietSuDungDichVu>();
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = "Select * from ChiTietSuDungDichVu";
			Statement sta = con.createStatement();

			ResultSet rs = sta.executeQuery(sql);

			while (rs.next()) {
				HoaDon hoaDon = new HoaDon(rs.getString("MAHD"));
				DichVu dichVu = new DichVu(rs.getString("MADV"));
				int soLuong = rs.getInt("SOLUONG");

				ChiTietSuDungDichVu ct = new ChiTietSuDungDichVu(hoaDon, dichVu, soLuong);
				dsCT.add(ct);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsCT;
	}

	@Override
	public ArrayList<ChiTietSuDungDichVu> getCTDichVuTheoMaHD(String idHD) {
		ArrayList<ChiTietSuDungDichVu> dsCT = new ArrayList<ChiTietSuDungDichVu>();
		PreparedStatement sta = null;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from ChiTietSuDungDichVu where MAHD = ?";
			sta = con.prepareStatement(sql);
			sta.setString(1, idHD);

			ResultSet rs = sta.executeQuery();
			while (rs.next()) {
				HoaDon hoaDon = new HoaDon(rs.getString("MAHD"));
				DichVu dichVu = new DichVu(rs.getString("MADV"));
				int soLuong = rs.getInt("SOLUONG");

				ChiTietSuDungDichVu ct = new ChiTietSuDungDichVu(hoaDon, dichVu, soLuong);
				dsCT.add(ct);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				sta.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return dsCT;
	}

	@Override
	public ArrayList<ChiTietSuDungDichVu> getCTDichVuTheoMaDV(String idDV) {
		ArrayList<ChiTietSuDungDichVu> dsCT = new ArrayList<ChiTietSuDungDichVu>();
		PreparedStatement sta = null;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from ChiTietSuDungDichVu where MADV = ?";
			sta = con.prepareStatement(sql);
			sta.setString(1, idDV);

			ResultSet rs = sta.executeQuery();
			while (rs.next()) {
				HoaDon hoaDon = new HoaDon(rs.getString("MAHD"));
				DichVu dichVu = new DichVu(rs.getString("MADV"));
				int soLuong = rs.getInt("SOLUONG");

				ChiTietSuDungDichVu ct = new ChiTietSuDungDichVu(hoaDon, dichVu, soLuong);
				dsCT.add(ct);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				sta.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return dsCT;
	}

	@Override
	public boolean them(ChiTietSuDungDichVu dv) {
		PreparedStatement sta = null;
		int n = 0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "insert into ChiTietSuDungDichVu values(?,?,?)";
			sta = con.prepareStatement(sql);

			sta.setString(1, dv.getHoaDon().getMaHD());
			sta.setString(2, dv.getDichVu().getMaDV());
			sta.setInt(3, dv.getSoLuong());
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
	public boolean capNhat(ChiTietSuDungDichVu dv) {
		PreparedStatement sta = null;
		int n = 0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "update ChiTietSuDungDichVu set SOLUONG = ? where MAHD = ? and MADV = ?";
			sta = con.prepareStatement(sql);

			sta.setInt(1, dv.getSoLuong());
			sta.setString(2, dv.getHoaDon().getMaHD());
			sta.setString(3, dv.getDichVu().getMaDV());		
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
	public boolean xoa(String idHD, String idDV) {
		PreparedStatement sta = null;
		int n = 0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();

			String sql = "delete from ChiTietSuDungDichVu where MAHD = ? and MADV = ?";
			sta = con.prepareStatement(sql);

			sta.setString(1, idHD);
			sta.setString(2, idDV);
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
