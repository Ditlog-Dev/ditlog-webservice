package id.ac.itb.logistik.ditlog.filter;

import id.ac.itb.logistik.ditlog.model.User;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import id.ac.itb.logistik.ditlog.service.TokenAuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.GenericFilterBean;


public class JwtFilter extends GenericFilterBean {

  public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
    final HttpServletRequest request = (HttpServletRequest) req;
    final HttpServletResponse response = (HttpServletResponse) res;

    User user = TokenAuthenticationService.getAuthenticateUser(request);
    if(user == null){
      response.sendError(HttpStatus.UNAUTHORIZED.value(), "Invalid authentication");
    }
    req.setAttribute("user",user);

    chain.doFilter(req, res);
  }
}
