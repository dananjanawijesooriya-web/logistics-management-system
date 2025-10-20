/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package logistics.management.system;

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

   

    private static class citiesManager {

        private static void menu() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        public citiesManager() {
        }
    }

     private static class distanceManager {

        public distanceManager() {
        }
    }
     
    private static class vehicleManager {

        private static void displayVehicles() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        public vehicleManager() {
        }
    }

    private static class deliveryManager {

        public deliveryManager() {
        }
    }

    private static class reportManager {

        private static void veiwReports() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        public reportManager() {
        }
    }

    private static class fileHandler {

        private static void saveData() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        public fileHandler() {
        }
    }
    
}
