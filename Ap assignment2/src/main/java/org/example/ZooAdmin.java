package org.example;

import java.util.*;

public class ZooAdmin  {
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin123";
    private static HashMap<String,  Double> discounts = new HashMap<>();
    private static int totalVisitors = 0;
    private static double totalRevenue = 0.0;
    private static HashMap<String, Integer> attractionPopularity = new HashMap<>();
    private static Map<Integer, Attraction> attractions = new HashMap<>();
    private static Map<String, Animal> animals = new HashMap<>();
    private int nextAttractionID = 1;
    private List<String> visitorFeedback = new ArrayList<>();
    private Map<String, Integer> visitorStats = new HashMap<>();
    private static HashMap<Integer, Double> specialDeals = new HashMap<>();

    public ZooAdmin() {
        visitorStats.put("TotalVisitors", 0);
        visitorStats.put("TotalRevenue", 0);
        specialDeals.put(2,15.00);
        discounts.put("minor", 10.00);
        discounts.put("senior", 20.00);
        animals.put("Lion", new Mammal("Lion"));
        animals.put("Elephant", new Mammal("Elephant"));
        animals.put("Snake", new Reptile("Snake"));
        animals.put("Lizard", new Reptile("Lizard"));
        animals.put("Frog", new Amphibian("Frog"));
        animals.put("Tadpole", new Amphibian("Tadpole"));
    }

    public int getTotalVisitors() {
        return totalVisitors;
    }

