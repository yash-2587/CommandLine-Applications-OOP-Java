package org.example;

import java.util.*;
import java.time.LocalTime;
import java.time.Duration;
import java.util.Arrays;

import static org.example.Main.f;

public class Book {
    private static int bookId=-1;

    public String title;
    public String author;
    public int totalCopies;
    public String booktitle[]=new String[100];
    public String bookauthor[]=new String[100];
    public  String[] Avbook =new String[100];
    public  String Avtitle[]=new String[100];
    public String issbook0[]=new String[100];
    public String issbook1[]=new String[100];

    public long fine[]=new long[100];

    public int bid[]=new int[2];
    public LocalTime issuetime;
    public LocalTime rettime;
    public static int i=0;
    public Book( String title, String author, int totalCopies) {
        int bookId = getNextID();
        this.title = title;
        this.author = author;
        this.totalCopies = totalCopies;
        Arrays.fill(issbook0, "");
        Arrays.fill(issbook1, "");
    }

    // Getters
    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getTotalCopies() {
        return totalCopies;
    }

    // Setters
    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTotalCopies(int totalCopies) {
        this.totalCopies = totalCopies;
    }
    private static int getNextID() {
        return ++bookId;
    }
    public void display() {
        for (int j=0;j<i;j++){
            if (booktitle[j]!=""){
                System.out.println("Name: " + booktitle[j]);
                System.out.println("Author: " + bookauthor[j]);
                System.out.println("BookId: " + (j+1));
            }    }}
    public void addBook() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Book title: ");
        String newTitle = scanner.nextLine();

        System.out.print("Author: ");
        String newAuthor = scanner.nextLine();

        System.out.print("Copies: ");
        int newCopies = scanner.nextInt();

        Book newBook = new Book(newTitle, newAuthor, newCopies);
        for (int k=0;k<newCopies;k++){
            booktitle[i]=newTitle;
            bookauthor[i]=newAuthor;
            Avbook[i] = newTitle;
            Avtitle[i] = newAuthor;
            i++;
        }
        System.out.println("Book Added Successfully!");

    }
    public void rembook(){
        int kflag=0;
        for (int num = 0; num < i; num++) {
            if (!booktitle[num].isEmpty()) { // Use .isEmpty() to check for an empty string
                kflag = 1;
                break; // Exit the loop as soon as a non-empty member is found
            }
        }if (kflag==0){
            System.out.println("No books added");
            return;
        }
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Book Id: ");
        int ID = scanner.nextInt();
        booktitle[ID-1]="";
        bookauthor[ID-1]="";
        Avbook[ID - 1] = "";
        Avtitle[ID - 1] = "";
        System.out.println("Book removed Successfully");
    }
    public  void availbook(){
        System.out.println("Book Title\tBook Author\tBook Id");
        for(int lan=0;lan<i;lan++){
            if (Avbook[lan]!=""){
            System.out.println("\t"+Avbook[lan]+"\t\t"+Avtitle[lan]+"\t\t"+(lan+1));
        }}
    }

    public void mybook() {
        System.out.println("MY books\tbook id");
        if (issbook0[f].isEmpty()) {
            System.out.println("No book issued");
        } else if (!issbook0[f].isEmpty() && issbook1[f].isEmpty()) {
            System.out.println(issbook0[f] + "\t\t" + bid[0]);
        } else {
            System.out.println(issbook0[f] + "\t\t" + bid[0]);
            System.out.println(issbook1[f] + "\t\t" + bid[1]);
        }
    }

    public void issuebook() {
        if (fine[f] == 0) {
            if (issbook0[f].isEmpty()) {
                availbook();
                Scanner sc = new Scanner(System.in);
                System.out.print("Enter bookId : ");
                bid[0] = sc.nextInt();
                issbook0[f] = Avbook[bid[0] - 1];
                Avbook[bid[0] - 1] = "";
                Avtitle[bid[0] - 1] = "";
                issuetime = LocalTime.now();
                System.out.println("Your book has been issued");

            } else if (!issbook0[f].isEmpty() && issbook1[f].isEmpty()) {
                availbook();
                Scanner sc = new Scanner(System.in);
                System.out.print("Enter bookId : ");
                bid[1] = sc.nextInt();
                issbook1[f] = Avbook[bid[1] - 1];
                Avbook[bid[1] - 1] = "";
                Avtitle[bid[1] - 1] = "";
                issuetime = LocalTime.now();
                System.out.println("Your book has been issued");

            } else {
                System.out.println("Two books already issued");
            }
        } else {
            System.out.println("Please pay your fine first");
        }
    }



    public void retbook(){
        mybook();
        if (issbook0[f]==""&&issbook1[f]=="") {
            return;
        }else{
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter bookId : ");
        int b=sc.nextInt();
        if (bid[0]==b&&bid[1]==0){
            issbook0[f]="";
            Avbook[b-1]=booktitle[b-1];
            Avtitle[b-1]=bookauthor[b-1];
            bid[0]=0;

        }else if(bid[0]==b&&bid[1]!=0){
            issbook0[f]=issbook1[f];
            Avbook[b-1]=booktitle[b-1];
            Avtitle[b-1]=bookauthor[b-1];
            bid[0]=bid[1];
            issbook1[f]="";
            bid[1]=0;

        }else if (bid[1]==b){
            issbook1[f]="";
            Avbook[b-1]=booktitle[b-1];
            Avtitle[b-1]=bookauthor[b-1];
            bid[1]=0;

        }

         rettime = LocalTime.now();
        System.out.println("your book has been returned");
        calculatefine();
        System.out.println("Please pay your fine of Rs "+fine[f]);
    }}

    public  void calculatefine(){
        long secondsDifference = Duration.between(issuetime, rettime).getSeconds();
        fine[f]=fine[f]+((secondsDifference-10)*3);

    }
    public void payfine(){
        try {
        calculatefine();
        if (fine[f] >0){
            System.out.println("your fine of Rs "+fine[f]+" has been paid successfully");
        }else{
            System.out.println("No fine overdue");
        }fine[f]=0;
            }
        catch (NullPointerException e){
            System.out.println("No fine overdue");
        }
}public void memmenu(){
        Scanner sc=new Scanner (System.in);
        int c=0;
        while (c!=6){
            System.out.println("---------------------------------\n" +
                    "1. List Available Books\n" +
                    "2. List My Books\n" +
                    "3. Issue book\n" +
                    "4. Return book\n" +
                    "5. Pay Fine\n" +
                    "6. Back\n" +
                    "---------------------------------\n");
            c=sc.nextInt();
            if (c==1){
                availbook();
            }else if (c==2){
                mybook();
            }else if (c==3){
                issuebook();
            }else if (c==4){
                retbook();
            }else if (c==5) {
                payfine();
            }else if (c==6){
                return;
            }else{
                System.out.println("Invalid input");
            }
        }}

}

