package id.ac.itb.logistik.ditlog.model;

public class UserPayload {

  public Long idUser;
  public Long idResponsibility;
  public String jwtToken;

  public UserPayload(Long idUser, Long idResponsibility, String jwtToken) {
    this.idUser = idUser;
    this.idResponsibility = idResponsibility;
    this.jwtToken = jwtToken;
  }
}
