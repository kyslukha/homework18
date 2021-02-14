package ua.com.alevel.user;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;


public class Console implements Serializable {
    public static void main(String[] args) throws IOException {
        UserMethods userMethods = new UserMethods();

        String userCommand = userMethods.enter();

        switch (userCommand) {
            case "create":
                String userInfo = userMethods.read();
                userMethods.serialize(userMethods.create(userInfo),
                        new File("src//main//resources//" + userMethods.create(userInfo).getName().replace(" ", "_") + ".dat"));
                break;
            case "show":
                String answerName = userMethods.askName();
                try {
                    System.out.println(userMethods.deserialization(userMethods.show(answerName)));
                } catch (FileNotFoundException e) {
                    System.out.println("User " + answerName + " is not found");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            default:
                System.out.println("Command is wrong!");
        }

    }

}
