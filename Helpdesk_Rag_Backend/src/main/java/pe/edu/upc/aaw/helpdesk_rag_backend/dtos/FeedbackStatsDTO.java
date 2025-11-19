
package pe.edu.upc.aaw.helpdesk_rag_backend.dtos;

public class FeedbackStatsDTO {
    private long total;

    private long promoters;
    private long passives;
    private long detractors;

    private long rating1;
    private long rating2;
    private long rating3;
    private long rating4;
    private long rating5;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getPromoters() {
        return promoters;
    }

    public void setPromoters(long promoters) {
        this.promoters = promoters;
    }

    public long getPassives() {
        return passives;
    }

    public void setPassives(long passives) {
        this.passives = passives;
    }

    public long getDetractors() {
        return detractors;
    }

    public void setDetractors(long detractors) {
        this.detractors = detractors;
    }

    public long getRating1() {
        return rating1;
    }

    public void setRating1(long rating1) {
        this.rating1 = rating1;
    }

    public long getRating2() {
        return rating2;
    }

    public void setRating2(long rating2) {
        this.rating2 = rating2;
    }

    public long getRating3() {
        return rating3;
    }

    public void setRating3(long rating3) {
        this.rating3 = rating3;
    }

    public long getRating4() {
        return rating4;
    }

    public void setRating4(long rating4) {
        this.rating4 = rating4;
    }

    public long getRating5() {
        return rating5;
    }

    public void setRating5(long rating5) {
        this.rating5 = rating5;
    }
}
