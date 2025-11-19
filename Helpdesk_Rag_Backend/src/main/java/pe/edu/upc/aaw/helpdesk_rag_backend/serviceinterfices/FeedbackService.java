package pe.edu.upc.aaw.helpdesk_rag_backend.serviceinterfices;

import pe.edu.upc.aaw.helpdesk_rag_backend.entities.Feedback;

import java.util.List;

import pe.edu.upc.aaw.helpdesk_rag_backend.dtos.FeedbackStatsDTO;

public interface FeedbackService {
    void insert(Feedback feedback);
    public void delete(int idFeedback);
    List<Feedback> list();
    Feedback listarId(int idFeedback);
    FeedbackStatsDTO getMonthlyStats(int year, int month);
}
