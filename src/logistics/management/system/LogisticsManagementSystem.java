/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package logistics.management.system;

import LogisticsManagementSystem.LogisticsManagementSystem.CityManager;
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
     public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        CityManager cityManager = new CityManager();
        DistanceManager distanceManager = new DistanceManager();
        VehicleManager vehicleManager = new VehicleManager();
        Delivery deliveryManager = new DeliveryManager(distanceManager, vehicleManager);
        ReportManager reportManager = new ReportManager(deliveryManager);
        FileHandler fileHandler = new FileHandler(cityManager, distanceManager, deliveryManager);

        
        fileHandler.loadData();

        boolean run = true;

        while (run) {

            System.out.println("\n===== LOGISTICS MANAGEMENT SYSTEM =====");
            System.out.println("1. Manage Cities");
            System.out.println("2. Manage Distances");
            System.out.println("3. View Vehicles");
            System.out.println("4. Delivery request");
            System.out.println("5. Reports");
            System.out.println("6. Save");
            System.out.print("Choose an option: ");

         int option = input.nextInt();

            switch (option) {
                case 1:
                    cityManager.menu();
                    break;
                case 2:
                    distanceManager.menu(cityManager);
                    break;
                case 3:
                    vehicleManager.showVehicles();
                    break;
                case 4:
                    deliveryManager.handleDelivery(cityManager);
                    break;
                case 5:
                    reportManager.showReports();
                    break;
                case 6:
                    fileHandler.saveData();
                    System.out.println("Data saved.");
                    run = false;
                    break;
                default:
                    System.out.println("Invalid option.try again.");
            }
        }

        input.close();
    }

   

    public class CityManager {

        private ArrayList<String> cities;
        private final int MAX_CITIES = 30;
        private Scanner input = new Scanner(System.in);

        public CityManager() {
        cities = new ArrayList<>();
    }

        public void menu() {
           System.out.println("\n--- CITY MANAGEMENT ---");
           System.out.println("1. Add City");
           System.out.println("2. Rename City");
           System.out.println("3. Remove City");
           System.out.print("Select option: ");

           int option = input.nextInt();
           input.nextLine();

           switch (option) {
              case 1:
                addCity();
                break;
              case 2:
                renameCity();
                break;
              case 3:
                removeCity();
                break;
              default:
                System.out.println("Invalid option.");
        }
    }
            private void addCity() {
               if (cities.size() >= MAX_CITIES) {
               System.out.println("City limit reached.");
               return;
        }

               System.out.print("Enter city name: ");
               String name = input.nextLine();

               if (!cities.contains(name)) {
               cities.add(name);
               System.out.println("City added.");
               } else {
               System.out.println("City is already exists.");
          }
      }

            private void renameCity() {
               
               System.out.print("Enter the index no of city to rename: ");
               int index = input.nextInt();
               input.nextLine();
               if (index >= 0 && index < cities.size()) {
               System.out.print("Enter new name: ");
               cities.set(index, input.nextLine());
               System.out.println("City renamed.");
               } else {
               System.out.println("Invalid index no.");
          }
      }

            private void removeCity() {
                
                System.out.print("Enter the index no of city to remove: ");
                int index = input.nextInt();
                if (index >= 0 && index < cities.size()) {
                    cities.remove(index);
                    System.out.println("City removed.");
                } else {
                    System.out.println("Invalid index no.");
          }
      }
          

            public ArrayList<String> getCities() {
                return cities;
    }
   }
  }
            
            
        

        
    
    
    

