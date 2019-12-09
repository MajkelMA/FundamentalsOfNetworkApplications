package com.lapciakbilicki.pas2.beans;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;

@Named
@SessionScoped
public class LogoutBean implements Serializable {

    public void logout() throws IOException {
       FacesContext context = FacesContext.getCurrentInstance();
       context.getExternalContext().getClientWindow().disableClientWindowRenderMode(context);
       context.getExternalContext().invalidateSession();
       context.getExternalContext().redirect("/pas2/faces/login/login.xhtml");
    }
}
