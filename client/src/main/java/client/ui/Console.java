package client.ui;

import client.controller.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Scanner;
import java.util.stream.StreamSupport;

@Component
public class Console {
    public static final Logger logger = LoggerFactory.getLogger(Console.class);
    private final DriverControllerClient driverController;
    private final TrainControllerClient trainController;
    private final DriverTrainControllerClient driverTrainController;
    private final RouteControllerClient routeController;
    private final JoinControllerClient joinController;

    public Console(DriverControllerClient driverController, TrainControllerClient trainController, DriverTrainControllerClient driverTrainController, RouteControllerClient routeController, JoinControllerClient joinController) {
        this.driverController = driverController;
        this.trainController = trainController;
        this.driverTrainController = driverTrainController;
        this.routeController = routeController;
        this.joinController = joinController;
    }

    private void printFilteredTrainsByCityRoute() {
        logger.trace("FilterTrainsByRouteSource started.");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Source: ");
        String source = scanner.next();
        joinController.filterTrainsByRouteSource(source).whenComplete((result, exception) -> {
            if (exception == null) {
                result.forEach(System.out::println);
            } else {
                System.out.println(exception.getMessage());
            }
        });
        logger.trace("FilterTrainsByRouteSource finished.");
    }

    private void printDrivers() {
        logger.trace("FindAll driver started.");
        driverController.findAll().whenComplete((result, exception) -> {
            if (exception == null) {
                StreamSupport.stream(result.spliterator(), false).forEach(System.out::println);
            }
            else {
                System.out.println(exception.getMessage());
            }
        });
        logger.trace("FindAll driver finished.");
    }

    private void addDriver() {
        logger.trace("Save driver started.");
        Scanner scanner = new Scanner(System.in);
        System.out.println("DriverName: ");
        String driverName = scanner.next();
        System.out.println("DriverCNP: ");
        String driverCNP = scanner.next();
        driverController.save(driverName, driverCNP).whenComplete((result, exception) -> {
            if (exception == null) {
                System.out.println(result);
            } else {
                System.out.println(exception.getMessage());
            }
        });
        logger.trace("Save driver finished.");
    }

    private void deleteDriver() {
        logger.trace("Delete driver started.");
        Scanner scanner = new Scanner(System.in);
        System.out.println("DriverID: ");
        Long driverID = scanner.nextLong();
        driverController.delete(driverID).whenComplete((result, exception) -> {
            if (exception == null) {
                System.out.println(result);
            } else {
                System.out.println(exception.getMessage());
            }
        });
        logger.trace("Delete driver finished.");
    }

    private void updateDriver() {
        logger.trace("Update driver started.");
        Scanner scanner = new Scanner(System.in);
        System.out.println("DriverID: ");
        Long driverID = scanner.nextLong();
        scanner.nextLine();
        System.out.println("DriverName: ");
        String driverName = scanner.next();
        System.out.println("DriverCNP: ");
        String driverCNP = scanner.next();
        driverController.update(driverID, driverName, driverCNP).whenComplete((result, exception) -> {
            if (exception == null) {
                System.out.println(result);
            } else {
                System.out.println(exception.getMessage());
            }
        });
        logger.trace("Update driver finished.");
    }

    private void printTrains() {
        logger.trace("FindAll train started.");
        trainController.findAll().whenComplete((result, exception) -> {
            if (exception == null) {
                StreamSupport.stream(result.spliterator(), false).forEach(System.out::println);
            }
            else {
                System.out.println(exception.getMessage());
            }
        });
        logger.trace("FindAll train finished.");
    }

