package com.lapciakbilicki.pas2.filler;

import com.lapciakbilicki.pas2.model.Role.Role;

import java.util.List;
import java.util.UUID;

public class RoleFiller implements Filler<Role> {

    @Override
    public void autoFill(List<Role> destination) {
        destination.add(new Role(UUID.randomUUID().toString(), "Client", "Client"));
        destination.add(new Role(UUID.randomUUID().toString(), "Resources_Admin", "Resources Admin"));
        destination.add(new Role(UUID.randomUUID().toString(), "User_Admin", "User Admin"));
    }
}
