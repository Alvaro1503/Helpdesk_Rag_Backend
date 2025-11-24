package pe.edu.upc.aaw.helpdesk_rag_backend.serviceimplements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.aaw.helpdesk_rag_backend.dtos.FeedbackStatsDTO;
import pe.edu.upc.aaw.helpdesk_rag_backend.entities.Feedback;
import pe.edu.upc.aaw.helpdesk_rag_backend.repositories.FeedbackRepository;
import pe.edu.upc.aaw.helpdesk_rag_backend.serviceinterfices.FeedbackService;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class FeedbackServiceImplement implements FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Override
    public void insert(Feedback feedback) {
        feedbackRepository.save(feedback);
    }

    @Override
    public List<Feedback> list() {
        return feedbackRepository.findAll();
    }

    @Override
    public Feedback listarId(int idFeedback) {
        return feedbackRepository.findById(idFeedback).orElse(null);
    }

    @Override
    public void delete(int idFeedback) {
        feedbackRepository.deleteById(idFeedback);
    }

    @Override
    public FeedbackStatsDTO getMonthlyStats(int year, int month) {
        // primer día del mes
        LocalDate startLocal = LocalDate.of(year, month, 1);
        // primer día del mes siguiente
        LocalDate endLocal = startLocal.plusMonths(1);

        Date start = Date.from(startLocal.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date end = Date.from(endLocal.atStartOfDay(ZoneId.systemDefault()).toInstant());

        List<Feedback> feedbacks = feedbackRepository.findByCreatedAtBetween(start, end);

        FeedbackStatsDTO dto = new FeedbackStatsDTO();
        long total = feedbacks.size();
        dto.setTotal(total);

        long promoters = feedbacks.stream()
                .filter(f -> f.getRating() == 5)
                .count();

        long passives = feedbacks.stream()
                .filter(f -> f.getRating() == 4)
                .count();

        long detractors = feedbacks.stream()
                .filter(f -> f.getRating() <= 3)
                .count();

        dto.setPromoters(promoters);
        dto.setPassives(passives);
        dto.setDetractors(detractors);

        dto.setRating1(feedbacks.stream().filter(f -> f.getRating() == 1).count());
        dto.setRating2(feedbacks.stream().filter(f -> f.getRating() == 2).count());
        dto.setRating3(feedbacks.stream().filter(f -> f.getRating() == 3).count());
        dto.setRating4(feedbacks.stream().filter(f -> f.getRating() == 4).count());
        dto.setRating5(feedbacks.stream().filter(f -> f.getRating() == 5).count());

        return dto;
    }
}
