package gui;

import bus.Bus_TaiKhoan;
import entity.TaiKhoan;

public class test_console {

	public static void main(String[] args) {
		Bus_TaiKhoan bus_tk = new Bus_TaiKhoan();

		System.out.println(bus_tk.getTheoMaTK("tk003"));
		System.out.println(bus_tk.getTheoMaNV("NV002"));
		
		//TaiKhoan tk = new TaiKhoan("TK005", "21032351", "2", "admin");
		//System.out.println(bus_tk.them(tk));
//		System.out.println(bus_tk.capNhat(tk));
//		System.out.println(bus_tk.xoa("TK002"));
	}

}
