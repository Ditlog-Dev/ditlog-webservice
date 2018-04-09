package id.ac.itb.logistik.ditlog.utility;

import java.security.MessageDigest;
import javax.xml.bind.DatatypeConverter;

public class Encryption {
  public static String encodeWithMD5(String in) throws Exception {
    MessageDigest md = MessageDigest.getInstance("MD5");
    try {
      md.update(in.getBytes());
      byte[] digest = md.digest();
      return DatatypeConverter.printHexBinary(digest).toLowerCase();
    } catch (Exception se) {
      se.printStackTrace();
      throw new Exception("Exception while encoding with MD5" + se);
    }
  }
}
