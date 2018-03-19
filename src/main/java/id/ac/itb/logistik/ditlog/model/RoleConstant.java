package id.ac.itb.logistik.ditlog.model;

import java.util.HashMap;
import java.util.Map;

public class RoleConstant {
    public static final Long KASUBDIT_PEMERIKSA = 422L;
    public static final Long PEMERIKSA_JASA = 79L;
    public static final Long PEMERIKSA_BARANG = 78L;
    public static final Long KASIE_PEMERIKSA_JASA = 302L;
    public static final Long KASIE_PEMERIKSA_BARANG = 282L;
    public static final Long VENDOR = 118L;
    public static final Map<Long,String> ROLE = new HashMap<Long, String>(){{
        put(422L,"KASUBDIT_PEMERIKSA");
        put(79L,"PEMERIKSA_JASA");
        put(78L,"PEMERIKSA_BARANG");
        put(302L,"KASIE_PEMERIKSA_JASA");
        put(282L,"KASIE_PEMERIKSA_BARANG");
        put(118L,"VENDOR");
    }};
    public static final Map<Long,String> TAG = new HashMap<Long, String>(){{
        put(KASIE_PEMERIKSA_JASA,"JASA");
        put(PEMERIKSA_JASA,"JASA");
        put(KASIE_PEMERIKSA_BARANG,"BARANG");
        put(PEMERIKSA_BARANG,"BARANG");
    }};
}
