package com.techelevator.controller;

import com.techelevator.dao.JdbcReadingActivityDao;
import com.techelevator.model.ReadingActivity;
import com.techelevator.model.ReadingActivityNotFoundException;
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
    @GetMapping (path ="/{activityId}")
    public ReadingActivity getActivity (@PathVariable Long activityId) throws ReadingActivityNotFoundException {
        return this.readingActivityDao.getActivity(activityId);
    }
    @PostMapping (path = "/add")
    public void addActivity(@RequestBody ReadingActivity activityToAdd, @RequestParam Long memberId) {
        this.readingActivityDao.addActivity(activityToAdd, memberId);
    }
    @RequestMapping (path = "/{activityId}", method= RequestMethod.PUT)
        public void updateActivity (@RequestBody ReadingActivity updatedActivity) throws ReadingActivityNotFoundException {
        this.readingActivityDao.updateActivity(updatedActivity);
    }
    @RequestMapping (path = "/{activityId}", method = RequestMethod.DELETE)
    public void deleteActivity(@PathVariable Long activityId) throws ReadingActivityNotFoundException {
        this.readingActivityDao.deleteActivity(activityId);
    }
    @GetMapping (path = "/memberId")
    public Long getReadingMinutes(@RequestParam Long memberId) {
        return this.readingActivityDao.getReadingMinutes(memberId);
    }
    @GetMapping(path = "")
    public List<ReadingActivity> getListOfActivities(@RequestParam Long familyId) throws ReadingActivityNotFoundException{
        return this.readingActivityDao.getListOfActivities(familyId);
    }
}
