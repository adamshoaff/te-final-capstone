package com.techelevator.model;

public class Member {

    // (member_id,user_id, family_id, first_name, last_name, member_type)
    public Long memberId;
    public Long userId;
    public Long familyId;
    public String firstName;
    public String lastName;
    public String memberType;

    public Member () {

    }

    public Member(Long memberId, Long userId, Long familyId, String firstName, String lastName, String memberType) {
        this.memberId = memberId;
        this.userId = userId;
        this.familyId = familyId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.memberType = memberType;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFamilyId() {
        return familyId;
    }

    public void setFamilyId(Long familyId) {
        this.familyId = familyId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }
}
