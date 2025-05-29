package org.example;

import java.util.*;

public class Librarian {
    Book instanceB = new Book("","",0);

    public String member[]=new String[100];
    public String phoneno[]=new String[100];

    public static int l=0;
    public void regmember(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Name : ");
        String Name=sc.nextLine();
        System.out.print("Age : ");
        int Age=sc.nextInt();
        sc.nextLine();
        System.out.print("Phone no: ");
        String Phone_no=sc.nextLine();
        member[l]=Name;
        phoneno[l]=Phone_no;
        System.out.println("Member" +Name+" Successfully registered with member ID "+Phone_no);
        l++;
    }
    public void remmember(){
        int vflag=0;
        for (int num = 0; num < l; num++) {
            if (!member[num].isEmpty()) {
                vflag = 1;
                break;
            }
        }if (vflag==0){
            System.out.println("No members registered");
            return;
        }
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Member Id: ");
        String memID = scanner.nextLine();
        int index = Arrays.asList(phoneno).indexOf(memID);
        member[index]="";
        phoneno[index]="";
        System.out.println("Member removed Successfully");
    }
    public void view() {
        for (int dis=0;dis<l;dis++) {

            System.out.println("Name: " + member[dis]);
            System.out.println("Member ID: " + phoneno[dis]);
            System.out.println("Book 1 : "+instanceB.issbook0[dis]);
            System.out.println("Book 2 : "+instanceB.issbook1[dis]);
            System.out.println("Total fine : "+instanceB.fine[dis]);

                }System.out.println();
            }



    public void librarianmenu(){
        Scanner sc=new Scanner (System.in);
        int c=0;
        while (c!=7){
            System.out.println("---------------------------------\n1. Register a member\n2. Remove a member\n3. Add a book\n4. Remove a book\n5. View all members \n6. View all books\n7. Back\n---------------------------------");
            c=sc.nextInt();
            if (c==1){
                regmember();
            }else if (c==2){
                remmember();
            }else if (c==3){
                instanceB.addBook();
            }else if (c==4){
                instanceB.rembook();
            }else if (c==5){
                view();
            }else if (c==6){
                instanceB.display();
            }else if (c==7){
                return;
            }else{
                System.out.println("Invalid input");
            }

        }
    }

}
