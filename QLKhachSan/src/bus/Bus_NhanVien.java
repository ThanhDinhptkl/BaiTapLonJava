package bus;

import java.util.ArrayList;

import dao.Dao_NhanVien;
import entity.NhanVien;
import interfaces.I_NhanVien;

public class Bus_NhanVien implements I_NhanVien{
	private Dao_NhanVien dao_nv = new Dao_NhanVien();

	@Override
	public ArrayList<NhanVien> getAllNhanVien() {
		return dao_nv.getAllNhanVien();
	}

	@Override
	public NhanVien getTheoMaNV(String maNV) {
		return dao_nv.getTheoMaNV(maNV);
	}

	@Override
	public ArrayList<NhanVien> getTheoHoTen(String hoTen) {
		return null;
	}

	@Override
	public ArrayList<NhanVien> getTheoSDT(String sdt) {
		return dao_nv.getTheoSDT(sdt);
	}

	@Override
	public ArrayList<NhanVien> getTheoPhai(Boolean phai) {
		return null;
	}

	@Override
	public ArrayList<NhanVien> getTheoTuoi(int tuoi) {
		return null;
	}

	@Override
	public ArrayList<NhanVien> getTheoLuong(float luong) {		
		return null;
	}

	@Override
	public boolean them(NhanVien nv) {		
		return dao_nv.them(nv);
	}

	@Override
	public boolean capNhat(NhanVien nv) {
		return dao_nv.capNhat(nv);
	}

	@Override
	public boolean xoa(String maNV) {
		return dao_nv.xoa(maNV);
	}

}
