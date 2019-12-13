package com.lapciakbilicki.pas2.repository;

import com.lapciakbilicki.pas2.filler.RoleFiller;
import com.lapciakbilicki.pas2.model.Role.Role;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;

@ApplicationScoped
public class RoleRepository extends RepositoryAdapter<Role> implements Serializable {

    @Inject
    private RoleFiller filler;

    public RoleRepository() {
        this.setListOfItems(Collections.synchronizedList(new ArrayList<>()));
    }

    @PostConstruct
    public void init() {
        this.setFiller(filler);
        this.getFiller().autoFill(this.getListOfItems());
    }

    @Override
    public void update(Role item) {
        Role roleToUpdate = this.get(item.getId());
        if (roleToUpdate != null) {
            roleToUpdate.setDescription(item.getDescription());
            roleToUpdate.setName(item.getName());
        }
    }
}
