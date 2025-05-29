package org.example;

public class Attraction {
    private String name;
    private int id;
    private String description;
    private boolean isOpen;
    private double ticketPrice;
    private int visitorCount;

    public int getVisitorCount() {
        return this.visitorCount;
    }

    public Attraction(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.isOpen = true;
        this.ticketPrice = 0.0;
        this.visitorCount = 0;
    }

    public void setOpen(boolean status) {
        this.isOpen = status;
    }

    public void setTicketPrice(double price) {
        this.ticketPrice = price;
    }
    public double getTicketPrice() {
        return this.ticketPrice;
    }


    public String getName() {
        return name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
