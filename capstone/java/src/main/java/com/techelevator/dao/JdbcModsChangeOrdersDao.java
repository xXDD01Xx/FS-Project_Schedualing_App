package com.techelevator.dao;

import com.techelevator.model.ModChangeOrder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JdbcModsChangeOrdersDao implements ModsChangeOrdersDao
{


    private final JdbcTemplate jdbcTemplate;

    public JdbcModsChangeOrdersDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

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
        SqlRowSet rs = this.jdbcTemplate.queryForRowSet(sql);
        List<ModChangeOrder> modsChangeOrderDaos = new ArrayList<>();
        while(rs.next()) {
            modsChangeOrderDaos.add(mapRowToModsCO(rs));
        }
        return modsChangeOrderDaos;
    }

    @Override
    public void addToModsCO(int projectId)
    {
        String sql = "INSERT INTO mods_changes " +
                "(mod_co_name, project_id, type, court_date, schedule_impacted, " +
                "budget_impacted, why, approved, approv_datetm) " +
                "VALUES (?,?,?,?,?,?,?,?,?);";
        jdbcTemplate.update(sql, projectId);
    }

    @Override
    public void updateModsCO(ModChangeOrder modChangeOrder)
    {
        String sql = "UPDATE mods_changes " +
                "SET mod_co_name = ?, project_id = ?, type = ?, court_date = ?, schedule_impacted, " +
                "budget_impacted  = ?, why = ?, approved = ?, approv_datetm = ?;";
        jdbcTemplate.update(sql, modChangeOrder.getModCoName(), modChangeOrder.getProjectId(), modChangeOrder.getType(),
                modChangeOrder.getCourtDate(), modChangeOrder.isScheduleImpacted(), modChangeOrder.isBudgetImpacted(),
                modChangeOrder.getWhy(), modChangeOrder.isApprovedStatus(), modChangeOrder.getApproveDateTm());
    }

    @Override
    public void deleteModsChangeCO(int id)
    {
        String sql = "DELETE FROM mods_changes WHERE id = ?;";
        jdbcTemplate.update(sql, id);
    }

    private ModChangeOrder mapRowToModsCO(SqlRowSet rs)
    {
        ModChangeOrder modChangeOrder = new ModChangeOrder();
        modChangeOrder.setId(rs.getInt("id"));
        modChangeOrder.setModCoName(rs.getString("mod_co_name"));
        modChangeOrder.setProjectId(rs.getInt("project_id"));
        modChangeOrder.setType(rs.getString("type"));
        modChangeOrder.setCourtDate(rs.getDate("court_date").toLocalDate());
        modChangeOrder.setScheduleImpacted(rs.getBoolean("schedule_impacted"));
        modChangeOrder.setBudgetImpacted(rs.getBoolean("budget_impacted"));
        modChangeOrder.setWhy(rs.getString("why"));
        modChangeOrder.setApprovedStatus(rs.getBoolean("approved"));
        modChangeOrder.setApproveDateTm(LocalDateTime.from(rs.getDate("aprrov_datetm").toLocalDate()));
        return modChangeOrder;
    }
}
