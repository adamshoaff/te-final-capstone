package com.techelevator.dao;


import com.techelevator.model.Member;

public interface MemberDao {

    //void addMember(String firstName, String lastName, String memberType);

    void addMember(Member memberToAdd);

    void updateMember(Member updatedMember);

    void deleteMember(Long memberId);

    String getMember(Long memberId);

}
