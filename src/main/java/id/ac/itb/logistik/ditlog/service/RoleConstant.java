package id.ac.itb.logistik.ditlog.service;

import java.util.HashMap;

public class RoleConstant {
    public static final HashMap<Long,String> ROLE = new HashMap<Long, String>(){{
        put(422L,"KASUBDIT_PEMERIKSA");
        put(79L,"PEMERIKSA_JASA");
        put(78L,"PEMERIKSA_BARANG");
        put(302L,"KASIE_PEMERIKSA_JASA");
        put(282L,"KASIE_PEMERIKSA_BARANG");
        put(118L,"VENDOR");
    }};
}
