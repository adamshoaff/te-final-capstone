package com.techelevator.controller;

import com.techelevator.model.MemberNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController

public class MemberController {

    private MemberDao memberDao;

    public MemberController(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    // I think we need a Member model
    @RequestMapping (path = "/{id}", method = RequestMethod.GET)
    public Member getMember(@PathVariable long memberId) throws MemberNotFoundException {
        return this.memberDao.getMember(memberId);
    }

    @RequestMapping (method = RequestMethod.POST)
    public boolean addMember(@RequestBody Member memberToSave) {
        return this.memberDao.addMember(memberToSave);
    }

    @RequestMapping (path = "/{id}", method = RequestMethod.PUT)
    public boolean updateMember(@RequestBody long memberId, @RequestBody Member member) throws MemberNotFoundException {
        return this.memberDao.updateMember(memberId, member);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public boolean deleteMember(@PathVariable long memberId) throws MemberNotFoundException {
        return this.memberDao.deleteMember(memberId);
    }

}
