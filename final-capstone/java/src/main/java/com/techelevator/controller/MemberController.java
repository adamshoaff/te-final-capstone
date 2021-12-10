package com.techelevator.controller;

import com.techelevator.dao.JdbcMemberDao;
import com.techelevator.model.Member;
import com.techelevator.model.MemberNotFoundException;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping ("/members")
// (Annie) I have changed "/member" to "/members"

public class MemberController {

    private JdbcMemberDao memberDao;

    public MemberController(JdbcMemberDao memberDao) {
        this.memberDao = memberDao;
    }

    @GetMapping (path = "")
    public List<Member> getListOfMembers(@RequestParam String username) throws MemberNotFoundException {
        return this.memberDao.getListOfMembers(username);
    }

    @PostMapping (path = "") // previously this said "/create", I believe Mike said to omit that
    public void addMember(@RequestBody Member memberToSave, @RequestParam String username) {
        this.memberDao.addMember(memberToSave, username);
    }

    @RequestMapping (path = "/{id}", method = RequestMethod.PUT)
    public void updateMember(@RequestBody Member member) throws MemberNotFoundException {
       this.memberDao.updateMember(member);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void deleteMember(@PathVariable long id) throws MemberNotFoundException {
        this.memberDao.deleteMember(id);
    }

}
