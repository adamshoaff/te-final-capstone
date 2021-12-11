package com.techelevator.controller;

import com.techelevator.dao.JdbcReadingActivityDao;
import com.techelevator.model.ReadingActivity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/activities")
public class ReadingActivityController {
    private JdbcReadingActivityDao readingActivityDao;
    public ReadingActivityController (JdbcReadingActivityDao readingActivityDao) {
        this.readingActivityDao = readingActivityDao;
    }
//    @GetMapping (path ="")
//    public ReadingActivity getActivity (@RequestParam Long activityId) {
//        ReadingActivity readingActivity = readingActivityDao.getActivity(activityId);
//        List<ReadingActivity> activities = readingActivityDao.getListOfActivities(activityId);
//    }
    @PostMapping (path = "")
    public void addActivity(@RequestBody ReadingActivity activityToAdd) {
        this.readingActivityDao.addActivity(activityToAdd);
    }
    @RequestMapping (path = "/{id}", method= RequestMethod.PUT)
        public void updateActivity (@RequestBody ReadingActivity updatedActivity) {
        this.readingActivityDao.updateActivity(updatedActivity);
    }
    @RequestMapping (path ="/{id}" , method = RequestMethod.DELETE)
    public void deleteActivity(@PathVariable Long activityId) {
        this.readingActivityDao.deleteActivity(activityId);
    }
    @GetMapping (path ="/")
    public Long getReadingMinutes(@RequestParam Long memberId) {
        return this.readingActivityDao.getReadingMinutes(memberId);
    }
    @GetMapping(path = "")
    public List<ReadingActivity> getListOfActivities(@RequestParam Long memberId) {
        return this.readingActivityDao.getListOfActivities(memberId);
    }
}
