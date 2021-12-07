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
        String sql = "INSERT INTO our_family (first_name, last_name, member_type)" +
                " VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, member.getFirstName(), member.getLastName(), member.getMemberType());
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
    public List<String> getListOfMembers(String username) {
        List<String> members = new ArrayList<>();
        String sql = "SELECT first_name, last_name, member_type " +
                "FROM our_family " +
                "JOIN users "
                "WHERE username = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, memberId);
        // this will need to join to the user table


        //Look for map method.
        return null;
    }




}