    private void addTrain() {
        logger.trace("Save train started.");
        System.out.println("The list of routes: ");
        this.printRoutes();
        System.out.println("\n");
        Scanner scanner = new Scanner(System.in);
        System.out.println("RouteID: (from the list of routes)");
        Long routeID = scanner.nextLong();
        scanner.nextLine();
        System.out.println("DepartureTime: ");
        String departureTime = scanner.next();
        System.out.println("ArrivalTime: ");
        String arrivalTime = scanner.next();
        trainController.save(routeID, departureTime, arrivalTime).whenComplete((result, exception) -> {
            if (exception == null) {
                System.out.println(result);
            } else {
                System.out.println(exception.getMessage());
            }
        });
        logger.trace("Save train finished.");
    }

    private void deleteTrain() {
        logger.trace("Delete train started.");
        Scanner scanner = new Scanner(System.in);
        System.out.println("TrainID: ");
        Long trainID = scanner.nextLong();
        trainController.delete(trainID).whenComplete((result, exception) -> {
            if (exception == null) {
                System.out.println(result);
            } else {
                System.out.println(exception.getMessage());
            }
        });
        logger.trace("Delete train finished.");
    }

    private void updateTrain() {
        logger.trace("Update train started.");
        Scanner scanner = new Scanner(System.in);
        System.out.println("TrainID: ");
        Long trainID = scanner.nextLong();
        scanner.nextLine();
        System.out.println("DepartureTime: ");
        String departureTime = scanner.next();
        System.out.println("ArrivalTime: ");
        String arrivalTime = scanner.next();
        trainController.update(trainID, departureTime, arrivalTime).whenComplete((result, exception) -> {
            if (exception == null) {
                System.out.println(result);
            } else {
                System.out.println(exception.getMessage());
            }
        });
        logger.trace("Update train finished.");
    }

    private void printDriverTrains() {
        logger.trace("FindAll driverTrain started.");
        driverTrainController.findAll().whenComplete((result, exception) -> {
            if (exception == null) {
                StreamSupport.stream(result.spliterator(), false).forEach(System.out::println);
            }
            else {
                System.out.println(exception.getMessage());
            }
        });
        logger.trace("FindAll driverTrain finished.");
    }

    private void addDriverTrain() {
        logger.trace("Save driverTrain started.");
        System.out.println("The list of drivers: ");
        this.printDrivers();
        System.out.println("\n");
        System.out.println("The list of trains: ");
        this.printTrains();
        System.out.println("\n");
        Scanner scanner = new Scanner(System.in);
        System.out.println("DriverID: (from the list of drivers)");
        Long driverID = scanner.nextLong();
        System.out.println("TrainID: (from the list of trains)");
        Long trainID = scanner.nextLong();
        scanner.nextLine();
        System.out.println("HowManyDays: ");
        String howManyDays = scanner.next();
        driverTrainController.save(driverID, trainID, howManyDays).whenComplete((result, exception) -> {
            if (exception == null) {
                System.out.println(result);
            } else {
                System.out.println(exception.getMessage());
            }
        });
        logger.trace("Save driverTrain finished.");
    }

    private void deleteDriverTrain() {
        logger.trace("Delete driverTrain started.");
        Scanner scanner = new Scanner(System.in);
        System.out.println("DriverTrainID: ");
        Long driverTrainID = scanner.nextLong();
        scanner.nextLine();
        driverTrainController.delete(driverTrainID).whenComplete((result, exception) -> {
            if (exception == null) {
                System.out.println(result);
            } else {
                System.out.println(exception.getMessage());
            }
        });
        logger.trace("Delete driverTrain finished.");
    }

    private void updateDriverTrain() {
        logger.trace("Update driverTrain started.");
        Scanner scanner = new Scanner(System.in);
        System.out.println("DriverTrain ID: ");
        Long driverTrainID = scanner.nextLong();
        scanner.nextLine();
        System.out.println("HowManyDays: ");
        String howManyDays = scanner.next();
        driverTrainController.update(driverTrainID, howManyDays).whenComplete((result, exception) -> {
            if (exception == null) {
                System.out.println(result);
            } else {
                System.out.println(exception.getMessage());
            }
        });
        logger.trace("Update driverTrain finished.");
    }

