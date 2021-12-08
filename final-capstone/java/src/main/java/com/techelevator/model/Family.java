package com.techelevator.model;

public class Family {

    private Long familyId;
    private String familyName;
    private Long memberId;

    public Family () {}

    public Family(Long familyId, String familyName, Long memberId) {
        this.familyId = familyId;
        this.familyName = familyName;
        this.memberId = memberId;
    }

    public Long getFamilyId() {
        return familyId;
    }

    public void setFamilyId(Long familyId) {
        this.familyId = familyId;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }
}
