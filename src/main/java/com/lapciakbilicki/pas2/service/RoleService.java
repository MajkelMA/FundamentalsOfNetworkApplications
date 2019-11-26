package com.lapciakbilicki.pas2.service;

import com.lapciakbilicki.pas2.model.Role.Role;
import com.lapciakbilicki.pas2.repository.RoleRepository;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class RoleService extends ServiceAdapter<Role> {

    public RoleService(){

    }

    @Inject
    @PostConstruct
    public void inti(RoleRepository roleRepository){
        this.repository = roleRepository;
    }

}
