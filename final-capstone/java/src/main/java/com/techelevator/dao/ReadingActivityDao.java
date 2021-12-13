package com.techelevator.dao;

import com.techelevator.model.ReadingActivity;

import java.util.List;

public interface ReadingActivityDao {

    ReadingActivity getActivity(Long activityId);

    ReadingActivity addActivity(ReadingActivity activityToAdd, Long memberId);

    void updateActivity(ReadingActivity updatedActivity);

    void deleteActivity(Long activityId);

    Long getReadingMinutes(Long memberId);

    List<ReadingActivity> getListOfActivities(String username);


}
