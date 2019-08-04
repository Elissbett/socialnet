package com.ebolotina.socialnet.repository;

import com.ebolotina.socialnet.model.Community;
import com.ebolotina.socialnet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommunityRepository extends JpaRepository<Community, Long> {

    @Query("select c from Community c join c.members m where m.id = :userId")
    List<Community> findByUser (@Param("userId") Long userId);
}
