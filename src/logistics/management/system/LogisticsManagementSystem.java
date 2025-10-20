/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package logistics.management.system;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author dhana
 */
public class LogisticsManagementSystem {

    /**
     * @param args the command line arguments
     */
    public static void printMenu(String[] args) {
        // TODO code application logic here
        Scanner sc=new Scanner(System.in);
        
        
        boolean run=true;
        while(run){
        System.out.println("\\n===== LOGISTICS MANAGEMENT SYSTEM =====");
        System.out.println("1.Manage cities");
        System.out.println("2.Manage distance");
        System.out.println("3.Vehicles");
        System.out.println("4.Delivery request");
        System.out.println("5.Veiw reports");
        System.out.println("6.Select an option:");
        
        int option = sc.nextInt();
        
        switch(option){
                case 1 -> citiesManager.menu();
                break;
                case 2 -> distanceManager.menu(cityManager);
                break;
                case 3 -> vehicleManager.displayVehicles();
                break;
                case 4 -> deliveryManager.manageDelivery(cityManager);
                break;
                case 5 -> reportManager.veiwReports();
                break;
                case 6 -> fileHandler.saveData();
                break;
                default -> System.out.println("Invalid option.");
            
        }
        }
        sc.close();
    }

   

    public class citiesManager {

        private static void addCity() {
            if(cities.size()>=MAXNO_OFCITIES){
                System.out.println("City limit has reached.");
                return;
            }else{
            System.out.print("Enter city name: ");
            String name=input.nextLine();
            }
            if(cities.contains(name)){
               System.out.println("This city already exist.");
            }else{
               cities.add(name);
               System.out.println("City added.");}}
        }

        private static void renameCity() {

        }

        private static void removeCity() {

        }

        
        
        private static ArrayList<String> cities;
        private static final int MAXNO_OFCITIES=30;
        cities = new ArrayList<>();
        private static final Scanner input = new Scanner(System.in);
        
        private static void menu() {
            System.out.println("\\n--- CITY MANAGEMENT ---");
            System.out.println("1.Add city");
            System.out.println("2.Rename city");
            System.out.println("3.Remove city");
            System.out.println("Select an option:");
            
            int option;
            option = input.nextInt();
            
            switch(option){
                case 1 -> addCity();
                case 2 -> renameCity();
                case 3 -> removeCity();
                default -> System.out.println("Invalid option");
               
               
        
                    
            }
            
            
        }

        public citiesManager() {
        }
    }

     public class distanceManager {

        public distanceManager() {
        }
    }
     
    public class vehicleManager {

        private static void displayVehicles() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        public vehicleManager() {
        }
    }

    public class deliveryManager {

        public deliveryManager() {
        }
    }

    public class reportManager {

        private static void veiwReports() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        public reportManager() {
        }
    }

    public class fileHandler {

        private static void saveData() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        public fileHandler() {
        }
    }
    
}
