package org.example;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ZooAdmin admin = new ZooAdmin();
         HashMap<String, Visitor> registeredVisitors = new HashMap<>();

        while (true) {
            System.out.println("1. Enter as Admin");
            System.out.println("2. Enter as a Visitor");
            System.out.println("3. View Special Deals");
            System.out.println("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                System.out.println("Enter Admin Username: ");
                String username = sc.nextLine();
                System.out.println("Enter Admin Password: ");
                String password = sc.nextLine();

                if (admin.authenticate(username, password)) {
                    System.out.println("Logged in as Admin.");
                    while (true) {
                        System.out.println("Admin Menu:");
                        System.out.println("1. Manage Attractions");
                        System.out.println("2. Manage Animals");
                        System.out.println("3. Schedule Events");
                        System.out.println("4. Set Discounts");
                        System.out.println("5. Set Special Deal");
                        System.out.println("6. View Visitor Stats");
                        System.out.println("7. View Feedback");
                        System.out.println("8. Exit");
                        System.out.println("Enter your choice: ");
                        int adminChoice = sc.nextInt();
                        sc.nextLine();

                        if (adminChoice == 1) {
                            while (true) {
                                System.out.println("Manage Attractions:");
                                System.out.println("1. Add Attraction");
                                System.out.println("2. View Attractions");
                                System.out.println("3. Modify Attraction");
                                System.out.println("4. Remove Attraction");
                                System.out.println("5. Exit");
                                System.out.println("Enter your choice: ");
                                int attractionChoice = sc.nextInt();
                                sc.nextLine();

                                if (attractionChoice == 1) {
                                        System.out.println("Enter Attraction Name:");
                                        String name = sc.nextLine();
                                        System.out.println("Enter Attraction Description:");
                                        String description = sc.nextLine();
                                        admin.addAttraction(name, description);
                                        System.out.println("Attraction added successfully.");
                                } else if (attractionChoice == 2) {
                                    admin.viewAttractions();
                                } else if (attractionChoice == 3) {
                                    System.out.println("Enter Attraction ID to Modify:");
                                    int id = sc.nextInt();
                                    sc.nextLine();
                                    if (admin.hasAttraction(id)) {
                                        System.out.println("Enter New Attraction Name:");
                                        String newName = sc.nextLine();
                                        System.out.println("Enter New Attraction Description:");
                                        String newDescription = sc.nextLine();
                                        admin.modifyAttraction(id, newName, newDescription);
                                        System.out.println("Attraction modified successfully.");
                                    } else {
                                        System.out.println("Attraction not found.");
                                    }
                                } else if (attractionChoice == 4) {
                                    System.out.println("Enter Attraction ID to Remove:");
                                    int id = sc.nextInt();
                                    if (admin.hasAttraction(id)) {
                                        admin.removeAttraction(id);
                                        System.out.println("Attraction removed successfully.");
                                    } else {
                                        System.out.println("Attraction not found.");
                                    }
                                } else if (attractionChoice == 5) {
                                    break;
                                } else {
                                    System.out.println("Invalid choice.");
                                }
                            }
                        } else if (adminChoice == 2) {
                            while (true) {
                                System.out.println("Manage Animals:");
                                System.out.println("1. Add Animal");
                                System.out.println("2. View Animals");
                                System.out.println("3. Modify Animal");
                                System.out.println("4. Remove Animal");
                                System.out.println("5. Exit");
                                int manageAnimalsChoice = sc.nextInt();
                                sc.nextLine();

                                if (manageAnimalsChoice == 1) {
                                    System.out.println("Enter Animal Name:");
                                    String animalName = sc.nextLine();
                                    System.out.println("Enter Animal Type:");
                                    String animalType = sc.nextLine();
                                    admin.addAnimal(animalName, animalType);
                                    System.out.println("Animal added successfully.");
                                } else if (manageAnimalsChoice == 2) {
                                    admin.viewAnimals();
                                } else if (manageAnimalsChoice == 3) {
                                    System.out.println("Enter Existing Animal Name:");
                                    String oldAnimalName = sc.nextLine();
                                    System.out.println("Enter New Animal Name:");
                                    String newAnimalName = sc.nextLine();
                                    System.out.println("Enter New Animal Type:");
                                    String newAnimalType = sc.nextLine();
                                    admin.updateAnimal(oldAnimalName, newAnimalName, newAnimalType);
                                    System.out.println("Animal modified successfully.");
                                } else if (manageAnimalsChoice == 4) {
                                    System.out.println("Enter Animal Name:");
                                    String animalName = sc.nextLine();
                                    admin.removeAnimal(animalName);
                                    System.out.println("Animal removed successfully.");
                                } else if (manageAnimalsChoice == 5) {
                                    break;
                                } else {
                                    System.out.println("Invalid choice, please try again.");
                                }
                            }
                        }
                        else if (adminChoice == 3) {
                            while (true) {
                                System.out.println("Schedule Events:");
                                System.out.println("1. Set Attraction Status (Open/Close)");
                                System.out.println("2. Set Ticket Price");
                                System.out.println("3. Exit");
                                int manageEventsChoice = sc.nextInt();
                                sc.nextLine();

                                if (manageEventsChoice == 1) {
                                    System.out.println("Enter Attraction ID:");
                                    int id = sc.nextInt();
                                    if (admin.validateAttraction(id)) {
                                        System.out.println("Set Status (Enter true for Open and false for Close):");
                                        boolean status = sc.nextBoolean();
                                        if (admin.scheduleAttraction(id, status)) {
                                            System.out.println("Status set successfully.");
                                        } else {
                                            System.out.println("Failed to set status. Please try again.");
                                        }
                                    } else {
                                        System.out.println("Invalid Attraction ID.");
                                    }
                                } else if (manageEventsChoice == 2) {
                                    System.out.println("Enter Attraction ID:");
                                    int id = sc.nextInt();
                                    if (admin.validateAttraction(id)) {
                                        System.out.println("Enter Ticket Price:");
                                        double price = sc.nextDouble();
                                        if (admin.setTicketPrice(id, price)) {
                                            System.out.println("Ticket price set successfully.");
                                        } else {
                                            System.out.println("Failed to set ticket price. Please try again.");
                                        }
                                    } else {
                                        System.out.println("Invalid Attraction ID.");
                                    }

                                } else if (manageEventsChoice == 3) {
                                    break;
                                } else {
                                    System.out.println("Invalid choice, please try again.");
                                }
                            }
                        }

                        else if (adminChoice == 4) {
                            while (true) {
                                System.out.println("Set Discounts:");
                                System.out.println("1. Add or Modify Discount");
                                System.out.println("2. View Discounts");
                                System.out.println("3. Remove Discount");
                                System.out.println("4. Go Back to Main Menu");

                                int discountChoice = sc.nextInt();
                                sc.nextLine();

                                if (discountChoice == 1) {
                                    System.out.println("Enter visitor category (e.g., 'minor', 'senior'):");
                                    String category = sc.nextLine();
                                    System.out.println("Enter discount rate (0.00 to 100.00):");
                                    double discount = sc.nextDouble();
                                    sc.nextLine();

                                    if (admin.addOrModifyDiscount(category,  discount)) {
                                        System.out.println("Discount added or modified successfully.");
                                    } else {
                                        System.out.println("Failed to add or modify discount. Please try again.");
                                    }

                                } else if (discountChoice == 2) {
                                    admin.viewDiscounts();

                                } else if (discountChoice == 3) {
                                    System.out.println("Enter visitor category to remove discount:");
                                    String category = sc.nextLine();

                                    if (admin.removeDiscount(category)) {
                                        System.out.println("Discount removed successfully.");
                                    } else {
                                        System.out.println("Failed to remove discount. Please try again.");
                                    }

                                } else if (discountChoice == 4) {
                                    break;  // Exit to main menu
                                } else {
                                    System.out.println("Invalid choice. Please try again.");
                                }
                            }
                        }

                        else if (adminChoice == 5) {
                            while (true) {
                                System.out.println("Set Special Deals:");
                                System.out.println("1. Add Special Deal");
                                System.out.println("2. View Special Deals");
                                System.out.println("3. Modify Special Deal");
                                System.out.println("4. Remove Special Deal");
                                System.out.println("5. Go Back to Main Menu");

                                int dealChoice = sc.nextInt();
                                sc.nextLine();

                                if (dealChoice == 1) {
                                    System.out.println("Enter the minimum number of attractions for the deal:");
                                    int minAttractions = sc.nextInt();
                                    System.out.println("Enter the special discount rate (0 to 100):");
                                    double discountRate = sc.nextDouble();
                                    sc.nextLine();

                                    if (admin.addSpecialDeal(minAttractions, discountRate)) {
                                        System.out.println("Special deal added successfully.");
                                    } else {
                                        System.out.println("Failed to add special deal. Please try again.");
                                    }

                                } else if (dealChoice == 2) {
                                    admin.viewSpecialDeals();

                                } else if (dealChoice == 3) {
                                    System.out.println("Enter the minimum number of attractions for the deal to modify:");
                                    int minAttractions = sc.nextInt();
                                    System.out.println("Enter the new special discount rate (0 to 100):");
                                    double newDiscountRate = sc.nextDouble();
                                    sc.nextLine();

                                    if (admin.modifySpecialDeal(minAttractions, newDiscountRate)) {
                                        System.out.println("Special deal modified successfully.");
                                    } else {
                                        System.out.println("Failed to modify special deal. Please try again.");
                                    }

                                } else if (dealChoice == 4) {
                                    System.out.println("Enter the minimum number of attractions for the deal to remove:");
                                    int minAttractions = sc.nextInt();
                                    sc.nextLine();

                                    if (admin.removeSpecialDeal(minAttractions)) {
                                        System.out.println("Special deal removed successfully.");
                                    } else {
                                        System.out.println("Failed to remove special deal. Please try again.");
                                    }

                                } else if (dealChoice == 5) {
                                    break;

                                } else {
                                    System.out.println("Invalid choice. Please try again.");
                                }
                            }
                        }

                        else if (adminChoice == 6) {
                            admin.viewStats();
                        } else if (adminChoice == 7) {
                            admin.viewFeedback();
                        } else if (adminChoice == 8) {
                            System.out.println("Logged out.");
                            break;
                        } else {
                            System.out.println("Invalid choice.");
                        }
                    }
                } else {
                    System.out.println("Invalid username or password.");
                }
            } else if (choice == 2) {
                boolean loggedIn = false;
                int visitorChoice;
                do {
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Go Back");

                System.out.print("Enter your choice: ");
                 visitorChoice = sc.nextInt();
                sc.nextLine();

                if (visitorChoice == 1) {
                    // Register
                    System.out.print("Enter Your Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Your Age: ");
                    int age = sc.nextInt();
                    System.out.print("Enter Your Phone Number: ");
                    String phone = sc.next();
                    System.out.print("Enter Your Balance: ");
                    double balance = sc.nextDouble();
                    System.out.print("Enter Your Email: ");
                    String email = sc.next();
                    System.out.print("Enter Your Password: ");
                    String password = sc.next();

                    Visitor newVis = new Visitor(name, age, phone, balance, email, password);
                    registeredVisitors.put(email, newVis);

                    System.out.println("\nRegistration is successful.\n");
                } else if (visitorChoice == 2) {
                    // Login
                    System.out.print("Enter Visitor Email: ");
                    String email = sc.next();
                    System.out.print("Enter Visitor Password: ");
                    String password = sc.next();

                    Visitor visitor = registeredVisitors.get(email);

                    if (visitor != null && visitor.getPassword().equals(password)) {
                        System.out.println("\nLogin Successful.\n");
                        loggedIn = true;
                    } else {
                        System.out.println("\nInvalid email or password.\n");
                    }if (loggedIn) {
                int ch;
                do {
                    System.out.println("Visitor Menu:");
                    System.out.println("1. Explore the Zoo");
                    System.out.println("2. Buy Membership");
                    System.out.println("3. Buy Tickets");
                    System.out.println("4. View Discounts");
                    System.out.println("5. View Special Deals");
                    System.out.println("6. Visit Animals");
                    System.out.println("7. Visit Attractions");
                    System.out.println("8. Leave Feedback");
                    System.out.println("9. Log Out");
                    System.out.print("Enter your choice: ");
                    ch = sc.nextInt();
                    sc.nextLine();

                    switch (ch) {
                        case (1):
                            visitor.exploreZoo();
                            break;
                        case 2:
                            visitor.buyMembership();
                            break;
                        case 3:
                            visitor.buyTickets();
                            break;
                        case 4:
                            admin.viewDiscounts();
                            break;
                        case 5:
                            admin.viewSpecialDeals();
                            break;
                        case 6:
                            visitor.visitAnimals();
                            break;
                        case 7:
                            visitor.visitAttractions();
                            break;
                        case 8:
                            System.out.println("Leave Feedback:\n" +
                                    "Enter your feedback (max 200 characters): ");
                            String abc = sc.nextLine();
                            admin.addFeedback(abc);
                            System.out.println("Thanks for providing feedback");
                            break;
                        case 9:
                            System.out.println("Logged out.");
                            loggedIn = false;
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                } while (ch != 9);
                    } else {
                        System.out.println("\nInvalid email or password.\n");
                    }
                } else if (visitorChoice==3) {
                    break;
                }
                } while (!loggedIn || visitorChoice != 2);
            }
            else if (choice == 3) {
                admin.viewSpecialDeals();
            } else {
                System.out.println("Invalid choice.");
            }}}}
