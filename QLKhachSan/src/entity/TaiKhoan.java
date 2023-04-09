package entity;

import java.util.Objects;

public class TaiKhoan {
	private String maTK;
	private String maNV;
	private String pass;
	private String quyen;
	public TaiKhoan(String maTK, String maNV, String pass, String quyen) {
		super();
		this.maTK = maTK;
		this.maNV = maNV;
		this.pass = pass;
		this.quyen = quyen;
	}
	public TaiKhoan(String maTK) {
		this.maTK = maTK;
	}
	public TaiKhoan() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getMaTK() {
		return maTK;
	}
	public void setMaTK(String maTK) {
		this.maTK = maTK;
	}
	public String getMaNV() {
		return maNV;
	}
	public void setMaNV(String maNV) {
		this.maNV = maNV;
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
	@Override
	public String toString() {
		return "TaiKhoan [maTK=" + maTK + ", maNV=" + maNV + ", pass=" + pass + ", quyen=" + quyen + "]";
	}
	
	
}
