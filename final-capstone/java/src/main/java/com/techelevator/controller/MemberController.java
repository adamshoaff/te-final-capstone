package com.techelevator.controller;

import com.techelevator.dao.JdbcMemberDao;
import com.techelevator.model.Member;
import com.techelevator.model.MemberNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController

public class MemberController {

    private JdbcMemberDao memberDao;

    public MemberController(JdbcMemberDao memberDao) {
        this.memberDao = memberDao;
    }

    // I think we need a Member model
    @RequestMapping (path = "/{id}", method = RequestMethod.GET)
    public String getListofMembers(@PathVariable long memberId) throws MemberNotFoundException {
        return this.memberDao.getListOfMembers(memberId);
    }

    @RequestMapping (method = RequestMethod.POST)
    public void addMember(@RequestBody Member memberToSave) {
        this.memberDao.addMember(memberToSave);
    }

    @RequestMapping (path = "/{id}", method = RequestMethod.PUT)
    public void updateMember(@RequestBody Member member) throws MemberNotFoundException {
       this.memberDao.updateMember(member);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void deleteMember(@PathVariable long memberId) throws MemberNotFoundException {
        this.memberDao.deleteMember(memberId);
    }

}
