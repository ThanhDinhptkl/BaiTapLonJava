package entity;

import java.util.Objects;

public class ThietBi {
	private String maTB;
	private Phong phong;
	private String tenTB;
	private String moTa;
	
	public String getMaTB() {
		return maTB;
	}
	public void setMaTB(String maTB) {
		this.maTB = maTB;
	}
	public Phong getPhong() {
		return phong;
	}
	public void setPhong(Phong phong) {
		this.phong = phong;
	}
	public String getTenTB() {
		return tenTB;
	}
	public void setTenTB(String tenTB) {
		this.tenTB = tenTB;
	}
	public String getMoTa() {
		return moTa;
	}
	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maTB);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ThietBi other = (ThietBi) obj;
		return Objects.equals(maTB, other.maTB);
	}
	public ThietBi(String maTB, Phong phong, String tenTB, String moTa) {
		super();
		this.maTB = maTB;
		this.phong = phong;
		this.tenTB = tenTB;
		this.moTa = moTa;
	}
	public ThietBi(String maTB) {
		super();
		this.maTB = maTB;
	}
	public ThietBi() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "ThietBi [maTB=" + maTB + ", phong=" + phong + ", tenTB=" + tenTB + ", moTa=" + moTa + "]";
	}
	
	
}
