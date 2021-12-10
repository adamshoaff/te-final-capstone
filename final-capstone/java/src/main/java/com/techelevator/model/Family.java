package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

public class Family {

    private Long familyId;
    private String familyName;
   private List<Member> members = new ArrayList<>();

    public Family () {}


    public Family(Long familyId, String familyName, List<Member> members) {
        this.familyId = familyId;
        this.familyName = familyName;
        this.members = members;
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

    public List<Member> getMembers() {
        return members;
    }
    public void setMembers(List<Member> members) {
        this.members = members;
    }
}
