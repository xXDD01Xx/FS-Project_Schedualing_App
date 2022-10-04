package com.techelevator.controller;


import com.techelevator.dao.ModsChangeOrdersDao;
import com.techelevator.model.ModChangeOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class ModsChangeOrdersController {

    @Autowired
    ModsChangeOrdersDao modsChangeOrdersDao;


    @GetMapping(path = "/modco")
    public List<ModChangeOrder> listModsCO() {
        return modsChangeOrdersDao.listAllModsChangeOrders();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @PostMapping(path = "/modco/new")
    public int addToModsCO(@RequestBody ModChangeOrder modChangeOrder) {
        return modsChangeOrdersDao.addToModsCO(modChangeOrder);

    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @PostMapping(path = "/modco/update")
    public void updateModsCO(@RequestBody ModChangeOrder modChangeOrder) {
        modsChangeOrdersDao.updateModsCO(modChangeOrder);

    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @DeleteMapping(path = "/modco/delete")
    public void deleteModsCO(@PathVariable int id) {
        modsChangeOrdersDao.deleteModsChangeCO(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @PutMapping(path = "/modco/{id}/changeStatus")
    public boolean changeApprovedStatus(@PathVariable int id, @RequestBody boolean status) {
        return modsChangeOrdersDao.changeApprovedStatus(id, status);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping(path = "/modco/getModCO/{id}")
    public ModChangeOrder getSingleModCO(@PathVariable int id) {
        return modsChangeOrdersDao.getSingleModCO(id);
    }
}
