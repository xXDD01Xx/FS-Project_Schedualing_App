package com.techelevator.dao;

import com.techelevator.model.ModChangeOrder;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;

public class JdbcModsChangeOrdersDao implements ModsChangeOrdersDao
{


    @Override
    public List<ModChangeOrder> listAllModsChangeOrders()
    {
        String sql = "SELECT id, " +
                "mods_co_name " +
                "project_id " +
                "type " +
                "court_date " +
                "schedule_impacted " +
                "budget_impacted " +
                "why " +
                "approved " +
                "aprrov_datetm " +
                "FROM mods_changes;";
        SqlRowSet rs = this.jdbcTemplate.queryForRowset(sql);
        List<ModChangeOrder> modsChangeOrderDaos = new ArrayList<>();
        while(rs.next()) {
            modsChangeOrderDaos.add(mapRowToModsCO(rs));
        }
        return modsChangeOrderDaos;
    }

    @Override
    public void addToModsCO(int id)
    {
//        String sql INSERT INTO mods_changes

    }

    @Override
    public void updateModsCO(ModChangeOrder ModChangeOrder)
    {

    }

    @Override
    public void deleteModsChangeCO(int id)
    {

    }
}
