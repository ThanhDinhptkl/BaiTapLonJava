package gui;

import bus.Bus_NhanVien;
import entity.NhanVien;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Bus_NhanVien nv = new Bus_NhanVien();
		NhanVien nvmoi = new NhanVien("21032352", "Đoàn Thục Nhi", "0365645684", false, 19, 20000000);
		System.out.println(nv.capNhat(nvmoi));
	}

}
