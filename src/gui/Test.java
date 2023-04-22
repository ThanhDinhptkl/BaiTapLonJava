package gui;

import java.time.LocalDate;

import bus.Bus_ChiTietDatPhong;
import bus.Bus_ChiTietSuDungDichVu;
import bus.Bus_DichVu;
import bus.Bus_HoaDon;
import bus.Bus_KhachHang;
import bus.Bus_NhanVien;
import bus.Bus_Phong;
import bus.Bus_Tang;
import bus.Bus_ThietBi;
import entity.ChiTietDatPhong;
import entity.ChiTietSuDungDichVu;
import entity.DichVu;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import entity.Phong;
import entity.Tang;
import entity.ThietBi;

public class Test {

	public static void main(String[] args) {
//		Bus_Tang btang = new Bus_Tang();
		Tang t1 = new Tang(1, "Tầng 1");
		Tang t2 = new Tang(2, "Tầng 2");
		Tang t3 = new Tang(3, "Tầng 3");
		Tang t4 = new Tang(4, "Tầng 4");
		Tang t5 = new Tang(5, "Tầng 5");
//		btang.them(t1);
//		btang.them(t2);
//		btang.them(t3);
//		btang.them(t4);
//		btang.them(t5);
//		System.out.println(btang.getAllTang());
//		System.out.println(btang.getTangTheoMaTang(2));;
//		System.out.println(btang.capNhat(t5));
//		System.out.println(btang.xoa(3));
		
		Bus_Phong bphong = new Bus_Phong();
		Phong p1 = new Phong("100", "VIP", "2 giường đơn", false, t1, 500000);
		Phong p2 = new Phong("101", "VIP", "2 giường đơn", false, t1, 500000);
		Phong p3 = new Phong("200", "BT", "1 giường đơn", false, t2, 300000);
		Phong p4 = new Phong("300", "BT", "2 giường đơn", false, t3, 300000);
		Phong p5 = new Phong("301", "BT", "1 giường đôi", false, t5, 300000);
		
//		System.out.println(bphong.them(p1));
//		System.out.println(bphong.them(p2));
//		System.out.println(bphong.them(p3));
//		System.out.println(bphong.them(p4));
//		System.out.println(bphong.them(p5));
		
//		System.out.println(bphong.getAllPhong());
//		System.out.println(bphong.getPhongTheoMaPhong("101"));
//		System.out.println(bphong.getPhongTheoMaTang(3));
//		System.out.println(bphong.getPhongTheoLoaiPhong("2 giường đơn"));
//		System.out.println(bphong.getPhongTheoTrangThai(false));
//		System.out.println(bphong.capNhat(p5));
//		System.out.println(bphong.xoa("301"));
		
		Bus_ThietBi btb = new Bus_ThietBi();
		ThietBi tb1 = new ThietBi("ML", p1, "Máy lạnh", "công suất 1500w");
		ThietBi tb2 = new ThietBi("Q100", p3, "Quát treo tường", "công suất 100w");
		ThietBi tb3 = new ThietBi("TL01", p1, "Tủ lạnh", "công suất 1000w");
		
//		System.out.println(btb.them(tb1));
//		System.out.println(btb.them(tb2));
//		System.out.println(btb.them(tb3));
		
//		System.out.println(btb.getAllThietBi());
//		System.out.println(btb.getThietBiTheoMaTB("Q100"));
//		System.out.println(btb.capNhat(tb3));
//		System.out.println(btb.xoa("TL01"));
		
		Bus_DichVu bd = new Bus_DichVu();
		DichVu d1 = new DichVu("DV1", "Ăn & Uống", 200000);
		DichVu d2 = new DichVu("DV2", "Giữ xe", 50000);
		DichVu d3 = new DichVu("dv3", "sda", 100000);
		
//		System.out.println(bd.them(d1));
//		System.out.println(bd.them(d2));
//		System.out.println(bd.them(d3));
		
//		System.out.println(bd.getAllDichVu());
//		System.out.println(bd.capNhat(d2));
//		System.out.println(bd.xoa("dV3"));
		
		Bus_KhachHang bk = new Bus_KhachHang();
		KhachHang k1 = new KhachHang("KH001", "Nguyen Van A", "0321546587", "256413547851", "hkdsnah@haksd", true, "632/32 Hoan hoa");
		KhachHang k2 = new KhachHang("KH002", "Nguyen Thi Bé", "0321456587", "458413547851", "dsnah@haksd", false, "652/32 le loi");
		KhachHang k3 = new KhachHang("KH003", "Nguyen Thi Bé", "0321456587", "458413547851", "dsnah@haksd", false, "652/32 le loi");
//		System.out.println(bk.them(k1));
//		System.out.println(bk.them(k2));
//		System.out.println(bk.them(k3));
		
//		System.out.println(bk.getAllKhachHang());
//		System.out.println(bk.getKhachHangTheoMaKH("kh001"));
//		System.out.println(bk.getKhachHangTheoSDT("0321546587"));
//		System.out.println(bk.getKhachHangTheoCCCD("256413547851"));
//		System.out.println(bk.capNhat(k2));
//		System.out.println(bk.xoa("KH003"));
		
		Bus_HoaDon bh = new Bus_HoaDon();
		Bus_NhanVien bnv = new Bus_NhanVien();
		NhanVien nv = bnv.getTheoMaNV("21032351");
		
		HoaDon h1 = new HoaDon("HD1", nv, k1, LocalDate.of(2023, 3, 15));
		
//		System.out.println(bh.them(h1));
		
//		System.out.println(bh.getAllHoaDon());
//		System.out.println(bh.getHoaDonTheoMa("hd1"));
//		System.out.println(bh.getHoaDonTheoMaKH(k1.getMaKH()));
//		System.out.println(bh.getHoaDonTheoMaNV(nv.getMaNV()));
//		System.out.println(bh.capNhat(h1));
//		System.out.println(bh.xoa("hd1"));
		
		Bus_ChiTietSuDungDichVu bctd = new Bus_ChiTietSuDungDichVu();
		
		ChiTietSuDungDichVu ctd1 = new ChiTietSuDungDichVu(h1, d1, 3);
		ChiTietSuDungDichVu ctd2 = new ChiTietSuDungDichVu(h1, d2, 2);
		
//		System.out.println(bctd.them(ctd1));
//		System.out.println(bctd.them(ctd2));
		
//		System.out.println(bctd.getAllChiTietDichVu());
//		System.out.println(bctd.getCTDichVuTheoMaDV(d1.getMaDV()));
//		System.out.println(bctd.getCTDichVuTheoMaHD(h1.getMaHD()));
//		System.out.println(bctd.capNhat(ctd2));
//		System.out.println(bctd.xoa(h1.getMaHD(), d1.getMaDV()));
		
		Bus_ChiTietDatPhong bctdp = new Bus_ChiTietDatPhong();
		
		ChiTietDatPhong ctdp1 = new ChiTietDatPhong(h1, p1, LocalDate.of(2023, 2, 20), LocalDate.of(2023, 4, 15), 2, "");
		ChiTietDatPhong ctdp2 = new ChiTietDatPhong(h1, p2, LocalDate.of(2023, 3, 20), LocalDate.of(2023, 4, 15), 1, "");
	
		
//		System.out.println(bctdp.them(ctdp1));
//		System.out.println(bctdp.them(ctdp2));
		
//		System.out.println(bctdp.getAllCTDatPhong());
//		System.out.println(bctdp.getCTDatPhongTheoMaHD(h1.getMaHD()));
//		System.out.println(bctdp.getCTDatPhongTheoMaPhong(p1.getMaPhong()));
		
//		System.out.println(bctdp.capNhat(ctdp2));
//		System.out.println(bctdp.xoa(h1.getMaHD(), p1.getMaPhong()));
	}

}
