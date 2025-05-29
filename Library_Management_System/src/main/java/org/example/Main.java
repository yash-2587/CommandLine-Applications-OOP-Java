package org.example;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// problem while taking an input for phone no
import java.util.*;

public class Main
{
    public static int f=0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        Librarian librarian = new Librarian();

        while (choice!=3){
            System.out.println("Library Portal Initialized….\n ---------------------------------\n1. Enter as a librarian\n2. Enter as a member\n3. Exit\n---------------------------------");
            choice = sc.nextInt();
            if (choice==1){

                librarian.librarianmenu();

                 }else if (choice==2){
                   System.out.print("Name :");
                   String name = sc.next();
                    sc.nextLine();
                   System.out.print("Phone No. :");
                   String phone_no=sc.nextLine();
                   int flag=0;
                   for (int i=0;i<librarian.member.length;i++){
                       if (librarian.member[i].equals(name) && librarian.phoneno[i].equals(phone_no)) {
                           f=i;
                           System.out.println("Welcome "+name+" Member ID : "+phone_no);
                            librarian.instanceB.memmenu();
                            flag=1;
                            break;
                       }
                   }if (flag==0) {
                    System.out.println("Member with Name: " + name + " and Phone No: " + phone_no + " doesn't exist.");
                }

            }else if (choice == 3){
                System.out.println("---------------------------------\nThanks for visiting!\n---------------------------------");
            }
            else{
                System.out.println("Invalid input");
            }
        }
    }}
