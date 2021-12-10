package com.techelevator.controller;

import com.techelevator.dao.JdbcFamilyDao;
import com.techelevator.dao.JdbcMemberDao;
import com.techelevator.model.Family;
import com.techelevator.model.Member;
import org.springframework.web.bind.annotation.*;
import java.io.FileNotFoundException;
import java.security.Principal;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping ("/family")

public class FamilyController {

    private JdbcFamilyDao familyDao;
private JdbcMemberDao memberDao;
    public FamilyController(JdbcFamilyDao familyDao, JdbcMemberDao memberDao) {
        this.familyDao = familyDao;
        this.memberDao = memberDao;
    }

//    @GetMapping (path = "")
//    public List<Family> getListOfFamilies(@RequestParam String familyName) throws FileNotFoundException {
//        return this.familyDao.getListOfFamilies(familyName);
//        //Why is FileNotFoundationException dark? because we don't use this exception in method
//    }

  //TODO: add authentication
    @GetMapping(path="")
    public Family getFamily(@RequestParam String username){
        Family family = familyDao.getByUsername(username);
        List<Member> members = memberDao.getListOfMembers(username);
        family.setMembers(members);
        return family;
    }

    @PostMapping (path = "/create")
    public void addFamily(@RequestBody Family familyToSave) {
        this.familyDao.addFamily(familyToSave);
    }

    @RequestMapping (path = "/{id}", method = RequestMethod.PUT)
    public void updateFamily(@RequestBody Family family) throws FileNotFoundException {
        this.familyDao.updateFamily(family);
    }

    @RequestMapping (path = "/{id)", method = RequestMethod.DELETE)
    public void deleteMember(@PathVariable long id) throws FileNotFoundException {
        this.familyDao.deleteFamily(id);
    }
}
