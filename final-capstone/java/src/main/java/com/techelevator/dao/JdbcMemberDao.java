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

    @Override
    public void addMember(Member member) {
        String sql = "INSERT INTO members (member_id, user_id, family_id, first_name, last_name, member_type)" +
                " VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, member.getMemberId(), member.getUserId(), member.getFamilyId(), member.getFirstName(), member.getLastName(), member.getMemberType());
    }

    @Override
    public void updateMember(Member updatedMember) {
        String sql = "UPDATE members" +
                " SET user_id = ?, family_id = ?, first_name = ?, last_name = ?, member_type = ?" +
                " WHERE member_id = ?";
        jdbcTemplate.update(sql, updatedMember.getUserId(), updatedMember.getFamilyId(), updatedMember.getFirstName(), updatedMember.getLastName(),
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
                " FROM members" +
                " JOIN users ON member.user_id = users.user_id" +
                " WHERE username = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);
        while (results.next()) {
            Member newMember = mapRowToMember(results);
            members.add(newMember);
        }
        return members;

    }

    // (member_id,user_id, family_id, first_name, last_name, member_type)

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