    public void setTotalVisitors(int totalVisitors) {
        this.totalVisitors = totalVisitors;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public int getNextAttractionID() {
        return nextAttractionID;
    }

    public void setNextAttractionID(int nextAttractionID) {
        this.nextAttractionID = nextAttractionID;
    }

    public void addFeedback(String feedback) {
        visitorFeedback.add(feedback);
    }

    public void viewFeedback() {
        System.out.println("Visitor Feedback:");

        if (visitorFeedback.isEmpty()) {
            System.out.println("No feedback to display.");
            return;
        }

        for (int i = 0; i < visitorFeedback.size(); i++) {
            System.out.println((i + 1) + ". " + visitorFeedback.get(i));
        }
    }
    public static Map<Integer, Double> getSpecialDeals() {

        return new HashMap<>(specialDeals);
    }
    public static Map<String, Double> getDiscount() {

        return new HashMap<>(discounts);
    }

    public static void addVisitor(double ticketPrice, String attractionName) {
        totalVisitors++;
        totalRevenue += ticketPrice;

        int currentCount = attractionPopularity.getOrDefault(attractionName, 0);
        attractionPopularity.put(attractionName, currentCount + 1);
    }

    public void viewStats() {
        System.out.println("Visitor Statistics:");
        System.out.println("Total Number of Visitors: " + totalVisitors);
        System.out.println("Total Revenue: " + totalRevenue);

        System.out.println("Attraction Popularity:");
        for (Map.Entry<String, Integer> entry : attractionPopularity.entrySet()) {
            System.out.println("Attraction: " + entry.getKey() + ", Visitor Count: " + entry.getValue());
        }
    }

    public boolean addSpecialDeal(int minAttractions, double discountRate) {
        if (discountRate < 0 || discountRate > 100 || minAttractions <= 0) {
            return false;
        }
        specialDeals.put(minAttractions, discountRate);
        return true;
    }

    public void viewSpecialDeals() {
        if (specialDeals.isEmpty()) {
            System.out.println("No special deals to display.");
            return;
        }

        System.out.println("Special Deals:");
        for (Map.Entry<Integer, Double> entry : specialDeals.entrySet()) {
            System.out.println("Buy " + entry.getKey() + " tickets and get " + entry.getValue()+"% off");
        }
    }

    public boolean modifySpecialDeal(int minAttractions, double newDiscountRate) {
        if (!specialDeals.containsKey(minAttractions) || newDiscountRate < 0 || newDiscountRate > 100) {
            return false;
        }
        specialDeals.put(minAttractions, newDiscountRate);
        return true;
    }

    public boolean removeSpecialDeal(int minAttractions) {
        if (!specialDeals.containsKey(minAttractions)) {
            return false;
        }
        specialDeals.remove(minAttractions);
        return true;
    }

    boolean authenticate(String username, String password) {
        return USERNAME.equals(username) && PASSWORD.equals(password);
    }

    void addAttraction(String name, String description) {
        attractions.put(nextAttractionID, new Attraction(nextAttractionID, name, description));
        nextAttractionID++;
    }
    public boolean addOrModifyDiscount(String category,  double discount) {
        if (discount >= 0 && discount <= 100) {
            discounts.put(category, discount);
            return true;
        }
        return false;
    }

    public boolean removeDiscount(String category) {
        if (discounts.containsKey(category)) {
            discounts.remove(category);
            return true;
        }
        return false;
    }

    public void viewDiscounts() {
        for (String category : discounts.keySet()) {
                System.out.printf("Category: %s, Discount: %.2f%n", category,  discounts.get(category));
        }
    }
    public static void viewAttractions() {
        for (Map.Entry<Integer, Attraction> entry : attractions.entrySet()) {
            Attraction attraction = entry.getValue();
            System.out.println("ID: " + attraction.getId() + ", Name: " + attraction.getName() + ", Description: " + attraction.getDescription());
        }
    }

    void removeAttraction(int id) {
        attractions.remove(id);
    }

    void modifyAttraction(int id, String newName, String newDescription) {
        Attraction attraction = attractions.get(id);
        if (attraction != null) {
            attraction.setName(newName);
            attraction.setDescription(newDescription) ;
        }
    }
    boolean scheduleAttraction(int id, boolean status) {
        if (attractions.containsKey(id)) {
            attractions.get(id).setOpen(status);
            return true;
        }
        return false;
    }

    boolean setTicketPrice(int id, double price) {
        if (attractions.containsKey(id)) {
            attractions.get(id).setTicketPrice(price);
            return true;
        }
        return false;
    }

    public double getTicketPrice(int id) {
        if (attractions.containsKey(id)) {
            return attractions.get(id).getTicketPrice();
        }
        return -1;
    }

    public static List<Attraction> getAvailableAttractions() {
        List<Attraction> availableAttractions = new ArrayList<>();

        for (Map.Entry<Integer, Attraction> entry : attractions.entrySet()) {
            Attraction attraction = entry.getValue();
                availableAttractions.add(attraction);

        }

        return availableAttractions;
    }

    public static List<Animal> getAvailableAnimals() {
        List<Animal> availableAnimals = new ArrayList<>();

        for (Map.Entry<String, Animal> entry : animals.entrySet()) {
            Animal animal = entry.getValue();
            availableAnimals.add(animal);

        }

        return availableAnimals;
    }

    public boolean validateAttraction(int id) {
        return attractions.containsKey(id);
    }
    public boolean hasAttraction(int id) {
        return attractions.containsKey(id);
    }


    void addAnimal(String animalName, String animalType) {
        Animal newAnimal = null;

        switch (animalType) {
            case "mammal":
                animals.put(animalName, new Mammal(animalName));
                break;
            case "reptile":
                animals.put(animalName, new Reptile(animalName));
                break;
            case "amphibian":
                animals.put(animalName, new Amphibian(animalName));
                break;
            default:
                System.out.println("No such category exist");
                break;
        }

    }


    void updateAnimal(String oldAnimalName, String newAnimalName, String newAnimalType) {
        animals.remove(oldAnimalName);
        addAnimal(newAnimalName, newAnimalType);
    }

    void removeAnimal(String animalName) {
        animals.remove(animalName);
    }

    public static void viewAnimals() {
        for (Map.Entry<String, Animal> entry : animals.entrySet()) {
            System.out.println("Name: " + entry.getKey() + "\n"+ entry.getValue().getType());
        }
    }

}



