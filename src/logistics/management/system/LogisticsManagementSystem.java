/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package logistics.management.system;

import com.sun.tools.javac.Main;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.PrintWriter;

/**
 *
 * @author dhana
 */
public class LogisticsManagementSystem {
    static ReportManager reportManager;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        
        CityManager cityManager = new CityManager();
        DistanceManager distanceManager = new DistanceManager();
        VehicleManager vehicleManager = new VehicleManager();
        DeliveryManager deliveryManager = new DeliveryManager(distanceManager, vehicleManager);
        Report Manager reportManager = new ReportManager(deliveryManager);
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
            System.out.println("6. find least-cost route");
            System.out.println("7.save");
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
                    deliveryManager.deliveryHandle(cityManager);
                    break;
                case 5:
                    reportManager.showReports();
                    break;
                case 6:
                    RouteFinder routeFinder = new RouteFinder(distanceManager);
                    cityManager.listCities();
                    System.out.println("Enter up to 4 city indexes separated by spaces:");
                    input.nextLine();
                    String[] parts = input.nextLine().split(" ");
                    ArrayList<Integer> selectedCities = new ArrayList<>();
                    for (String p : parts) {
                        selectedCities.add(Integer.parseInt(p));
                    }
                    routeFinder.findBestroute(selectedCities, cityManager);
                    break;
                case 7:
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

    public static class CityManager {

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

            System.out.print("Enter the id no of city to rename: ");
            int id = input.nextInt();
            input.nextLine();
            if (id >= 0 && id < cities.size()) {
                System.out.print("Enter new name: ");
                cities.set(id, input.nextLine());
                System.out.println("City renamed.");
            } else {
                System.out.println("Invalid id no.");
            }
        }

        private void removeCity() {

            System.out.print("Enter the id no of city to remove: ");
            int id = input.nextInt();
            if (id >= 0 && id < cities.size()) {
                cities.remove(id);
                System.out.println("City removed.");
            } else {
                System.out.println("Invalid id no.");
            }
        }

        public ArrayList<String> getCities() {
            return cities;
        }

