package Exercicios_java;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.io.Serializable;
import java.lang.reflect.Executable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import java.util.UUID;

enum Category {
    PARTY,
    SHOW,
    SPORTSEVENTS,
    PUB,
    BALLAD,
};

// class Category{
//     String party;
//     String show;
//     String sportsEvents;
//     String pub;
//     String ballad;
// }

class Event implements Serializable {
    String id;
    String name;
    String address;
    int cep;
    String country;
    String state;
    String city;
    Date startTime;
    Date endTime;
    String description;
    String category;
    // Category category;
    String[] participants;
    int maxNumberOfParticipants;
}

interface EventLocal {
    void load();

    Event loadNearEvents(Event eventData);

    void show(String ID, ArrayList<Object> eventsData);

    void create();

    void update(ArrayList<Object> eventsData, Event eventData, String option);
}

class User implements Serializable {
    String id;
    String name;
    String mail;
    String country;
    String state;
    String city;
    String[] events;
}

interface UserLocal {
    void show();

    void create();

    void update(String eventID, String option);
}

public class App {
    public static void main(String[] args) {
        System.out.println("Welcome to your app that will help you find the best and near parties near you!");
        init();
    }

    public static void init() {

        Scanner scanner = new Scanner(System.in);

        int option = 0;

        System.out.println("Please enter:");
        System.out.println("[0]: To create a account");
        System.out.println("[1]: To register a new event");
        System.out.println("[2]: To see all the events");
        System.out.println("[3]: See your user perfil");
        System.out.println("[4]: Close application");

        try {
            option = scanner.nextInt();
            if (option > 5)
                throw new InputMismatchException("Invalid option entered " + option);

        } catch (InputMismatchException e) {
            System.out.println("Invalid option entered, please enter a valid option");
            init();

        }

        switch (option) {
            case 0:
                user().create();
                break;

            case 1:
                event().create();
                break;

            case 2:
                event().load();
                break;

            case 3:
                user().show();
                break;

            case 4:
                System.exit(0);
                break;
        }
    }

    public static EventLocal event() {
        class general {
            int print(Event eventData) {
                System.out.println("ID: " + eventData.id);
                System.out.println("> Name: " + eventData.name);
                System.out.println("> Address: " + eventData.address);
                System.out.println("> CEP: " + eventData.cep);
                System.out.println("> Country: " + eventData.country);
                System.out.println("> State: " + eventData.state);
                System.out.println("> Start time: " + eventData.startTime);
                System.out.println("> End time: " + eventData.endTime);
                // System.out.println("> Time: " + eventData.time);
                System.out.println("> Description: " + eventData.description);
                System.out.println("> Type: " + eventData.category);

                int participants = 0;
                for (int x = 0; x < eventData.participants.length; x++) {
                    if (eventData.participants[x] != null) {
                        participants++;
                        continue;
                    }
                }

                System.out.println("> Registrations: " + participants + "/" + eventData.maxNumberOfParticipants);

                return participants;
            }

            void showMore(ArrayList<Object> data) {
                Scanner scanner = new Scanner(System.in);

                System.out.println("Enter R if you want to return, or the ID of the event to see more of it:");

                String choice = scanner.nextLine();

                if (choice.equals("R")) {
                    init();
                }

                if (choice.length() != 36) {
                    System.out.println("Please type in correctly.");
                    showMore(data);
                } else {
                    event().show(choice, data);
                }
            }

            void unsubscribe(ArrayList<Object> eventsData, Event eventData) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Do you wish to cancel your registration in this this event? (y/n)");
                System.out.println("Enter R to return.");
                String choice = scanner.nextLine();

                if (choice.equals("R")) {
                    event().load();
                }

                if (choice.equals("y") || choice.equals("yes")) {
                    System.out.println(">> You unsubscribed of this event");
                    event().update(eventsData, eventData, "del");
                    event().load();

                } else if (choice.equals("n") || choice.equals("no")) {
                    System.out.println(">> Returning");
                    event().load();
                } else {
                    System.out.println("Please type in correctly.");
                    unsubscribe(eventsData, eventData);
                }

            }

            void registration(int participants, Event eventData, ArrayList<Object> eventsData) {
                if (participants < eventData.maxNumberOfParticipants) {
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Do you wish to register yourself in this event? (y/n)");
                    System.out.println("Enter R to return.");
                    String choice = scanner.nextLine();

                    if (choice.equals("R")) {
                        event().load();
                    }

                    if (choice.equals("y") || choice.equals("yes")) {
                        System.out.println(">> Registered!");
                        event().update(eventsData, eventData, "add");
                        event().load();

                    } else if (choice.equals("n") || choice.equals("no")) {
                        System.out.println(">> Returning");
                        event().load();
                    } else {
                        System.out.println("Please type in correctly.");
                        registration(participants, eventData, eventsData);
                    }
                }
            }

            void update(String option, Event eventData, String userID) {
                for (int i = 0; i < eventData.participants.length; i++) {
                    String filter = (option == "add" ? null : userID);
                    String data = (option == "add" ? userID : null);

                    if (eventData.participants[i] == filter || eventData.participants[i].equals(filter)) {
                        eventData.participants[i] = data;
                        break;
                    }
                }
            }
        }

