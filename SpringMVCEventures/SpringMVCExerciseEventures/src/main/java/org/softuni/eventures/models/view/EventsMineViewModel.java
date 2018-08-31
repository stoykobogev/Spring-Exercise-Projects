package org.softuni.eventures.models.view;

public class EventsMineViewModel {

    private String name;
    private String start;
    private String end;
    private int ticketsCount;

    public EventsMineViewModel() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStart() {
        return this.start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return this.end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public int getTicketsCount() {
        return this.ticketsCount;
    }

    public void setTicketsCount(int ticketsCount) {
        this.ticketsCount = ticketsCount;
    }
}
