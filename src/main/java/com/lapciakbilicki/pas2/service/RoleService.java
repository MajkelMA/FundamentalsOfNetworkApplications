package com.lapciakbilicki.pas2.service;

import com.lapciakbilicki.pas2.model.Role.Role;
import com.lapciakbilicki.pas2.repository.RoleRepository;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class RoleService extends ServiceAdapter<Role> implements Serializable {

    @Inject
    private RoleRepository roleRepository;

    public RoleService() {

    }

    @PostConstruct
    public void init() {
        this.repository = roleRepository;
    }

}
