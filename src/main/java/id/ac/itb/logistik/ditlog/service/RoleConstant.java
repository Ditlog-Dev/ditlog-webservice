package id.ac.itb.logistik.ditlog.service;

import java.util.HashMap;

public class RoleConstant {
    public static final HashMap<Long,String> ROLE = new HashMap<Long, String>(){{
        put(Long.valueOf(422),"KASUBDIT_PEMERIKSA");
        put(Long.valueOf(79),"PEMERIKSA_JASA");
        put(Long.valueOf(78),"PEMERIKSA_BARANG");
        put(Long.valueOf(302),"KASIE_PEMERIKSA_JASA");
        put(Long.valueOf(282),"KASIE_PEMERIKSA_BARANG");
        put(Long.valueOf(118),"VENDOR");
    }};
}
