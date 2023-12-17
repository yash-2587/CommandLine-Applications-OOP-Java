package org.example;

import java.util.*;

class Visitor  {
    Scanner scanner = new Scanner(System.in);
    Membership mem = new Membership("");
    private String name;
    private int age;
    private String phoneNumber;
    private double balance;
    private String email;
    private String password;
    private List<Ticket> tickets;
    public Visitor(String name, int age, String phoneNumber, double balance, String email, String password) {
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
        this.email = email;
        this.password = password;
        this.tickets = new ArrayList<>();
    }
    public String getName() {
        return name;
    }


    public void setMem(String type) {
        this.mem = new Membership(type);
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public int getAge() {
        return age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public double getBalance() {
        return balance;
    }

    public String getEmail() {
        return email;
    }


    public boolean useTicketForAttraction() {
        if (!tickets.isEmpty()) {
            tickets.remove(0);
            return true;
        } else {
            return false;
        }
    }

    public void exploreZoo() {
        int choice;
        do {
            System.out.println("Explore the Zoo:");
            System.out.println("1. View Attractions");
            System.out.println("2. View Animals");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Attractions in the Zoo:");
                    ZooAdmin.viewAttractions();
                    break;
                case 2:
                    System.out.println("Animals in the Zoo:");
                    ZooAdmin.viewAnimals();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 3);
    }

    public void buyMembership() {
        System.out.println("Buy Membership:");
        System.out.println("1. Basic Membership (₹20)");
        System.out.println("2. Premium Membership (₹50)");
        int memType = scanner.nextInt();
        scanner.nextLine();

        int cost = 0;
        String membershipType = "";

        if (memType == 1) {
            cost = 20;
            membershipType = "Basic";
        } else if (memType == 2) {
            cost = 50;
            membershipType = "Premium";
        } else {
            System.out.println("Invalid choice. Transaction aborted.");
            return;
        }

        if (getBalance() < cost) {
            System.out.println("Insufficient balance. Transaction aborted.");
            return;
        }

        setBalance(getBalance() - cost);
        setMem(membershipType);

        System.out.println("Congratulations, you are now a " + membershipType + " member of the zoo!");
    }


    public void buyTickets() {
        if ("Premium".equals(mem.getType())) {
            System.out.println("Premium members does not require tickets.");
            return;
        }
        if (!"Basic".equals(mem.getType())) {
            System.out.println("Only Basic members can buy tickets.");
            return;
        }

        List<Attraction> availableAttractions = ZooAdmin.getAvailableAttractions();
        Map<Integer, Attraction> attractionMap = new HashMap<>();

        System.out.println("Available Attractions:");
        for (int i = 0; i < availableAttractions.size(); i++) {
            System.out.println((i + 1) + ". " + availableAttractions.get(i).getName() + " - Rs" + availableAttractions.get(i).getTicketPrice());
            attractionMap.put(i + 1, availableAttractions.get(i));
        }

        System.out.print("Enter the number of attraction you want to visit ");
        String[] selectedAttractions = scanner.nextLine().split(",");

        for (String s : selectedAttractions) {
            int choice = Integer.parseInt(s.trim());
            Attraction selectedAttraction = attractionMap.get(choice);

            if (selectedAttraction != null) {
                if (getBalance() < selectedAttraction.getTicketPrice()) {
                    System.out.println("Insufficient balance for " + selectedAttraction.getName());
                    continue;
                }

                setBalance(getBalance() - selectedAttraction.getTicketPrice());

                Ticket ticket = new Ticket(selectedAttraction.getName(), selectedAttraction.getTicketPrice());
                tickets.add(ticket);
                ZooAdmin.addVisitor(selectedAttraction.getTicketPrice(),selectedAttraction.getName());
                System.out.println("Ticket purchased for " + selectedAttraction.getName());
            } else {
                System.out.println("Invalid choice: " + choice);
            }
        }

        applyDiscount();
        applySpecialDeals();

        System.out.println("Tickets purchased successfully!");
    }
    public void applySpecialDeals() {
        Map<Integer, Double> specialDeals = ZooAdmin.getSpecialDeals();
        int numTicketsPurchased = tickets.size();

        List<Integer> sortedKeys = new ArrayList<>(specialDeals.keySet());
        Collections.sort(sortedKeys, Collections.reverseOrder());
        double discountRate = 0.0;
        for (Integer minAttractions : sortedKeys) {
            if (numTicketsPurchased >= minAttractions) {
                discountRate = specialDeals.get(minAttractions);
                break;
            }
        }

        if (discountRate > 0) {
            double totalCost = 0;
            for (Ticket ticket : tickets) {
                totalCost += ticket.getPrice();
            }

            double discountAmount = Math.round(totalCost * (discountRate / 100.0));
            setBalance(getBalance() + discountAmount);
            System.out.println("A special deal is applied. You get a " + discountRate + "% discount amounting to Rs" + discountAmount);
        }
    }
    public void applyDiscount() {
        Map<String, Double> specialDeals = ZooAdmin.getDiscount();

        int visitorAge = getAge();

        double discountRate = 0.0;
        if (visitorAge < 18) {
            discountRate = specialDeals.getOrDefault("minor", 0.0);
        } else if (visitorAge >= 65) {
            discountRate = specialDeals.getOrDefault("senior", 0.0);
        }

        if (discountRate > 0) {
            double totalCost = 0;
            for (Ticket ticket : tickets) {
                totalCost += ticket.getPrice();
            }

            double discountAmount = Math.round(totalCost * (discountRate / 100.0));
            setBalance(getBalance() + discountAmount);
            System.out.println("A discount is applied. You get a " + discountRate + "% discount amounting to Rs" + discountAmount);
        }
    }


    public void visitAnimals() {
            System.out.println("Visit Animals:");
            System.out.println("Choose an animal to view:");

            List<Animal> animals = ZooAdmin.getAvailableAnimals();
            for (int i = 0; i < animals.size(); i++) {
                System.out.println((i + 1) + ". " + animals.get(i).getName());
            }

            System.out.print("Enter the number of the animal you want to view: ");
            int choice = scanner.nextInt();

            if (choice >= 1 && choice <= animals.size()) {
                Animal selectedAnimal = animals.get(choice - 1);
                System.out.println("You are now viewing " + selectedAnimal.getName());

                System.out.println("Would you like to \n" +
                        " 1) Feed the animal \n" +
                        " 2) Read about the animal? ");
                int action = scanner.nextInt();
                if (action == 1) {
                    System.out.println("You chose to feed the animal.");
                    System.out.println(selectedAnimal.makeNoise());
                } else if (action == 2) {
                    System.out.println("You chose to read about the animal.");
                    System.out.println(selectedAnimal.getType());
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }

            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }


    public void visitAttractions() {
        System.out.println("Visit Attractions:");
        System.out.println("Choose an attraction to visit:");
        Scanner sc = new Scanner(System.in);
        List<Attraction> attractions = ZooAdmin.getAvailableAttractions();
        for (int i = 0; i < attractions.size(); i++) {
            System.out.println((i + 1) + ". " + attractions.get(i).getName());
        }
        System.out.println((attractions.size() + 1) + ". Exit");

        System.out.print("Enter the number of the attraction you want to visit: ");
        int choice = sc.nextInt();

        if (choice >= 1 && choice <= attractions.size()) {
            Attraction selectedAttraction = attractions.get(choice - 1);
            if ("Premium".equals(mem.getType())) {
            ZooAdmin.addVisitor(0.00,selectedAttraction.getName());}
            else if (useTicketForAttraction()==false) {
                System.out.println("Insufficient tickets");
                return;
            }
            System.out.println("Thank you for visiting " + selectedAttraction.getName() + ". Hope you enjoyed the attraction.");
        } else if (choice == attractions.size() + 1) {
            System.out.println("Exiting attractions menu.");
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
    }
}

