package com.lapciakbilicki.pas2.controller;

import com.lapciakbilicki.pas2.model.Role.Role;
import com.lapciakbilicki.pas2.service.RoleService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@RequestScoped
@Named
public class RoleController {

    @Inject
    private RoleService roleService;

    public List<Role> getAll(){
        return roleService.getAll();
    }
}
