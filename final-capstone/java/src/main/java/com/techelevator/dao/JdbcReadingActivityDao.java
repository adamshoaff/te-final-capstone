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
        String sql = "SELECT activity_id, activity_date, reading_format, reading_minutes, member_id, book_id, reader_notes" +
                " FROM reading_activity" +
                " WHERE reading_activity.activity_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, activityId);
        if (results.next()) {
            activity = mapRowToReadingActivity(results);
        }
        return activity;
    }

    @Override
    public void addActivity(ReadingActivity activityToAdd) {
        String sql = "INSERT INTO reading_activity (activity_id, activity_date, reading_format, reading_minutes, member_id, book_Id, reader_notes)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, activityToAdd.getActivityId(), activityToAdd.getActivityDate(), activityToAdd.getReadingFormat(),
                activityToAdd.getReadingMinutes(), activityToAdd.getMemberId(), activityToAdd.getBookId(), activityToAdd.getReaderNotes());
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
    public List<ReadingActivity> getListOfActivities(String username) {
        List<ReadingActivity> activities = new ArrayList<>();
        String sql = "SELECT *" +
                " FROM reading_activity" +
                " JOIN members ON reading_activity.member_id = members.member_id" +
                " JOIN family ON members.family_id = family.family_id" +
                " JOIN users ON members.user_id = users.user_id" +
                " WHERE users.username = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);
        while (results.next()) {
            ReadingActivity newActivity = mapRowToReadingActivity(results);
            activities.add(newActivity);
        }
        return activities;
    }

    private ReadingActivity mapRowToReadingActivity(SqlRowSet results) {
        ReadingActivity activity = new ReadingActivity();
        activity.setActivityId(results.getLong("activity_id"));
        activity.setActivityDate(results.getString("activity_date"));
        activity.setReadingFormat(results.getString("reading_format"));
        activity.setReadingMinutes(results.getLong("reading_minutes"));
        activity.setMemberId(results.getLong("member_id"));
        activity.setBookId(results.getLong("book_id"));
        activity.setReaderNotes(results.getString("reader_notes"));
        return activity;
    }

}