        EventLocal local = new EventLocal() {
            public void load() {
                System.out.println("The events that are hapenning or already happened:");

                ArrayList<Object> eventsData = FileToObject("data\\events.data");

                System.out.println("================================");
                System.out.println(">> Events near you, that are happening now: ");

                for (int i = 0; i < eventsData.size(); i++) {
                    Event eventData = (Event) eventsData.get(i);
                    if (loadNearEvents(eventData) != null) {
                        System.out.println("--------------------------------");
                        eventsData.remove(i);
                        new general().print(loadNearEvents(eventData));
                    }
                }

                System.out.println("================================");

                System.out.println(">> Other events: ");
                for (int i = 0; i < eventsData.size(); i++) {
                    Event eventData = (Event) eventsData.get(i);

                    System.out.println("--------------------------------");

                    new general().print(eventData);
                }


                new general().showMore(eventsData);

            };

            public Event loadNearEvents(Event eventData) {
                ArrayList<Object> data = FileToObject("data\\user.data");

                User userData = (User) data.get(0);
                SimpleDateFormat format = new SimpleDateFormat("dd-mm-yyyy h:mm a");

                Date parsedDate = null;

                try {
                    LocalDateTime timeNow = LocalDateTime.now();

                    int day = timeNow.getDayOfMonth();
                    int month = timeNow.getMonthValue();
                    int year = timeNow.getYear();
                    int hour = timeNow.getHour();
                    int minute = timeNow.getMinute();
                    String a = "AM";

                    if (hour > 12) {
                        a = "PM";
                        hour = hour - 12;
                    }

                    parsedDate = format.parse(Integer.toString(day) + '-' + month + '-' + year + ' ' + hour + ':' + minute + ' ' + a);

                } catch (ParseException e) {
                    e.printStackTrace();
                }

                boolean country = eventData.country.equals(userData.country);
                boolean state = eventData.state.equals(userData.state);
                boolean city = eventData.city.equals(userData.city);
                boolean startTime = eventData.startTime.before(parsedDate);
                boolean endTime = eventData.endTime.after(parsedDate);
   
                if (country && state && city && startTime && endTime) {
                    return eventData;
                }


                return null;

            }

            public void show(String ID, ArrayList<Object> eventsData) {
                ArrayList<Object> data = FileToObject("data\\user.data");
                User userData = (User) data.get(0);

                for (int i = 0; i < eventsData.size(); i++) {
                    Event eventData = (Event) eventsData.get(i);
                    int participants = 0;
                    if (eventData.id.equals(ID)) {
                        System.out.println("");
                        System.out.println("");
                        System.out.println("--------------------------------");

                        participants = new general().print(eventData);

                        for (int x = 0; x < eventData.participants.length; x++) {
                            if (eventData.participants[x] != null && eventData.participants[x].equals(userData.id)) {
                                new general().unsubscribe(eventsData, eventData);
                                break;
                            } else {
                                new general().registration(participants, eventData, eventsData);
                            }
                        }

                    }

                }

            }

            public void create() {
                Event event = new Event();
                Scanner scanner = new Scanner(System.in);

                try {
                    System.out.println("> Please enter the event name:");
                    event.name = scanner.nextLine();

                    System.out.println("> Please enter address of the event:");
                    event.address = scanner.nextLine();

                    System.out.println("> Please enter cep of the event:");

                    try {
                        event.cep = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }

                    System.out.println("> Please enter country of the event:");
                    event.country = scanner.nextLine();

                    System.out.println("> Please enter state of the event:");
                    event.state = scanner.nextLine();

                    System.out.println("> Please enter city of the event:");
                    event.city = scanner.nextLine();

                    SimpleDateFormat format = new SimpleDateFormat("dd-mm-yyyy h:mm a");

                    System.out.println("> Please enter the start time of the event: (dd-mm-yyyy 'at' h:mm a)");
                    String startTime = scanner.nextLine();
                    Date startDateParse = format.parse(startTime);
                    event.startTime = startDateParse;

                    System.out.println("> Please enter the end time of the event: (dd-mm-yyyy 'at' h:mm a)");
                    String endTime = scanner.nextLine();
                    Date endDateParse = format.parse(endTime);
                    event.endTime = endDateParse;

                    System.out.println("> Please enter a description of the event:");
                    event.description = scanner.nextLine();

                    System.out.println("> Please enter the category of the event: (party, show, sportsEvents, pub, ballad)");
                    String category = scanner.nextLine();

                    for(int i = 0; i < Category.values().length; i++) {
                        if(Category.values()[i].toString().toLowerCase().equals(category) ) {
                            event.category = category;
                            break;
                        } else if(i == Category.values().length) {
                            throw new Exception("Please enter a valid category");
                        }
                    }

                    System.out.println("> Please enter the maximun number of participants:");
                    event.maxNumberOfParticipants = scanner.nextInt();

                    event.id = UUID.randomUUID().toString();

                    event.participants = new String[event.maxNumberOfParticipants];

                    System.out.println(">> Events registered");

                    writeToFile("data\\events.data", event);

                    init();

                } catch (InputMismatchException | ParseException e) {
                    System.out.println("\tInvalid input entered. Please enter the specified input");
                    System.out.println(">> Please, start the registration again.");
                } catch (Exception e) {
                    System.out.println(e);
                    System.out.println(">> Please, start the registration again.");
                }

                init();
                // scanner.close();

                

            };

            public void update(ArrayList<Object> eventsData, Event eventData, String option) {
                ArrayList<Object> data = FileToObject("data\\user.data");
                User userData = (User) data.get(0);

                new general().update(option, eventData, userData.id);

                String path = "data\\events.data";

                try {
                    new FileOutputStream(path).close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                for (int i = 0; i < eventsData.size(); i++) {
                    Event event = (Event) eventsData.get(i);
                    if (!event.equals(eventData)) {
                        writeToFile(path, event);
                        continue;
                    }

                    user().update(event.id, option);
                    writeToFile(path, eventData);
                }
            }
        };

        return local;
    }

