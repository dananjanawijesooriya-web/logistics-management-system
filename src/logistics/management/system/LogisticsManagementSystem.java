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
        public class DistanceManager{
            public void menu(CityManager cityManager){
               private final int MAX_CITIES=30;
               private int[][] distance=new int[ MAX_CITIES][ MAX_CITIES];
               private Scanner input=new Scanner(System.in);
            
            for(int i=0; i<MAX_CITIES; i++){
               for(int j=0; j<MAX_CITIES; j++){
                  if(i==j){
                     distance[i][j]=0;
                  }else{
                     distance[i][j]=-1;
                  }
                }}
            while(true){
                System.out.println("\n====Distance Manager====");
                System.out.println("1.Disatance between 2 cities");
                System.out.println("2.Show the distance table");
                System.out.println("Choose an option");
                int option=input.nextInt();
            
                switch(option){
                    case 1:
                       System.out.println("Enter the 1st city index(0 - "+(MAX_CITIES-1)+"):");
                       int city1=input.nextInt();
                       System.out.println("Enter the 2nd city index(0 - "+(MAX_CITIES-1)+"):");
                       int city2=input.nextInt();
                       if(city1==city2){ 
                          System.out.println("A city cannot have a distance.");
                          break;
        }              System.out.print("Enter the distance between city " + city1 + " and city " + city2 + ": ");
                       int dis=input.nextInt();
                       
                       distance[city1][city2]=dis;
                       distance[city2][city1]=dis;
                       System.out.println("Distance updated.");
                       break;
                    
                    case 2:
                       System.out.println("====Distance table====");
                       System.out.println("                ");
                       for(int k=0; k<MAX_CITIES; k++){
                          System.out.printf("%10s","City"+k);
                          for(int r=0; r<MAX_CITIES; r++){
                             if(distance[k][r]==-1){
                                System.out.printf("%10s","-");
                            }else{
                                System.out.printf("%10d", distance[k][r]);
                                }
                        }       System.out.println();
                       } break;
                    default:
                        System.out.println("Invalid option.");
                     }
        
                   }
                } 
            public class VehicleManager{
            
                private String[] vehicles={"Van","Track","Lorry"};
                private int[] capacities={1000,5000,10000};
                private double[] rates={30,40,80};
                private double[] avgspeeds={60,50,45};
                private double[] efficiencies={12,6,4};
            
                public void showVehicles(){
                    System.out.println("\n====Vehicles====");
                    System.out.println("Index\tType\tCapacity\tRate/km\tSpeed\tEfficiency");
                    for(int p=0; p<vehicles.length; p++){
                        System.out.println(p + "\t" + vehicles[p]+"\t"+capacities[p]+"\t"+rates[p]+"\t"+avgspeeds[p]+"\t"+efficiencies[p]);
                         
                    }
                }
                public int getCapacity(int index){return capacities[index];}
                public double getRate(int index){return rates[index];}
                public double getAvgspeed(int index){return avgspeeds[index];}
                public double getEfficiency(int index){return efficiencies[index];}
                public String getVehiclestype(int index){return vehicles[index];}
              }
            
            public class DeliveryManager{
                
                private final int MAX_DELIVERY=50;
                private ArrayList<Delivery> deliveries;
                private DistanceManager distanceManager;
                private VehicleManager vehicleManager;
                private final double fuel_price=310.0;
                private Scanner input=new Scanner(System.in);
                
                public DeliveryManager(DistanceManager distanceManagerParam, VehicleManager vehicleManagerParam) {
                    distanceManager = distanceManagerParam;
                    vehicleManager = vehicleManagerParam;
                    deliveries = new ArrayList<>();
                }
                public void deliveryHandle(CityManager cityManager){
                    System.out.println("Enter source city index: ");
                    int sci=input.nextInt();
                    System.out.println("Enter destination city index: ");
                    int dsi=input.nextInt();
                    
                    if(sci==dsi){
                        System.out.println("Source city and destination city can't be same.");
                        return;
                    }
                    vehicleManager.showVehicles();
                    System.out.println("Choose vehicle index: ");
                    int vehicleindex=input.nextInt();
                    System.out.println("enter the weight(kg): ");
                    double weight=input.nextInt();
                    
                    if(weight>vehicleManager.getCapacity(vehicleindex)){
                        System.out.println("Weight exceeds vehicle capacity.");
                        return;
                    }
                    int distance=distanceManager.getDistance(sci,dsi);
                    double rate=vehicleManager.getRate(vehicleindex);
                    double avgspeed=vehicleManager.getAvgspeed(vehicleindex);
                    double efficiencies=vehicleManager.getEfficiency(vehicleindex);
                    
                    double delivery_cost=distance*rate*(1+weight/10000);
                    double delivery_time=distance/avgspeed;
                    double fuel_consumption=distance/efficiencies;
                    double fuel_cost=fuel_consumption*fuel_price;
                    double total_cost=delivery_cost+fuel_cost;
                    double profit=delivery_cost*0.25;
                    double final_customer_charge=total_cost+profit;
                    
                    System.out.println("\n====DELIVERY SUMMARY====");
                    System.out.println("From: "+cityManager.getCities().get(sci));
                    System.out.println("To: "+cityManager.getCities().get(dsi));
                    System.out.println("Distance: "+distance+"km");
                    System.out.println("Vehicle: "+vehicleManager.getVehiclestype(vehicleindex));
                    System.out.println("Base cost: "+delivery_cost+"LKR");
                    System.out.println("Fuel used: "+fuel_consumption);
                    System.out.println("Fuel cost: "+fuel_cost+"LKR");
                    System.out.println("Operational Cost: "+total_cost+"LKR");
                    System.out.println("Profit: "+profit+"LKR");
                    System.out.println("Customer Charge: "+final_customer_charge+"LKR");
                    System.out.println("Estimated Time: "+delivery_time+"hours");
                    
                    deliveries.add(new Delivery(sci,dsi,weight,vehicleindex,distance,total_cost,profit,final_customer_charge,delivery_time));
                    
                } 
                public ArrayList<Delivery>getDeliveries(){return deliveries;}
            }
            public class Delivery{

                public Delivery(int sci, int dsi, double weight, int vehicleindex, int distance, double total_cost, double profit, double final_customer_charge, double delivery_time) {

                }
                
            }
            }
             
            
                
            
        

    
            
            
        

        
    
    
    

