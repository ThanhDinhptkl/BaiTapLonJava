package entity;

import java.util.ArrayList;
import java.util.Objects;

public class Tang {
	private int maTang;
	private String tenTang;
	private ArrayList<Phong> dsPhong;

	public int getMaTang() {
		return maTang;
	}

	public void setMaTang(int maTang) {
		this.maTang = maTang;
	}

	public String getTenTang() {
		return tenTang;
	}

	public void setTenTang(String tenTang) {
		this.tenTang = tenTang;
	}

	public ArrayList<Phong> getDsPhong() {
		return dsPhong;
	}

	public void setDsPhong(ArrayList<Phong> dsPhong) {
		this.dsPhong = dsPhong;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maTang);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tang other = (Tang) obj;
		return maTang == other.maTang;
	}

	public Tang(int maTang, String tenTang) {
		super();
		this.maTang = maTang;
		this.tenTang = tenTang;
	}

	public Tang(int maTang) {
		super();
		this.maTang = maTang;
	}

	public Tang(int maTang, String tenTang, ArrayList<Phong> dsPhong) {
		super();
		this.maTang = maTang;
		this.tenTang = tenTang;
		this.dsPhong = dsPhong;
	}

	public Tang() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Tang [maTang=" + maTang + ", tenTang=" + tenTang + ", dsPhong=" + dsPhong + "]";
	}

}
