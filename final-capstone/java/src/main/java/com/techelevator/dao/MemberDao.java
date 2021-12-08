package com.techelevator.dao;


import com.techelevator.model.Member;

import java.util.List;

public interface MemberDao {

    //void addMember(String firstName, String lastName, String memberType);

    void addMember(Member memberToAdd);

    void updateMember(Member updatedMember);

    void deleteMember(Long memberId);

    List<Member> getListOfMembers(String username);

}
