package bus;

import java.util.ArrayList;

import dao.Dao_NhanVien;
import entity.NhanVien;
import interfaces.I_NhanVien;

public class Bus_NhanVien implements I_NhanVien{
	private Dao_NhanVien dao_nv = new Dao_NhanVien();

	@Override
	public ArrayList<NhanVien> getAllNhanVien() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<NhanVien> getTheoMaNV(String maNV) {
		// TODO Auto-generated method stub
		return dao_nv.getTheoMaNV(maNV);
	}

	@Override
	public ArrayList<NhanVien> getTheoHoTen(String hoTen) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<NhanVien> getTheoSDT(String sdt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<NhanVien> getTheoPhai(Boolean phai) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<NhanVien> getTheoTuoi(int tuoi) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<NhanVien> getTheoLuong(float luong) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean them(NhanVien nv) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean capNhat(NhanVien nv) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean xoa(String maNV) {
		// TODO Auto-generated method stub
		return false;
	}

}
