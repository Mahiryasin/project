package com.mahir.Reporistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mahir.Entitites.UserEntitiy;

@Repository
public interface UserReporistory extends  JpaRepository<UserEntitiy, Long> {

}
