/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.shrestha.javabrains.messenger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author sukrins
 */
public class ProfileService {

    private static Map<String, Profile> profiles = Database.getProfiles();

    public ProfileService() {

        profiles.put("Sukrins", new Profile(1L, "Sukrins", "Sukrins", "Shrestha"));
        profiles.put("Admin", new Profile(2L, "Admin", "Admin", "User"));
        profiles.put("Guest", new Profile(3L, "Guest", "Guest", "User"));
    }

    /**
     *
     * @return
     */
    public List<Profile> getAllProfiles() {
        return new ArrayList<Profile>(profiles.values());
    }

    public Profile getProfile(String profileName) {
        return profiles.get(profileName);
    }

    public Profile addProfile(Profile profile) {
        profile.setId(profiles.size() + 1);
        profiles.put(profile.getProfileName(), profile);
        return profile;
    }

    public Profile updateProfile(Profile profile) {
        if (profile.getProfileName().isEmpty()) {
            return null;
        }
        profiles.put(profile.getProfileName(), profile);
        return profile;
    }

    public Profile removeProfile(String profileName) {
        return profiles.remove(profileName);
    }

}
