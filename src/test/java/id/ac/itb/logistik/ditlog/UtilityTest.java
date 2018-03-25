package id.ac.itb.logistik.ditlog;

import id.ac.itb.logistik.ditlog.utility.Encryption;
import org.junit.Assert;
import org.junit.Test;

public class UtilityTest {
  @Test
  public void checkMD5Encryption() throws Exception {
    Assert.assertEquals("202cb962ac59075b964b07152d234b70", Encryption.encodeWithMD5("123"));
  }
}
