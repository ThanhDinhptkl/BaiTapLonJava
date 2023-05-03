package interfaces;

import java.util.ArrayList;

import entity.DichVu;

public interface I_DichVu {
	public ArrayList<DichVu> getAllDichVu();
	
	public boolean them(DichVu dv);

	public boolean capNhat(DichVu dv);

	public boolean xoa(String id);
}
