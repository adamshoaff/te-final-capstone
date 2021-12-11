package com.techelevator.dao;

import com.techelevator.model.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Component
public class JdbcMemberDao implements MemberDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcMemberDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

// Friday 11AM this is what the table contains now -
// (member_id, user_id, family_id, first_name, last_name, member_type)

    @Override
    public Member addMember(Member newMember, String username) {
        //get the family id
        String familyIdSql = "SELECT members.family_id FROM members JOIN users ON members.user_id = users.user_id WHERE username = ?;";

        Long familyId = jdbcTemplate.queryForObject(familyIdSql, Long.class, username);


        String sql = "INSERT INTO members (family_id, first_name, last_name, member_type)" +
                " VALUES ( ?, ?, ?, ?) RETURNING member_id";
        Long newId = jdbcTemplate.queryForObject(sql, Long.class, familyId, newMember.getFirstName(), newMember.getLastName(), newMember.getMemberType());

        //jdbcTemplate.update(sql, member.getFamilyId(), member.getFirstName(), member.getLastName(), member.getMemberType());
        return getMember(newId);
        //the issue is that the family id doesn't get set up and there's no link between members and the user
        //for some reason, the members weren't getting added to the table until I changed things around? I don't know why...
    }

    @Override
    public void updateMember(Member updatedMember) {
        String sql = "UPDATE members" +
                " SET first_name = ?, last_name = ?, member_type = ?" +
                " WHERE member_id = ?";
        jdbcTemplate.update(sql, updatedMember.getFirstName(), updatedMember.getLastName(),
                updatedMember.getMemberType());
    }

    @Override
    public void deleteMember(Long memberId) {
        String sql = "DELETE FROM members WHERE member_id = ?;";
        jdbcTemplate.update(sql, memberId);
    }

    @Override
    public List<Member> getListOfMembers(String username) {
        List<Member> members = new ArrayList<>();
        String sql = "SELECT member_id, user_id, family_id, first_name, last_name, member_type" +
                "FROM members " +
                "WHERE family_id = " +
                "(SELECT family.family_id FROM family " +
                "JOIN members ON family.family_id = members.family_id " +
                "JOIN users ON members.user_id = users.user_id WHERE username = ?);";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);
        while (results.next()) {
            Member newMember = mapRowToMember(results);
            members.add(newMember);
        }
        return members;

    }
    @Override
    public Member getMember(Long memberId){
        Member member = null;
        String sql = "SELECT member_id, user_id, family_id, first_name, last_name, member_type" +
                    " FROM members" +
                    " WHERE members.member_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, memberId);
        if(results.next()){
            member = mapRowToMember(results);
        }
        return member;
    }


   // (member_id, user_id, family_id, first_name, last_name, member_type)

    private Member mapRowToMember(SqlRowSet results) {
        Member member = new Member();
        member.setMemberId(results.getLong("member_id"));
        member.setUserId(results.getLong("user_id"));
        member.setFamilyId(results.getLong("family_id"));
        member.setFirstName(results.getString("first_name"));
        member.setLastName(results.getString("last_name"));
        member.setMemberType(results.getString("member_type"));
        return member;
    }


}
