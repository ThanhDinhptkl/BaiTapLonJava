package interfaces;

import java.util.ArrayList;

import entity.ThietBi;

public interface I_ThietBi {
	public ArrayList<ThietBi> getAllThietBi();

	public ThietBi getThietBiTheoMaTB(String id);

	public boolean them(ThietBi tb);

	public boolean capNhat(ThietBi tb);

	public boolean xoa(String id);
}
