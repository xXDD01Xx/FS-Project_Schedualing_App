package com.techelevator.dao;


import com.techelevator.model.MonthlyPhaseItem;
import com.techelevator.dao.MonthlyScheduleDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcMonthlyScheduleDao implements MonthlyScheduleDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcMonthlyScheduleDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<MonthlyPhaseItem> listMonthlyScheduleItems(int monthlyScheduleId) {
        String sql = "SELECT m.id, m.monthly_sched_id, phase_item, item_date, item_tasks, item_description, phase " +
                "FROM monthly_sched_items m " +
                "JOIN phase_items pi on pi.id = m.phase_item;";
        SqlRowSet rs = this.jdbcTemplate.queryForRowSet(sql);
        List<MonthlyPhaseItem> monthlyPhaseItems = new ArrayList<>();
        while (rs.next()) {
            monthlyPhaseItems.add(mapRowToMonthlySchedule(rs));
        }
        return monthlyPhaseItems;
    }

    @Override
    public void addItemToMonthlySchedule() {
    }

    @Override
    public void addNewMonthlySchedule(int projectId, LocalDate monthYear) {
        String sql = "BEGIN; " +
                "" +
                "INSERT INTO monthly_schedule " +
                "(project_id, month_year) " +
                "VALUES " +
                "(?, ?); " +
                "" +
                "INSERT INTO monthly_sched_items " +
                "(monthly_sched_id, phase_item) " +
                "SELECT  " +
                "(SELECT MAX(id) FROM monthly_schedule WHERE project_id = ? AND month_year = ?), " +
                "a.phase_item, a.item_date, a.item_tasks " +
                "FROM all_items_vw a " +
                "LEFT JOIN all_items_vw b ON a.project_id = b.project_id AND " +
                "and a.phase_item=b.phase_item AND " +
                "a.month_year < b.month_year " +
                "WHERE b.month_year IS NULL AND a.project_id = ?; " +
                "" +
                "COMMIT;";

        jdbcTemplate.update(sql, projectId, monthYear, projectId, monthYear, projectId);
    }


    @Override
    public void updateMonthlySchedule() {
    }

    @Override
    public void deleteMonthlySchedule() {
    }


    private MonthlyPhaseItem mapRowToMonthlySchedule(SqlRowSet rs) {
        MonthlyPhaseItem monthlyPhaseItem = new MonthlyPhaseItem();
        monthlyPhaseItem.setId(rs.getInt("id"));
        monthlyPhaseItem.setMonthlyScheduleId(rs.getInt("monthly_sched_id"));
        monthlyPhaseItem.setPhaseItemId(rs.getInt("phase_item"));
        if (rs.getDate("item_date") != null) {
            monthlyPhaseItem.setItemDate(rs.getDate("item_date").toLocalDate());
        }
        monthlyPhaseItem.setItemTasks(rs.getInt("item_tasks"));
        return monthlyPhaseItem;
    }

}
