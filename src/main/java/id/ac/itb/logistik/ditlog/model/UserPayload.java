package id.ac.itb.logistik.ditlog.model;

public class UserPayload {
    public Long idUser;
    public String jwtToken;

    public UserPayload(Long idUser, String jwtToken) {
        this.idUser = idUser;
        this.jwtToken = jwtToken;
    }
}
