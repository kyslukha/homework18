package ua.com.alevel.user;


import java.io.*;
import java.util.Scanner;

public class UserMethods {


    public static String enter() {
        System.out.println("Enter the command (show or create)");
        String info = "";
        Scanner scanner = new Scanner(System.in);
        info = scanner.nextLine();
        return info;
    }

    public static String read() {
        System.out.println("Enter user's info");
        Scanner scanner = new Scanner(System.in);
        String info = scanner.nextLine();
        return info;
    }
    public static String askName() {
        System.out.println("Enter name of user");
        Scanner scanner = new Scanner(System.in);
        String info = scanner.nextLine();
        return info;
    }

    User create(String info) {

        String name = info.substring(0, info.indexOf(","));
        String mail = info.substring(info.indexOf(",") + 1, info.lastIndexOf(","));
        String phone = info.substring(info.lastIndexOf(",") + 1);

        if (!mail.contains("@")) {
            throw new RuntimeException("Mail is not correct");
        }
        if (!phone.contains("+")) {
            throw new RuntimeException("Phone is not correct");
        }
        User user = new User(name, mail, phone);
        System.out.println(user.toString());
        return user;
    }

    static void serialize(Object obj, File file) {
        try (ObjectOutputStream newUser = new ObjectOutputStream(new FileOutputStream(file))) {
            newUser.writeObject(obj);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Object deserialization(File file) throws IOException {
        Object oldUser = new Object();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            oldUser = ois.readObject();
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return oldUser;
    }


    public static File show(String name) throws IOException {
        File file = new File("src//main//resources//" + name + ".dat");
        return file;
    }

}