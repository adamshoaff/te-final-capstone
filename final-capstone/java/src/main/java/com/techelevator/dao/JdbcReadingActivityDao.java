package com.techelevator.dao;

import com.techelevator.model.ReadingActivity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        String sql = "SELECT activity_id, activity_date, reading_format, reading_minutes, member_id, book_id, family_id, reader_notes" +
                " FROM reading_activity" +
                " WHERE reading_activity.activity_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, activityId);
        if (results.next()) {
            activity = mapRowToReadingActivity(results);
        }
        return activity;
    }

    @Override
    public ReadingActivity addActivity(ReadingActivity activityToAdd, Long memberId) {
        String sql = "INSERT INTO reading_activity (reading_format, reading_minutes, member_id, book_Id, family_id,reader_notes)" +
                " VALUES (?, ?, ?, ?, ?, ?) RETURNING activity_id;";
        Long activityId = jdbcTemplate.queryForObject(sql, Long.class, activityToAdd.getReadingFormat(),
                activityToAdd.getReadingMinutes(), memberId, activityToAdd.getBookId(),activityToAdd.getFamilyId(), activityToAdd.getReaderNotes());

        return getActivity(activityId);
}

    @Override
    public void updateActivity(ReadingActivity updatedActivity) {
        String sql = "UPDATE reading_activity" +
                " SET activity_date = ?, reading_format = ?, reading_minutes = ?, member_id = ?, book_id = ?, reader_notes = ?" +
                " WHERE activity_id = ?";
        jdbcTemplate.update(sql, updatedActivity.getActivityDate(), updatedActivity.getReadingFormat(), updatedActivity.getReadingMinutes(),
                updatedActivity.getMemberId(), updatedActivity.getBookId(), updatedActivity.getReaderNotes());
    }

    @Override
    public void deleteActivity(Long activityId) {
        String sql = "DELETE FROM reading_activity WHERE activity_id = ?;";
        jdbcTemplate.update(sql, activityId);
    }

    @Override
    public Long getReadingMinutes(Long memberId) {
        String sql = "SELECT reading_minutes FROM reading_activity WHERE activity_id = ?;";
        return jdbcTemplate.queryForObject(sql, Long.class, memberId);
    }

    @Override
    public List<ReadingActivity> getListOfActivities(Long familyId) {
        List<ReadingActivity> activities = new ArrayList<>();
        String sql = "SELECT *" +
                " FROM reading_activity" +
                " JOIN members ON reading_activity.member_id = members.member_id" +
                " JOIN family ON members.family_id = family.family_id" +
                " WHERE family.family_id = ? ORDER BY activity_date desc";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, familyId);
        while (results.next()) {
            ReadingActivity newActivity = mapRowToReadingActivity(results);
            activities.add(newActivity);
        }
        return activities;
    }


    //we added is_completed column to the database schema and isCompleted boolean to Read Activity model - Tay
    public void markBookCompleted(Long activityId){
        String sql = "UPDATE reading_activity SET is_completed = true WHERE activity_id = ?;";
        jdbcTemplate.update(sql, activityId);
    }
    public void markBookIncomplete(Long activityId){
        String sql = "UPDATE reading_activity SET is_completed = false WHERE activity_id = ?;";
        jdbcTemplate.update(sql, activityId);
    }

    private ReadingActivity mapRowToReadingActivity(SqlRowSet results) {
        ReadingActivity activity = new ReadingActivity();
        activity.setActivityId(results.getLong("activity_id"));
        activity.setActivityDate(results.getString("activity_date"));
        activity.setReadingFormat(results.getString("reading_format"));
        activity.setReadingMinutes(results.getLong("reading_minutes"));
        activity.setMemberId(results.getLong("member_id"));
        activity.setBookId(results.getLong("book_id"));
        activity.setFamilyId(results.getLong("family_id"));
        activity.setCompleted(results.getBoolean("is_completed"));
        activity.setReaderNotes(results.getString("reader_notes"));
        return activity;
    }

}
