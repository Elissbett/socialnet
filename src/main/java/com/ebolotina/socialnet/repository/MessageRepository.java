package com.ebolotina.socialnet.repository;

import com.ebolotina.socialnet.model.Message;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    //TODO:implement pagination. селектить не все
    @Query("select m from Message m where m.owner.id = :ownerId and m.party.id = :partyId")
    List<Message> getDialog(@Param("ownerId") Long ownerId,
                            @Param("partyId") Long partyId,
                            Pageable pageable);
}
