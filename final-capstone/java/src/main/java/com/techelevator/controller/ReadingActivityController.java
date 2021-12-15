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

    //we're no longer using this because Postman confused this endpoint with another, so we will need to choose a different endpoint for this path.
//    @RequestMapping (path = "/{activityId}", method= RequestMethod.PUT)
//        public void updateActivity (@RequestBody ReadingActivity updatedActivity) throws ReadingActivityNotFoundException {
//        this.readingActivityDao.updateActivity(updatedActivity);
//    }
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


    //added endpoint so that we can toggle whether or not a book is complete
    @PutMapping(path="/complete")
    public void markBookCompleted(@RequestParam Long activityId) {
        this.readingActivityDao.markBookCompleted(activityId);
    }
    @PutMapping(path="/incomplete")
    public void markBookIncomplete(@RequestParam Long activityId) {
        this.readingActivityDao.markBookIncomplete(activityId);
    }
}
