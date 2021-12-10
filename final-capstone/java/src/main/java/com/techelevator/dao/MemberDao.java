package com.techelevator.dao;


import com.techelevator.model.Family;
import com.techelevator.model.Member;

import java.util.List;

public interface MemberDao {

    // (member_id,user_id, family_id, first_name, last_name, member_type)


    Member addMember(Member memberToAdd);

    void updateMember(Member updatedMember);

    void deleteMember(Long memberId);

    List<Member> getListOfMembers(String username);

}
