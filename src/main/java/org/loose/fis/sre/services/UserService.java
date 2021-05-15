package org.loose.fis.sre.services;

import static org.loose.fis.sre.services.FileSystemService.getPathToFile;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.sre.exceptions.UserNotFoundException;
import org.loose.fis.sre.model.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import static org.loose.fis.sre.services.FileSystemService.getPathToFile;

public class UserService {

    private static ObjectRepository<User> userRepository;
    private static Nitrite database;

    public static void initDatabase() {
        FileSystemService.initDirectory();
        database = Nitrite.builder()
                .filePath(getPathToFile("registration-example.db").toFile())
                .openOrCreate("test", "test");

        userRepository = database.getRepository(User.class);
    }

    public static void closeDatabase() {
        database.close();

    }

    public static void addUser(String username, String password, String role) throws UsernameAlreadyExistsException {
        checkUserDoesNotAlreadyExist(username);
        userRepository.insert(new User(username, encodePassword(username, password), role));
    }

    public static long getNoOfUsers() {
        long no = 0;
        for (User user : userRepository.find()) {
            no++;
        }

        return no;
    }

    public static User loginUser(String username, String password) throws UserNotFoundException {
        for (User user : userRepository.find()) {
            //System.out.println("username " + user.getUsername());
            if (Objects.equals(username, user.getUsername())
                && encodePassword(username, password).equals(user.getPassword()))  {
                System.out.println("user si pass gasite");
                return user;
            }
        }

        throw new UserNotFoundException();
    }

    private static void checkUserDoesNotAlreadyExist(String username) throws UsernameAlreadyExistsException {
        for (User user : userRepository.find()) {
            if (Objects.equals(username, user.getUsername()))
                throw new UsernameAlreadyExistsException(username);
        }
    }

    private static String encodePassword(String salt, String password) {
        MessageDigest md = getMessageDigest();
        md.update(salt.getBytes(StandardCharsets.UTF_8));

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

        // This is the way a password should be encoded when checking the credentials
        return new String(hashedPassword, StandardCharsets.UTF_8)
                .replace("\"", ""); //to be able to save in JSON format
    }

    private static MessageDigest getMessageDigest() {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-512 does not exist!");
        }
        return md;
    }

   /* public static User loggedUser(String username,String password) throws InvalidUsernameException, InvalidPasswordException {
        int ok1 = 0, ok2 = 0;
        for(User user : userRepository.find())
        {
            if (Objects.equals(username, user.getUsername()))
            {   ok1 = 1;
                if (encodePassword(username, password).equals(user.getPassword())) {
                    ok2 = 1;
                    return user;
                }
            }
        }
        if(ok1 == 0)
            throw new InvalidUsernameException("Invalid username");
        if(ok2 == 0)
            throw new InvalidPasswordException("Invalid password");
        return null;
    }*/


    /*public void handleLoginAction(ActionEvent event) throws Exception
    {
        try {
            UserService.loginUncompletedFields(username.getText(), password.getText());
            LoggedUser.setLoggedUser(UserService.loggedUser(username.getText(), password.getText()));
            if (UserService.getUserRole(username.getText()).equals("Junior Chef")) {
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("junior.fxml")));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setTitle("Junior Chef");
            } else {
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("head.fxml")));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setTitle("Head Chef");
            }
            stage.setScene(new Scene(root, 1280, 720));
            stage.show();
        } catch(UncompletedFieldsException | InvalidUsernameException | InvalidPasswordException e) {
            loginMessage.setText(e.getMessage());
        }*/
    }