package com.techelevator.model;

public class Member {

    public Long memberId;
    public String firstName;
    public String lastName;
    public String memberType;

    public Member(Long memberId, String firstName, String lastName, String memberType) {
        this.memberId = memberId;
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