    public static UserLocal user() {
        class general {
            void update(User userData, String eventID, String option) {
                for (int i = 0; i < userData.events.length; i++) {
                    String filter = (option == "add" ? null : eventID);
                    String data = (option == "add" ? eventID : null);

                    if (userData.events[i] == filter || userData.events[i].equals(filter)) {
                        userData.events[i] = data;
                        break;
                    }
                }
            }
        }

        UserLocal local = new UserLocal() {
            public void show() {
                ArrayList<Object> data = FileToObject("data\\user.data");

                User userData = (User) data.get(0);

                System.out.println("--------------------------------");
                System.out.println("Name: " + userData.name);
                System.out.println("Mail: " + userData.mail);
                System.out.println("Country: " + userData.country);
                System.out.println("State: " + userData.state);
                System.out.println("City: " + userData.city);
                System.out.println("Your events: ");

                System.out.println(toStringArrayNonNulls(userData.events).equals("[]") ? "You don't have any events"
                        : toStringArrayNonNulls(userData.events));

                System.out.println("--------------------------------");

                init();
            };

            public void create() {
                User user = new User();

                Scanner scanner = new Scanner(System.in);

                try {
                    System.out.println("> Please enter your name:");
                    user.name = scanner.nextLine();

                    System.out.println("> Please enter your mail:");
                    user.mail = scanner.nextLine();

                    System.out.println("> Please enter the country you live in:");
                    user.country = scanner.nextLine();

                    System.out.println("> Please enter the state you live in:");
                    user.state = scanner.nextLine();

                    System.out.println("> Please enter the city you live in:");
                    user.city = scanner.nextLine();

                } catch (InputMismatchException e) {
                    System.out.println("\tInvalid input entered. Please enter the specified input");
                }


                user.id = UUID.randomUUID().toString();

                user.events = new String[25];

                System.out.println(">> Account created");

                writeToFile("data\\user.data", user);

                init();

            };

            public void update(String eventID, String option) {
                String path = "data\\user.data";

                ArrayList<Object> data = FileToObject(path);
                User userData = (User) data.get(0);

                new general().update(userData, eventID, option);

                try {
                    new FileOutputStream(path).close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                writeToFile(path, userData);
            }
        };

        return local;
    }

    public static void writeToFile(String P, Object E) {
        try {
            FileOutputStream path = new FileOutputStream(P, P.contains("events") && true);

            ObjectOutputStream objectToFile = new ObjectOutputStream(path);

            objectToFile.writeObject(E);

            objectToFile.flush();

            // objectToFile.close();

        } catch (IOException ex) {
            ex.getStackTrace();
        }

    }

    public static ArrayList<Object> FileToObject(String P) {
        ArrayList<Object> result = new ArrayList<>();

        try {
            FileInputStream path = new FileInputStream(P);

            while (path.available() > 0) {
                ObjectInputStream ois = new ObjectInputStream(path);
                Object fileToObject = ois.readObject();

                result.add(fileToObject);
            }

            // path.close();

        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        return result;
    }

    public static <T> String toStringArrayNonNulls(T[] data) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for (int i = 0; i < data.length; i++) {
            if (data[i] != null) {
                sb.append(data[i].toString());
                sb.append(",");
                break;
            }
        }

        sb.append("]");

        return sb.toString();
    }

}