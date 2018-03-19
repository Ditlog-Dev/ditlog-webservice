package id.ac.itb.logistik.ditlog.model;

public class UserPayload {

  public Long idUser;
  public Long roleId;
  public String jwtToken;

  public UserPayload(Long idUser, Long roleId, String jwtToken) {
    this.idUser = idUser;
    this.roleId = roleId;
    this.jwtToken = jwtToken;
  }
}
