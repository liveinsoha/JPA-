package org.example.domain.embeddable;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
public class Period {

    LocalDate startDate;
    LocalDate endDate;

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
