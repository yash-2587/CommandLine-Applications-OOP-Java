# 🦁 Zootopia – A Zoo Management System

This is a **Java-based Zoo Management System** built using **Object-Oriented Programming (OOP)** principles. Developed in **IntelliJ IDEA** with **Maven**, the project simulates the functioning of a real-world zoo with separate functionalities for **Admins** and **Visitors**.

---

## 🚀 Features

### 🔐 Main Menu Options
- **Enter as Admin**
- **Enter as Visitor**
- **Show Special Deals**

---

## 👨‍💼 Admin Section


### 📋 Admin Menu Options
1. **Manage Attractions**
2. **Manage Animals**
3. **Schedule Events**
4. **Set Discounts**
5. **Set Special Deal**
6. **View Visitor Stats**
7. **View Feedback**
8. **Exit (Back to Main Menu)**

---

## 🙋 Visitor Section

### 🧾 Visitor Options
- **Register** (New member registration)
- **Login** (Using Email ID and Password)

### 📲 Visitor Menu Options
1. **Explore the Zoo**
2. **Buy Membership** (Basic / Premium)
3. **Buy Tickets**
4. **View Discounts**
5. **View Special Deals**
6. **Visit Animals**
7. **Visit Attractions**
8. **Leave Feedback**
9. **Log Out**

---

## ⚠️ Error Handling & Input Validation

- Custom `getInt()` function ensures proper input type handling (e.g., prevents string instead of int).
- Age must be **greater than 0**.
- Balance cannot be **negative**.
- Category input is **case-sensitive** (e.g., must type `Mammal`, not `mammal`).
- Invalid login attempts for Admin or Visitor are handled with clear error messages.

---

## 📦 Project Structure

- **Home Folder (main)**: Located in `src` directory.
- **Compiled JAR file**: Found in `target` directory.

---

## 📌 Assumptions Made

- All attractions are **closed** by default.
- Six hardcoded animals:  
  - **2 Mammals**  
  - **2 Amphibians**  
  - **2 Reptiles**
- Two hardcoded **special deals** exist at startup.
- All **attraction prices are set to 0** initially and must be updated via scheduling.
- Visitors must purchase membership before exploring zoo content.
- **Category names are case-sensitive** when adding animals.

---

## 🧱 Class Overview

### 🐾 `Animal` (Abstract Class)
- Subclasses: `Mammal`, `Amphibian`, `Reptile`
- Attributes: `id`, `name`, `type`, `sound`, `description`

### 🛠️ `Admin`
- Attributes: `username`, `password`  
- Two predefined admin users

### 🎟️ `SpecialDeal`
- Attributes: `attractionCount`, `discount`, `id`

### 💸 `Discount`
- Attributes: `percentage`, `discountCode`, `category` (minor/senior)

### 🎠 `Attraction`
- Attributes: `id`, `name`, `description`, `price`, `ticketSold`, `status`

### 👤 `Visitor`
- Attributes: `name`, `age`, `phoneNumber`, `balance`, `email`, `password`, `ticketsPurchased (List)`

### 💬 `Feedback`
- Attribute: `message` string

### 🪪 `Membership` (Abstract Class)
- Subclasses: `Basic`, `Premium`

### 🏛️ `Zoo`
- Central class with all lists and logic implementation
- Functions like `getInt()`, admin/visitor interfaces, and event handling

---

## 💡 Interfaces Implemented

### 🧾 `Discount`
- `void buyTicket()`
- `void applyDiscount()`

### 💬 `FeedbackInterface`
- `void setFeedback(String s)`
- `String getFeedback()`

### 🎫 `Visitor`
- `int buyTicket(int id, double price, int percentage)`
- `int visitAttraction(int id)`
- `void buyMembership(int id, int discountPercentage, int ageCategory)`

---

## 🛑 Exit Strategy

- No direct system exit
- Always returns to the main menu with:
  - **Enter as Admin**
  - **Enter as Visitor**
  - **Show Special Deals**

---

## 💻 Tech Stack

- **Java (JDK 8+)**
- **Maven** (for dependency and build management)
- **IntelliJ IDEA** (as the IDE)
- **Command-Line Interface** (CLI) for user interaction

---

## 📝 Notes

- Ticket pricing and attraction status must be set by the Admin.
- Visitors cannot interact with attractions or animals without a valid membership.
- The project demonstrates real-life inspired **zoo operations**, incorporating **roles, permissions, discounts**, and **feedback systems**.