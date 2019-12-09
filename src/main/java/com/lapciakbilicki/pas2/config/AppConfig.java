package com.lapciakbilicki.pas2.config;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;


@CustomFormAuthenticationMechanismDefinition(
        loginToContinue = @LoginToContinue(
                loginPage = "/faces/login/login.xhtml",
                errorPage = "/faces/login/failure.xhtml",
                useForwardToLogin = false
        )
)
@FacesConfig
@ApplicationScoped
public class AppConfig {
}