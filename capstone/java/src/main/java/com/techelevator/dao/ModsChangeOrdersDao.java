package com.techelevator.dao;

import com.techelevator.model.ModChangeOrder;

import java.util.List;

public interface ModsChangeOrdersDao
{
    List<ModChangeOrder> listAllModsChangeOrders();
    Integer addToModsCO(ModChangeOrder modChangeOrder);
    void updateModsCO(ModChangeOrder modChangeOrder);
    void deleteModsChangeCO(int id);

    boolean changeApprovedStatus(int id, boolean status);
    ModChangeOrder getSingleModCO(int id);
}
