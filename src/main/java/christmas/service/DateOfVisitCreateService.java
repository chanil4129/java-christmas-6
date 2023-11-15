package christmas.service;

import christmas.model.vo.DateOfVisit;

public class DateOfVisitCreateService {
    private int date;

    public DateOfVisitCreateService() {
        this.date = date;
    }

    public void initDate(int date) {
        this.date = date;
    }

    public DateOfVisit createDateOfVisit() {
        DateOfVisit dateOfVisit = new DateOfVisit(date);
        return dateOfVisit;
    }
}
