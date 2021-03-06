package com.techelevator.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.techelevator.model.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.techelevator.model.User;

@Service
public class JdbcUserDao implements UserDao {

    private JdbcTemplate jdbcTemplate;
    private MemberDao memberDao;

    public JdbcUserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int findIdByUsername(String username) {
        return jdbcTemplate.queryForObject("select user_id from users where username = ?", int.class, username);
    }

	@Override
	public User getUserById(Long userId) {
		String sql = "SELECT * FROM users WHERE user_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
		if(results.next()) {
			return mapRowToUser(results);
		} else {
			throw new RuntimeException("userId "+userId+" was not found.");
		}
	}

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String sql = "select * from users";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            User user = mapRowToUser(results);
            users.add(user);
        }

        return users;
    }

    @Override
    public User findByUsername(String username) throws UsernameNotFoundException {
        for (User user : this.findAll()) {
            if( user.getUsername().toLowerCase().equals(username.toLowerCase())) {
                return user;
            }
        }
        throw new UsernameNotFoundException("User " + username + " was not found.");
    }

//    @Override
//    public boolean create(String username, String password, String role,String firstName, String lastName) {
//        boolean userCreated = false;
//        int userId = createUser(username, password, role);
//        int familyId = createFamily(firstName +" "+lastName+" "+ "family" );
//        int memberId = createMember(userId, firstName, lastName, familyId);
//        return (userId > 0 && memberId > 0);
//    }

   @Override
    public int createMember(int userId, String firstName, int familyId, String memberType) {
        String sql = "INSERT INTO members (user_id, first_name, family_id, member_type) VALUES(?,?,?,?) RETURNING member_id;";
        return jdbcTemplate.queryForObject(sql, Integer.class, userId, firstName, familyId, memberType);
    }

    @Override
    public int createFamily(String username) {
        String sql = "INSERT INTO family (family_name) VALUES (?) RETURNING family_id;";
        return jdbcTemplate.queryForObject(sql, Integer.class, username);
    }

    @Override
    public int createUser(String username, String password, String role) {
        boolean userCreated;
        // create user
        String insertUser = "insert into users (username,password_hash,role) values(?,?,?);";
        //TODO: Insert user into members table
        String insertUserIntoMember = "INSERT INTO members();";

        String password_hash = new BCryptPasswordEncoder().encode(password);
        String ssRole = "ROLE_" + role.toUpperCase();

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        String id_column = "user_id";
        userCreated = jdbcTemplate.update(con -> {
                    PreparedStatement ps = con.prepareStatement(insertUser, new String[]{id_column});
                    ps.setString(1, username);
                    ps.setString(2, password_hash);
                    ps.setString(3, ssRole);
                    return ps;
                }
                , keyHolder) == 1;
        int newUserId = (int) keyHolder.getKeys().get(id_column);

//        String insertMember = "INSERT INTO members (user_id, family_id, first_name, last_name, member_type) VALUES (?, ?, ?, ?, ?);";
        int familyId = createFamily(username);
        createMember(newUserId, username, familyId, "parent");

        //return userCreated;
        return newUserId;
    }

    private User mapRowToUser(SqlRowSet rs) {
        User user = new User();
        user.setId(rs.getLong("user_id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password_hash"));
        user.setAuthorities(rs.getString("role"));
        user.setActivated(true);
        return user;
    }
}
