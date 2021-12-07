package com.techelevator.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.lang.reflect.Member;

@Service
public class JdbcMemberDao implements MemberDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcMemberDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addMember(String firstName, String lastName, String memberType) {
        String sql = "INSERT INTO our_family (first_name, last_name, member_type)" +
                " VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, firstName, lastName, memberType);
    }

    @Override
    public void updateMember(Member updatedMember) {
        String sql = "UPDATE our_family " +
                "SET first_name = ?, last_name = ?, member_type = ? " +
                "WHERE member_id = ?";
        jdbcTemplate.update(sql, updatedMember.getFirstName(), updatedMember.getLastName(),
                updatedMember.getMemberType());
    }

    @Override
    public void deleteMember(Long memberId) {
        String sql = "DELETE FROM our_family WHERE member_id = ?;";
        jdbcTemplate.update(sql, memberId);
    }

    @Override
    public String getMember(Long memberId) {
        String sql = "SELECT first_name, last_name, email_address, username, password " +
                "FROM our_family " +
                "WHERE member_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, memberId);

        //Look for map method.
    }
}
