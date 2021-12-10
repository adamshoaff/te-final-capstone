package com.techelevator.dao;

import com.techelevator.model.Family;

import java.util.List;

public interface FamilyDao {

    // what do we need in here?
    // addFamily, updateFamily, deleteFamily

    Family getByUsername(String username);

    void addFamily(Family familyToAdd);

    void updateFamily(Family updatedFamily);

    void deleteFamily(Long familyId);

    List<Family> getListOfFamilies(String familyName);

}
