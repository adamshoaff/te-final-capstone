package com.techelevator.dao;

import com.techelevator.model.ReadingActivity;

import java.util.List;

public interface ReadingActivityDao {

    Long getReadingMinutes(Long memberId);

    ReadingActivity getActivity(Long activityId);

    void addActivity(ReadingActivity activityToAdd);

    void updateActivity(ReadingActivity updatedActivity);

    void deleteActivity(Long activityId);

    List<ReadingActivity> getListOfActivities(Long memberId);


}
