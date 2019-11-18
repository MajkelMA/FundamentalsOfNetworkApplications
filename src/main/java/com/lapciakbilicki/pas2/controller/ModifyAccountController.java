package com.lapciakbilicki.pas2.controller;

import com.lapciakbilicki.pas2.model.account.Account;
import com.lapciakbilicki.pas2.model.account.AdminAccount;
import com.lapciakbilicki.pas2.model.account.ClientAccount;
import com.lapciakbilicki.pas2.model.account.ResourcesManagerAccount;
import com.lapciakbilicki.pas2.service.AccountService;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Named
@RequestScoped
public class ModifyAccountController implements Serializable {
    private String dropDownText;
    public void setDropDownText(String dropDownText){ this.dropDownText = dropDownText; }
    public String getDropDownText() { return this.dropDownText; }

    public List<String> setDropDownText(){
        return new ArrayList<String>(Arrays.asList("true", "false"));
    }
}
