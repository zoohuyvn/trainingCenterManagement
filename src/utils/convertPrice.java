package utils;
import java.text.NumberFormat;

/**
 * @author zoohuy
 * 28 thg 12, 2023
 */

public class convertPrice {
	
	public static String toCurrency(double amount) {
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        return nf.format(amount);
    }
	
//	public static double toNumber(String curString) throws ParseException {
//        NumberFormat nf = NumberFormat.getCurrencyInstance();
//        return nf.parse(curString).doubleValue();
//    }
	
	public static double toNumber2(String curStringNoD) {
		return Double.parseDouble(curStringNoD.replaceAll("\\.", ""));
    }

}