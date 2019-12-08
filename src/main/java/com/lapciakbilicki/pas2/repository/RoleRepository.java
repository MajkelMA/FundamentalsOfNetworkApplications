package com.lapciakbilicki.pas2.repository;

import com.lapciakbilicki.pas2.filler.RoleFiller;
import com.lapciakbilicki.pas2.model.Role.Role;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;

@ApplicationScoped
public class RoleRepository extends RepositoryAdapter<Role> {

    public RoleRepository(){
        this.setListOfItems(Collections.synchronizedList(new ArrayList<>()));
    }

    @Inject
    @PostConstruct
    public void init(RoleFiller filler){
        this.setFiller(filler);
        this.getFiller().autoFill(this.getListOfItems());
    }

    @Override
    public void update(Role item) {
        Role roleToUpdate = this.get(item.getId());
        if(roleToUpdate != null){
            roleToUpdate.setDescription(item.getDescription());
            roleToUpdate.setName(item.getName());
        }
    }
}

