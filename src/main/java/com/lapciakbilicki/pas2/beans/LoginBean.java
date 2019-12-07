package com.lapciakbilicki.pas2.beans;
//
//import javax.ejb.Stateless;
//import javax.faces.application.FacesMessage;
//import javax.faces.context.FacesContext;
//import javax.inject.Named;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//
//@Stateless
//@Named
//public class LoginBean {
//    private String username;
//    private String password;
//
//    public String login() {
//        FacesContext context = FacesContext.getCurrentInstance();
//        HttpServletRequest request = (HttpServletRequest)
//                context.getExternalContext().getRequest();
//        try {
//            request.login(this.username, this.password);
//        } catch (ServletException e) {
//            context.addMessage(null, new FacesMessage("Login failed."));
//            return "error";
//        }
//        return "/pas2/faces/createAccount.xhtml";
//    }
//
//    public void logout() {
//        FacesContext context = FacesContext.getCurrentInstance();
//        HttpServletRequest request = (HttpServletRequest)
//                context.getExternalContext().getRequest();
//        try {
//            request.logout();
//        } catch (ServletException e) {
//            context.addMessage(null, new FacesMessage("Logout failed."));
//        }
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//}


//
import com.lapciakbilicki.pas2.model.account.Account;
import com.lapciakbilicki.pas2.service.AccountService;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.Password;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

import static javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters.withParams;

@Named
@RequestScoped
public class LoginBean {

    @Inject
    private SecurityContext securityContext;

    @Inject
    private AccountService accountService;

    @NotNull
    private String username;

    @NotNull
    private String password;

    private String message;

    public void login() {
        Credential credential = new UsernamePasswordCredential(
                this.username, new Password(this.password));
        AuthenticationStatus status = securityContext
                .authenticate(
                        (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest(),
                        (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse(),
                        withParams().credential(credential)
        );
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}