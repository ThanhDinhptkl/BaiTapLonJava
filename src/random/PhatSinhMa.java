package random;

import java.util.Random;

public class PhatSinhMa {
	public PhatSinhMa() {

	}

	public String randomMaNV() {
		Random random = new Random();
	    int randomNumber = random.nextInt(100000000); // phát sinh số ngẫu nhiên từ 0 đến 99999999
	    String randomCode = String.format("%08d", randomNumber); // chèn các số 0 vào đầu chuỗi nếu cần
	    return randomCode;
	}
	public String randomMaKH() {
		Random random = new Random();
		 int randomNumber = random.nextInt(100000000); // phát sinh số ngẫu nhiên từ 0 đến 99999999
		    String randomCode = String.format("%08d", randomNumber); // chèn các số 0 vào đầu chuỗi nếu cần
		    return randomCode;
	}
	public static void main(String[] args) {

	}

}
