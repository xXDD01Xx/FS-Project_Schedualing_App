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
public abstract class ModsChangeOrdersController
{

    @Autowired
    ModsChangeOrdersDao modsChangeOrdersDao;


    @GetMapping(path="/")
    public List<ModChangeOrder> listModsCO() {
        return modsChangeOrdersDao.listAllModsChangeOrders();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @PostMapping(path="/")
    public void addToModsCO(@PathVariable int id)
    {

    }

    public void updateModsCO(@PathVariable ModChangeOrder modChangeOrder)
    {

    }

    public void deleteModsCO(@PathVariable int id)
    {

    }
}
