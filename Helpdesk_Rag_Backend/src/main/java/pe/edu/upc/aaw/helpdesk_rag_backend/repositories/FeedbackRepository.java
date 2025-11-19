package pe.edu.upc.aaw.helpdesk_rag_backend.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.edu.upc.aaw.helpdesk_rag_backend.entities.Feedback;

import java.util.Date;
import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {

    @Query("SELECT f FROM Feedback f WHERE f.created_at >= :start AND f.created_at < :end")
    List<Feedback> findByCreatedAtBetween(@Param("start") Date start,
                                          @Param("end") Date end);
}