        public void listCities() {
            System.out.println("\n===Cities===");
            for (int c = 0; c < cities.size(); c++) {
                System.out.println(c + ":" + cities.get(c));
            }
        }
    }

        public static class DistanceManager {

            private final int MAX_CITIES = 30;
            private int[][] distance = new int[MAX_CITIES][MAX_CITIES];
            private Scanner input = new Scanner(System.in);

            public DistanceManager() {
                distance = new int[MAX_CITIES][MAX_CITIES];
            }

            public void menu(CityManager cityManager) {
                System.out.println("\n--- DISTANCE MANAGEMENT ---");
                System.out.println("1. Set Distance");
                System.out.println("2. Display Distance Table");
                System.out.print("Select option: ");

                int choice = input.nextInt();

                switch (choice) {
                    case 1:
                        setDistance(cityManager);
                        break;
                    case 2:
                        displayDistance(cityManager);
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            }

            private void setDistance(CityManager cityManager) {
                cityManager.listCities();
                System.out.print("Enter source city index: ");
                int sci = input.nextInt();
                System.out.print("Enter destination city index: ");
                int dsi = input.nextInt();

                if (sci == dsi) {
                    System.out.println("Distance to itself must be 0.");
                    return;
                }

                System.out.print("Enter distance in km: ");
                int dist = input.nextInt();

                distance[sci][dsi] = dist;
                distance[dsi][sci] = dist;

                System.out.println("Distance updated successfully.");
            }

            private void displayDistance(CityManager cityManager) {
                var cities = cityManager.getCities();
                System.out.println("\nDistance Table:");

                System.out.print("\t");
                for (String name : cities) {
                    System.out.print(name + "\t");
                }
                System.out.println();

                for (int i = 0; i < cities.size(); i++) {
                    System.out.print(cities.get(i) + "\t");
                    for (int j = 0; j < cities.size(); j++) {
                        System.out.print(distance[i][j] + "\t");
                    }
                    System.out.println();
                }
            }

            public int getDistance(int sci, int dsi) {
                return distance[sci][dsi];
            }

            private void setDistance(int i, int j, int distance) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        }

        private static class VehicleManager {

            private String[] vehicles = {"Van", "Track", "Lorry"};
            private int[] capacities = {1000, 5000, 10000};
            private double[] rates = {30, 40, 80};
            private double[] avgspeeds = {60, 50, 45};
            private double[] efficiencies = {12, 6, 4};

            public void showVehicles() {
                System.out.println("\n====Vehicles====");
                System.out.println("Index\tType\tCapacity\tRate/km\tSpeed\tEfficiency");
                for (int p = 0; p < vehicles.length; p++) {
                    System.out.println(p + "\t" + vehicles[p] + "\t" + capacities[p] + "\t" + rates[p] + "\t" + avgspeeds[p] + "\t" + efficiencies[p]);

                }
            }

            public int getCapacity(int index) {
                return capacities[index];
            }

            public double getRate(int index) {
                return rates[index];
            }

            public double getAvgspeed(int index) {
                return avgspeeds[index];
            }

            public double getEfficiency(int index) {
                return efficiencies[index];
            }

            public String getVehiclestype(int index) {
                return vehicles[index];
            }
        }

        public static class DeliveryManager {

            private final int MAX_DELIVERY = 50;
            private ArrayList<Delivery> deliveries;
            private DistanceManager distanceManager;
            private VehicleManager vehicleManager;
            private final double fuel_price = 310.0;
            private Scanner input = new Scanner(System.in);

            public DeliveryManager(DistanceManager distanceManagerParam, VehicleManager vehicleManagerParam) {
                distanceManager = distanceManagerParam;
                vehicleManager = vehicleManagerParam;
                deliveries = new ArrayList<>();
            }

            public void deliveryHandle(CityManager cityManager) {

                System.out.println("Enter source city index: ");
                int sci = input.nextInt();
                System.out.println("Enter destination city index: ");
                int dsi = input.nextInt();

                if (sci == dsi) {
                    System.out.println("Source city and destination city can't be same.");
                    return;
                }
                vehicleManager.showVehicles();
                System.out.println("Choose vehicle index: ");
                int vehicleindex = input.nextInt();
                System.out.println("enter the weight(kg): ");
                double weight = input.nextInt();

                if (weight > vehicleManager.getCapacity(vehicleindex)) {
                    System.out.println("Weight exceeds vehicle capacity.");
                    return;
                }
                int distance = distanceManager.getDistance(sci, dsi);
                double rate = vehicleManager.getRate(vehicleindex);
                double avgspeed = vehicleManager.getAvgspeed(vehicleindex);
                double efficiencies = vehicleManager.getEfficiency(vehicleindex);

                double delivery_cost = distance * rate * (1 + weight / 10000);
                double delivery_time = distance / avgspeed;
                double fuel_consumption = distance / efficiencies;
                double fuel_cost = fuel_consumption * fuel_price;
                double total_cost = delivery_cost + fuel_cost;
                double profit = delivery_cost * 0.25;
                double final_customer_charge = total_cost + profit;

                System.out.println("\n====DELIVERY SUMMARY====");
                System.out.println("From: " + cityManager.getCities().get(sci));
                System.out.println("To: " + cityManager.getCities().get(dsi));
                System.out.println("Distance: " + distance + "km");
                System.out.println("Vehicle: " + vehicleManager.getVehiclestype(vehicleindex));
                System.out.println("Base cost: " + delivery_cost + "LKR");
                System.out.println("Fuel used: " + fuel_consumption);
                System.out.println("Fuel cost: " + fuel_cost + "LKR");
                System.out.println("Operational Cost: " + total_cost + "LKR");
                System.out.println("Profit: " + profit + "LKR");
                System.out.println("Customer Charge: " + final_customer_charge + "LKR");
                System.out.println("Estimated Time: " + delivery_time + "hours");

                deliveries.add(new Delivery(sci, dsi, weight, vehicleindex, distance, total_cost, profit, final_customer_charge, delivery_time));

            }

            public ArrayList<Delivery> getDeliveries() {
                return deliveries;
            }
        }

        public static class Delivery {

            private int sci, dsi, vehicleid;
            private double weight, distance, total_cost, profit, final_customer_charge, delivery_time;

            public Delivery(int sci, int dsi, double weight, int vehicleid, int distance, double total_cost, double profit, double final_customer_charge, double delivery_time) {
                this.sci = sci;
                this.dsi = dsi;
                this.weight = weight;
                this.vehicleid = vehicleid;
                this.distance = distance;
                this.total_cost = total_cost;
                this.profit = profit;
                this.final_customer_charge = final_customer_charge;
                this.delivery_time = delivery_time;
            }

            public double getDistance() {
                return distance;
            }

            public double getProfit() {
                return profit;
            }

            public double getCharge() {
                return final_customer_charge;
            }

            public double getDeliverytime() {
                return delivery_time;
            }

            public int getSci() {
                return sci;
            }

            public int getDsi() {
                return dsi;
            }

            public double getWeight() {
                return weight;
            }

            public int getVehicleid() {
                return vehicleid;
            }

            public double getTotal_cost() {
                return total_cost;
            }

        }

        public class ReportManager {

            private DeliveryManager deliveryManager;

            public ReportManager(DeliveryManager deliverymanager) {
                this.deliveryManager = deliverymanager;
            }

            public void showReports() {
                ArrayList<Delivery> deliveries = deliveryManager.getDeliveries();

                double totaldis = 0;
                double totalprofit = 0;
                double totalrvn = 0;
                double totaltime = 0;
                double maxdis = 0;
                double mindis = 0;

                for (Delivery d : deliveries) {
                    totaldis += d.getDistance();
                    totalprofit += d.getProfit();
                    totalrvn += d.getCharge();
                    totaltime += d.getDeliverytime();

                    if (d.getDistance() > maxdis) {
                        maxdis = d.getDistance();
                    }
                    if (d.getDistance() < mindis) {
                        mindis = d.getDistance();
                    }

                }
                System.out.println("\n====Delivery Report====");
                System.out.println("Total deliveries: " + deliveries.size());
                System.out.println("Total distance: " + totaldis + "km");
                System.out.println("Average deliverytime: " + (totaltime / deliveries.size()) + "hours");
                System.out.println("Total revenue: " + totalrvn + "LKR");
                System.out.println("Total profit: " + totalprofit + "LKR");
                System.out.println("Longest route: " + maxdis + "km");
                System.out.println("Shortest route: " + mindis + "km");
            }

            public static class RouteFinder {

                private DistanceManager distanceManager;

                public RouteFinder(DistanceManager distancemanager) {
                    this.distanceManager = distancemanager;
                }

                public void findBestroute(ArrayList<Integer> cityIds, CityManager cityManager, Iterable<ArrayList<Integer>> allroutes) {

                    int beginCity = cityIds.get(0);
                    ArrayList<Integer> otherCities = new ArrayList<>(cityIds.subList(1, cityIds.size()));

                    ArrayList<Integer> bestRoute = null;
                    int bestDistance = Integer.MAX_VALUE;

                    ArrayList<ArrayList<Integer>> allRoutes = generate_permutations(otherCities);

                    for (ArrayList<Integer> route : allroutes) {
                        int totaldis = 0;
                        int presentCity = beginCity;

                        for (int nextCity : route) {
                            totaldis += distanceManager.getDistance(presentCity, nextCity);
                        }
                        totaldis += distanceManager.getDistance(presentCity, beginCity);

                        if (totaldis < bestDistance) {
                            bestDistance = totaldis;
                            bestRoute = new ArrayList<>(route);
                        }
                    }
                    System.out.println("\n===LEAST-COST ROUTE");
                    System.out.print(cityManager.getCities().get(beginCity));

                    for (int city : bestRoute) {
                        System.out.print("->" + cityManager.getCities().get(city));
                    }
                    System.out.println("->" + cityManager.getCities().get(beginCity));
                    System.out.println("Total distance: " + bestDistance + "km");
                }

                private ArrayList<ArrayList<Integer>> generate_permutations(ArrayList<Integer> cities) {
                    ArrayList<ArrayList<Integer>> result = new ArrayList<>();
                    permutate(cities, 0, result);
                    return result;
                }

                private void permutate(ArrayList<Integer> cities, int begin, ArrayList<ArrayList<Integer>> result) {
                    if (begin == cities.size() - 1) {
                        result.add(new ArrayList<>(cities));
                        return;
                    }
                    for (int b = begin; b < cities.size(); b++) {
                        Collections.swap(cities, b, begin);
                        permutate(cities, begin + 1, result);
                        Collections.swap(cities, b, begin);
                    }
                }
            }

            public class FileHandler {

                private CityManager cityManager;
                private DistanceManager distanceManager;
                private DeliveryManager deliveryManager;

                public FileHandler(CityManager cm, DistanceManager dm, DeliveryManager del) {
                    this.cityManager = cm;
                    this.distanceManager = dm;
                    this.deliveryManager = del;
                }

                public void loadData() {
                    loadCitiesAndDistances();
                    loadDeliveries();
                }

                public void saveData() {
                    saveCitiesAndDistances();
                    saveDeliveries();
                }

                private void loadCitiesAndDistances() {
                    try (Scanner file = new Scanner(new File("routes.txt"))) {
                        ArrayList<String> cities = cityManager.getCities();

                        cities.clear();

                        int cityCount = Integer.parseInt(file.nextLine().trim());

                        for (int i = 0; i < cityCount; i++) {
                            if (file.hasNextLine()) {
                                cities.add(file.nextLine().trim());
                            }
                        }

                        for (int i = 0; i < cityCount; i++) {
                            if (file.hasNextLine()) {
                                String[] distRow = file.nextLine().trim().split(",");
                                for (int j = 0; j < cityCount; j++) {
                                    int distance = Integer.parseInt(distRow[j]);
                                    distanceManager.setDistance(i, j, distance);
                                }
                            }
                        }

                        System.out.println("Cities and distances loaded successfully.");

                    } catch (FileNotFoundException e) {
                        System.out.println("routes.txt not found. Starting with empty data.");
                    } catch (Exception e) {
                        System.out.println("Error loading cities/distances: " + e.getMessage());
                    }
                }

                private void saveCitiesAndDistances() {
                    try (PrintWriter writer = new PrintWriter("routes.txt")) {
                        ArrayList<String> cities = cityManager.getCities();
                        int cityCount = cities.size();

                        writer.println(cityCount);

                        for (String city : cities) {
                            writer.println(city);
                        }

                        for (int i = 0; i < cityCount; i++) {
                            for (int j = 0; j < cityCount; j++) {
                                writer.print(distanceManager.getDistance(i, j));
                                if (j < cityCount - 1) {
                                    writer.print(",");
                                }
                            }
                            writer.println();
                        }

                        System.out.println("Cities and distances saved successfully.");

                    } catch (Exception e) {
                        System.out.println("Error saving cities/distances: " + e.getMessage());
                    }
                }

                private void loadDeliveries() {
                    try (Scanner file = new Scanner(new File("deliveries.txt"))) {

                        deliveryManager.getDeliveries().clear();

                        while (file.hasNextLine()) {
                            String line = file.nextLine();
                            String[] parts = line.split(",");

                            int city1 = Integer.parseInt(parts[0]);
                            int city2 = Integer.parseInt(parts[1]);
                            double weight = Double.parseDouble(parts[2]);
                            int vehicleId = Integer.parseInt(parts[3]);
                            double distance = Double.parseDouble(parts[4]);
                            double totalCost = Double.parseDouble(parts[5]);
                            double profit = Double.parseDouble(parts[6]);
                            double charge = Double.parseDouble(parts[7]);
                            double time = Double.parseDouble(parts[8]);

                            deliveryManager.getDeliveries().add(
                                    new Delivery(city1, city2, weight, vehicleId, (int) distance, totalCost, profit, charge, time));

                        }

                        System.out.println("Deliveries successfully loaded.");

                    } catch (FileNotFoundException e) {
                        System.out.println("deliveries.txt not found. No deliveries loaded.");
                    } catch (Exception e) {
                        System.out.println("Error loading deliveries: " + e.getMessage());
                    }
                }

                private void saveDeliveries() {
                    try (PrintWriter writer = new PrintWriter("deliveries.txt")) {
                        for (Delivery d : deliveryManager.getDeliveries()) {
                            writer.printf("%d,%d,%.2f,%d,%.2f,%.2f,%.2f,%.2f,%.2f\n",
                                    d.getSci(), d.getSci(), d.getWeight(), d.getVehicleid(),
                                    d.getDistance(), d.getTotal_cost(), d.getProfit(), d.getCharge(), d.getDeliverytime());
                        }
                        System.out.println("Deliveries successfully saved..");
                    } catch (Exception e) {
                        System.out.println("Error saving deliveries: " + e.getMessage());
                    }
                }
            }
        }
    }
