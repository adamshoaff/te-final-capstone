package com.techelevator.dao;


import com.techelevator.model.Family;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Component
public class JdbcFamilyDao implements FamilyDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcFamilyDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addFamily(Family family) {
        String sql = "INSERT INTO family (family_id, family_name)" +
                " VALUES (?, ?)";
        jdbcTemplate.update(sql, family.getFamilyId(), family.getFamilyName());
    }

    @Override
    public Family getByUsername(String username){
        Family family = null;
        String sql = "SELECT family.family_id, family_name FROM family" +
                " JOIN members ON family.family_id = members.family_id"+
                " JOIN users ON users.user_id = members.user_id WHERE username = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);
        if(results.next()){
            family = mapRowToFamily(results);
        }
        return family;
    }

    @Override
    public void updateFamily(Family updatedFamily) {
        String sql = "UPDATE family" +
                " SET family_name = ?" +
                " WHERE family_id = ?";
        jdbcTemplate.update(sql, updatedFamily.getFamilyName());
    }

    @Override
    public void deleteFamily(Long familyId) {
        String sql = "DELETE FROM family WHERE family_id = ?;";
        jdbcTemplate.update(sql, familyId);
    }

    @Override
    public List<Family> getListOfFamilies(String familyName) {
        List<Family> families = new ArrayList<>();
        String sql = "SELECT family_id, family_name" +
                " FROM family" +
                " JOIN members ON family.family_id = members.family_id" +
                " JOIN users ON members.user_id = users.user_id" +
                " WHERE username = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, familyName);
        while (results.next()) {
            Family newFamily = mapRowToFamily(results);
            families.add(newFamily);
        }
        return families;
    }

    private Family mapRowToFamily(SqlRowSet results) {
        Family family = new Family();
        family.setFamilyId(results.getLong("family_id"));
        family.setFamilyName(results.getString("family_name"));
        return family;
    }
}



