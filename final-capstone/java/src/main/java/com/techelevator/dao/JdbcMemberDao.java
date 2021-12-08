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
        String sql = "INSERT INTO members (first_name, last_name, member_type)" +
                " VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, member.getFirstName(), member.getLastName(), member.getMemberType());
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
        String sql = "SELECT first_name, last_name, member_type" +
                " FROM members" +
                " JOIN family ON members.member_id = family.member_id" +
                " JOIN users ON family.user_id = users.user_id" +
                " WHERE username = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);
        while (results.next()) {
            Member newMember = mapRowToMember(results);
            members.add(newMember);
        }
        return members;

    }

    private Member mapRowToMember(SqlRowSet results) {
        Member member = new Member();
        member.setFirstName(results.getString("first_name"));
        member.setLastName(results.getString("last_name"));
        member.setMemberType(results.getString("member_type"));
        member.setMemberId(results.getLong("member_id"));
        return member;
    }


}
