package com.techelevator.model;

import java.util.Date;

public class ReadingActivity {
    private Long activityId;
    private Date activityDate;
    private String readingFormat;
    private Long readingMinutes;
    private Long memberId;
    private Long bookId;
    private String readerNote;

    public ReadingActivity () {}

    public ReadingActivity (Long activityId, Date activityDate, String readingFormat, Long readingMinutes, Long memberId, Long bookId, String readerNote) {
       this.activityId = activityId;
       this.activityDate = activityDate;
       this.readingFormat= readingFormat;
       this.readingMinutes = readingMinutes;
       this.memberId = memberId;
       this.bookId = bookId;
       this.readerNote = readerNote;

    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Date getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(Date activityDate) {
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

    public String getReaderNote() {
        return readerNote;
    }

    public void setReaderNote(String readerNote) {
        this.readerNote = readerNote;
    }
}