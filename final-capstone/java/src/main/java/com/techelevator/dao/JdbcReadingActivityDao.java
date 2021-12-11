package com.techelevator.dao;

import com.techelevator.model.ReadingActivity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public class JdbcReadingActivityDao implements ReadingActivityDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcReadingActivityDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public ReadingActivity getActivity(Long activityId) {
        ReadingActivity activity = null;
        String sql = "SELECT activity_id, activity_date, reading_format, reading_minutes, member_id, book_id, reader_notes" +
                " FROM reading_activity" +
                " WHERE reading_activity.activity_id = ?;";
    }

    @Override
    public void addActivity(ReadingActivity activityToAdd) {}

    @Override
    public void updateActivity(ReadingActivity updatedActivity) {}

    @Override
    public void deleteActivity(Long activityId) {}

    @Override
    public Long getReadingMinutes(Long memberId) {}

    @Override
    public List<ReadingActivity> getListOfActivities(Long memberId) {}


    private ReadingActivity mapRowToReadingActivity(SqlRowSet results) {}


}
