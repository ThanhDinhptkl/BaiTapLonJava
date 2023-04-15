package entity;

import java.util.Objects;

public class TaiKhoan {
	private String maTK;
	private NhanVien nv;
	private String pass;
	private String quyen;

	public String getMaTK() {
		return maTK;
	}

	public void setMaTK(String maTK) {
		this.maTK = maTK;
	}

	public NhanVien getNv() {
		return nv;
	}

	public void setNv(NhanVien nv) {
		this.nv = nv;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getQuyen() {
		return quyen;
	}

	public void setQuyen(String quyen) {
		this.quyen = quyen;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maTK);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaiKhoan other = (TaiKhoan) obj;
		return Objects.equals(maTK, other.maTK);
	}

	public TaiKhoan(String maTK, NhanVien nv, String pass, String quyen) {
		super();
		this.maTK = maTK;
		this.nv = nv;
		this.pass = pass;
		this.quyen = quyen;
	}

	public TaiKhoan(String ma) {
		super();
		this.maTK = ma;
	}

	public TaiKhoan() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "TaiKhoan [maTK=" + maTK + ", nv=" + nv + ", pass=" + pass + ", quyen=" + quyen + "]";
	}

}
