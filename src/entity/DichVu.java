package entity;

import java.util.Objects;

public class DichVu {
	private String maDV;
	private String tenDV;
	private double gia;

	public String getMaDV() {
		return maDV;
	}

	public void setMaDV(String maDV) {
		this.maDV = maDV;
	}

	public String getTenDV() {
		return tenDV;
	}

	public void setTenDV(String tenDV) {
		this.tenDV = tenDV;
	}

	public double getGia() {
		return gia;
	}

	public void setGia(double gia) {
		this.gia = gia;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maDV);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DichVu other = (DichVu) obj;
		return Objects.equals(maDV, other.maDV);
	}

	public DichVu(String maDV, String tenDV, double gia) {
		super();
		this.maDV = maDV;
		this.tenDV = tenDV;
		this.gia = gia;
	}

	public DichVu(String maDV) {
		super();
		this.maDV = maDV;
	}

	public DichVu() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "DichVu [maDV=" + maDV + ", tenDV=" + tenDV + ", gia=" + gia + "]";
	}

}
