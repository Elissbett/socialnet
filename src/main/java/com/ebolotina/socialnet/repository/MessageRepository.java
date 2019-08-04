package com.ebolotina.socialnet.repository;
import com.ebolotina.socialnet.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("select m from Message m where m.owner.id = :owner and m.party.id = :party")
    List<Message> findByUsers (@Param("owner") Long owner,@Param("party") Long party);
}
