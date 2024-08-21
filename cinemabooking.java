 import java.util.*;
        public class cinemabooking {
            private static char[] seats;
            private static int rows;
            private static int noOfSeats;
            private static int seatBookCount=0;
            private static double standard=0,pensioner=0,frequent=0;
            private static int[] Sarray;
            private static int[] Parray;
            private static int[] Farray;
            private static Scanner input= new Scanner(System.in);

            public static void main(String [] args){
                System.out.print("Enter the number of rows in the cinema : ");
                rows = input.nextInt();
                noOfSeats = rows*10;
                seats = new char[noOfSeats];
                Sarray = new int[noOfSeats];
                Parray = new int[noOfSeats];
                Farray = new int[noOfSeats];

                System.out.println("enter date");
                String date = input.next();

                System.out.print("Enter the cost of a Standard seat : ");
                standard = input.nextInt();
                System.out.print("Enter the cost for a Pensioner : ");
                pensioner = input.nextInt();
                System.out.print("Enter the cost for a Frequent patron : ");
                frequent =  input.nextInt();

                while(true){
                    System.out.println("**************************************************");
                    System.out.println("            CINEMA BOOKING SYSTEM");
                    System.out.println("**************************************************");
                    System.out.println("1. Display available seats");
                    System.out.println("2. Book a seat");
                    System.out.println("3. Refund a seat");
                    System.out.println("4. Displaying a report");
                    System.out.println("0. exit");

                    int choice = input.nextInt();
                    switch(choice){
                        case 1:
                            displaySeat();
                            break;
                        case 2:
                            bookseat();
                            break;
                        case 3:
                            refundseat();
                            break;
                        case 4:
                             displayreport();
                            break;
                        case 0:
                            System.exit(0);
                            break;
                        default:
                            System.out.println("Enter valid choice");
                    }
                }


            }


            //function to display the seating
            public static void displaySeat() {
                int seat = 0;
                if(rows>1){
                    System.out.print("     ");
                }

                for (int row = 0; row < rows; row++) {
                    for (int tempseat = 0; tempseat < 10; tempseat++) {
                        if(tempseat==5){
                            System.out.print("  ");
                        }
                        if (seats[seat] == 0) {
                            System.out.print((seat + 1) + ":- ");
                        }else{
                            System.out.print(String.valueOf(seats[seat])+(seat+1)+" ");
                        }
                        seat++;
                    }
                    System.out.println();
                }
            }

            public static void bookseat(){
                System.out.println("*************************************");
                System.out.println("           Buying seat");
                System.out.println("*************************************");
                char type = 0;
                int seatNo,typeS=0,typeP=0,typeF=0,Noofbookedseat=0;
                System.out.print("Enter the number of seats that needs to be bought: ");
                int buyNum = input.nextInt();
                if((buyNum>noOfSeats)||(buyNum<1)){
                    System.out.println("Invalid Seat number enterd");
                    return;
                }
                while((buyNum <= noOfSeats) && (buyNum > 0)){
                    while(true){
                        System.out.print("Enter the seat number : ");
                        seatNo = input.nextInt();
                        if ((seatNo <= noOfSeats) && (seatNo > 0)) {
                            if (seats[seatNo - 1] != 0) {
                                System.out.println("The seat has already been booked.");
                            } else {
                                break;
                            }
                        } else {
                            System.out.println("The seat number you entered is not valid. Please try again.");
                        }

                    }

                    while (true){
                        System.out.println("What is the ticket type : ");
                        System.out.println("1. standard");
                        System.out.println("2. pensioner");
                        System.out.println("3. Frequent patron");
                        int tickettype = input.nextInt();
                        switch (tickettype){
                            case 1:
                                type = 'S';
                                typeS+=1;
                                Sarray[typeS]=seatNo;
                                break;

                            case 2:
                                type = 'P';
                                typeP+=1;
                                Parray[typeP]=seatNo;
                                break;

                            case 3:
                                type = 'F';
                                typeF+=1;
                                Farray[typeF]=seatNo;
                                break;

                            default:
                                System.out.println("The type you entered is not valid, please try again");
                                continue;
                        }
                        break;
                    }
                    seats[seatNo-1]=type;
                    buyNum--;
                    System.out.println("seat "+ seatNo +" purchased successfully");
                    Noofbookedseat+=1;
                    seatBookCount+=1;
                    System.out.println();
                }

                System.out.println("Receipt");
                System.out.println("*********");
                System.out.println("Number of seats booked : "+Noofbookedseat);
                System.out.print(typeS +"* Standard @ $"+standard+" = $"+(standard*typeS)+" seat(s):");
                printArray(Sarray);
                clearArray(Sarray);

                System.out.println();
                System.out.print(typeP +"* Standard @ $"+pensioner+" = $"+(pensioner*typeP)+" seat(s):");
                printArray(Parray);
                clearArray(Parray);

                System.out.println();
                System.out.print(typeF +"* Standard @ $"+frequent+" = $"+(frequent*typeF)+" seat(s):");
                printArray(Farray);
                clearArray(Farray);
                System.out.println();
                System.out.println("               Total : $"+((standard*typeS)+(pensioner*typeP)+(frequent*typeF)));

            }
            public static void refundseat(){
                int refund = 0,typeS=0,typeP=0,typeF=0,refundNum=0;
                System.out.println("*************************************");
                System.out.println("           Refund seat");
                System.out.println("*************************************");
                System.out.println("Enter the number of seats to refund");
                int refundcount = input.nextInt();
                if((refundcount>noOfSeats)||(refundcount<1)){
                    System.out.println("Invalid Seat number enterd");
                    return;
                }
                if(refundcount>seatBookCount){
                    System.out.println("There is no that much seats booked to cancel");
                    return;
                }
                while((refundcount <= noOfSeats) && (refundcount > 0)){
                    while (true){
                        System.out.print("Enter Seat number to refund: ");
                        refund = input.nextInt();
                        if((refund<noOfSeats)&&(refund>0)){
                            if(seats[refund-1]==0){
                                System.out.println("The seat you eneterd is not yet booked");
                            }else{
                                refundNum+=1;
                                if(seats[refund-1]=='S'){
                                    typeS+=1;
                                    Sarray[refundNum]=refundNum;
                                }
                                else if(seats[refund-1]=='P'){
                                    typeP+=1;
                                    Parray[refundNum]=refundNum;
                                }else {
                                    typeF+=1;
                                    Farray[refundNum]=refundNum;
                                }
                                    seats[refund-1]=0;
                                    System.out.println("Seat "+refund+ " refunded successfully");
                                    break;

                            }
                        }else{
                            System.out.println("Enter valid seat number.");
                        }
                    }
                    refundcount--;
                }

                System.out.println("Receipt");
                System.out.println("*********");
                System.out.println("Number of seats refunded : "+refundcount);
                System.out.print(typeS +"* Standard @ $"+standard+" = $"+(standard*typeS)+" seat(s):");
                printArray(Sarray);
                clearArray(Sarray);

                System.out.println();
                System.out.print(typeP +"* Standard @ $"+pensioner+" = $"+(pensioner*typeP)+" seat(s):");
                printArray(Parray);
                clearArray(Parray);

                System.out.println();
                System.out.print(typeF +"* Standard @ $"+frequent+" = $"+(frequent*typeF)+" seat(s):");
                printArray(Farray);
                clearArray(Farray);
                System.out.println();
                System.out.println("               Total : $"+((standard*typeS)+(pensioner*typeP)+(frequent*typeF)));
            }

            public static void clearArray(int[] arrayname){
                for(int temp=0;temp<arrayname.length;temp++){
                    arrayname[temp]=0;
                }
            }

            public static void printArray(int[] arrayname){
                for(int temp:arrayname){
                    if(temp!=0){
                        System.out.print(temp+",");
                    }
                }
            }

            public static void displayreport(){
                System.out.println("*******************************");
                System.out.println("Seats sold is "+seatBookCount);
                System.out.println("percentage of seats sold is: "+((seatBookCount/seats.length)*100)+"%");
                System.out.println("The average seat price is: "+((standard+pensioner+frequent)/3));
            }

        }




