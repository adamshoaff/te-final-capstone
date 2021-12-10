package com.techelevator.controller;

import com.techelevator.dao.JdbcFamilyDao;
import com.techelevator.model.Family;
import org.springframework.web.bind.annotation.*;
import java.io.FileNotFoundException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping ("/family")

public class FamilyController {

    private JdbcFamilyDao familyDao;

    public FamilyController(JdbcFamilyDao familyDao) {
        this.familyDao = familyDao;
    }

    @GetMapping (path = "")
    public List<Family> getListOfFamilies(@RequestParam String familyName) throws FileNotFoundException {
        return this.familyDao.getListOfFamilies(familyName);
        //Why is FileNotFoundationException dark? because we don't use this exception in method
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
