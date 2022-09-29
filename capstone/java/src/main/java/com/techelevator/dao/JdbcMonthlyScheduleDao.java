package com.techelevator.dao;


import com.techelevator.model.MonthlyPhaseItem;
import com.techelevator.dao.MonthlyScheduleDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
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
    public void addToMonthlySchedule() {
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
