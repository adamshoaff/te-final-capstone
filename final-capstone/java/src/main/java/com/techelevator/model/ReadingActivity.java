package com.techelevator.model;

import java.util.Date;

public class ReadingActivity {
    private Long activityId;
    private String activityDate;
    private String readingFormat;
    private Long readingMinutes;
    private Long memberId;
    private Long bookId;
    private Long familyId;
    private String readerNotes;

    public ReadingActivity () {}

    public ReadingActivity (Long activityId, String activityDate, String readingFormat, Long readingMinutes, Long memberId, Long bookId, Long familyId, String readerNotes) {
       this.activityId = activityId;
       this.activityDate = activityDate;
       this.readingFormat= readingFormat;
       this.readingMinutes = readingMinutes;
       this.memberId = memberId;
       this.bookId = bookId;
       this.familyId = familyId;
       this.readerNotes = readerNotes;

    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public String getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(String activityDate) {
        this.activityDate = activityDate;
    }

    public String getReadingFormat() {
        return readingFormat;
    }

    public void setReadingFormat(String readingFormat) {
        this.readingFormat = readingFormat;
    }

    public Long getReadingMinutes() {
        return readingMinutes;
    }

    public void setReadingMinutes(Long readingMinutes) {
        this.readingMinutes = readingMinutes;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getFamilyId() {
        return familyId;
    }

    public void setFamilyId(Long familyId) {
        this.familyId = familyId;
    }

    public String getReaderNotes() {
        return readerNotes;
    }

    public void setReaderNotes(String readerNotes) {
        this.readerNotes = readerNotes;
    }
}
