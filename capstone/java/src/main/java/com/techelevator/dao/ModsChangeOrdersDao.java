package com.techelevator.dao;

import com.techelevator.model.ModChangeOrder;

import java.util.List;

public interface ModsChangeOrdersDao
{
    List<ModChangeOrder> listAllModsChangeOrders();
    void addToModsCO(int projectId);
    void updateModsCO(ModChangeOrder modChangeOrder);
    void deleteModsChangeCO(int id);

}