    private void printRoutes() {
        logger.trace("FindAll route started.");
        routeController.findAll().whenComplete((result, exception) -> {
            if (exception == null) {
                StreamSupport.stream(result.spliterator(), false).forEach(System.out::println);
            }
            else {
                System.out.println(exception.getMessage());
            }
        });
        logger.trace("FindAll route finished.");
    }

    private void addRoute() {
        logger.trace("Save route started.");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Source: ");
        String source = scanner.next();
        System.out.println("Destination: ");
        String destination = scanner.next();
        routeController.save(source, destination).whenComplete((result, exception) -> {
            if (exception == null) {
                System.out.println(result);
            } else {
                System.out.println(exception.getMessage());
            }
        });
        logger.trace("Save route finished.");
    }

    private void deleteRoute() {
        logger.trace("Delete route started.");
        Scanner scanner = new Scanner(System.in);
        System.out.println("RouteID: ");
        Long routeID = scanner.nextLong();
        routeController.delete(routeID).whenComplete((result, exception) -> {
            if (exception == null) {
                System.out.println(result);
            } else {
                System.out.println(exception.getMessage());
            }
        });
        logger.trace("Delete route finished.");
    }

    private void updateRoute() {
        logger.trace("Update route started.");
        Scanner scanner = new Scanner(System.in);
        System.out.println("RouteID: ");
        Long routeID = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Source: ");
        String source = scanner.next();
        System.out.println("Destination: ");
        String destination = scanner.next();
        routeController.update(routeID, source, destination).whenComplete((result, exception) -> {
            if (exception == null) {
                System.out.println(result);
            } else {
                System.out.println(exception.getMessage());
            }
        });
        logger.trace("Update route finished.");
    }

    private static void printMenu() {
        System.out.println("1. Show drivers;");
        System.out.println("2. Add driver;");
        System.out.println("3. Delete driver;");
        System.out.println("4. Update driver;");
        System.out.println();
        System.out.println("5. Show trains;");
        System.out.println("6. Add train;");
        System.out.println("7. Delete train;");
        System.out.println("8. Update train;");
        System.out.println();
        System.out.println("9. Show driverTrains;");
        System.out.println("10. Add driverTrain;");
        System.out.println("11. Delete driverTrain;");
        System.out.println("12. Update driverTrain;");
        System.out.println();
        System.out.println("13. Show routes;");
        System.out.println("14. Add route;");
        System.out.println("15. Delete route;");
        System.out.println("16. Update route;");
        System.out.println();
        System.out.println("17. Show trains filtered by the source of their route;");
        System.out.println();
        System.out.println("0. Exit.");
        System.out.println("\n");
    }

    public void runConsole() {
        try {
            while (true) {
                printMenu();
                Scanner scanner = new Scanner(System.in);
                Integer option = scanner.nextInt();
                switch (option) {
                    case 0:
                        System.out.println("Client shutting down...");
                        return;
                    case 1:
                        printDrivers();
                        break;
                    case 2:
                        addDriver();
                        break;
                    case 3:
                        deleteDriver();
                        break;
                    case 4:
                        updateDriver();
                        break;
                    case 5:
                        printTrains();
                        break;
                    case 6:
                        addTrain();
                        break;
                    case 7:
                        deleteTrain();
                        break;
                    case 8:
                        updateTrain();
                        break;
                    case 9:
                        printDriverTrains();
                        break;
                    case 10:
                        addDriverTrain();
                        break;
                    case 11:
                        deleteDriverTrain();
                        break;
                    case 12:
                        updateDriverTrain();
                        break;
                    case 13:
                        printRoutes();
                        break;
                    case 14:
                        addRoute();
                        break;
                    case 15:
                        deleteRoute();
                        break;
                    case 16:
                        updateRoute();
                        break;
                    case 17:
                        printFilteredTrainsByCityRoute();
                        break;
                    default:
                        System.out.println("Invalid command!");
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            runConsole();
        }
    }
}
