package com.example.boot04.boot04.persistence;

import com.example.boot04.boot04.domain.Profile;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository extends CrudRepository<Profile, Long> {
}
