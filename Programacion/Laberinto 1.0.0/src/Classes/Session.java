package Classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Session {
    private User user;
    private boolean logged;
    public static final String ERROR_MESSAGE = "An error occurred.";
    public static final String COMING_SOON = "Proximamente";
    public static final String USERS_FILE_PATH = "assets/users.txt";

    public Session(){
        this.logged = false;
        this.user = new User();
    }


    public static void unloggedMenu(Session sessionActually){
        int option;
        boolean repeat = true;
        while (repeat) {
            System.out.print(Config.UNLOGGED_MENU);
            option = Interface.getInt();
            if (option == 1) {
                sessionActually.login();
            } else if (option == 2) {
                sessionActually.signup();
            } else if (option == 0) {
                System.out.println(Config.GOODBYE);
                repeat = false;
            } else {
                System.out.println("El valor introducido no es correcto");
            }
        }
    }

    public boolean login(){
        String username = Interface.getString("Introduzca su nombre de usuario: ");
        String password = Interface.getString("Introduzca su contraseña: ");
        if(validateUser(username, password)){
            this.logged = true;
            System.out.println("Inicio de sesion realizado con exito.");
            while (logged){
                loggedMenu(username, password);
            }

        } else {
            System.out.println("Usuario o contraseña incorrectos.");
        }
        return logged;
    }

    public void signup(){
        String line;
        String role = "user";
        System.out.println("A continuacion vamos a empezar con el registro");
        System.out.println("----------------------------------------------");
        String username = Interface.getString("Introduce tu nombre de usuario: ");
        if (userExists(username)){
            System.out.println("El usuario ya existe");
        } else {
            String password = Interface.getString("Introduce la contraseña: ");
            String name = Interface.getString("Introduce tu nombre completo: ");
            String nif = Interface.getString("Introduce la dni: ");
            String email = Interface.getString("Introduce la email: ");
            String address = Interface.getString("Introduce la direccion: ");
            String birthdate = Interface.getString("Introduce la fecha de cumpleaños: ");

            line = username+"#"+password+"#"+name+"#"+nif+"#"+email+"#"+address+"#"+birthdate+"#"+role;

            try {
                FileWriter myWriter = new FileWriter("assets/users.txt", true);
                myWriter.write(line);
                myWriter.write(System.lineSeparator());
                myWriter.close();
            } catch (IOException e) {
                System.out.println(ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }

    public void showUser(){
        System.out.println("-----Informacion del Usuario-----");
        String info = "Nombre de Usuario: "+this.user.getUsername()+"\n"+
                "Nombre: "+this.user.getName()+"\n"+
                "Dni: "+this.user.getNif()+"\n"+
                "Email: "+this.user.getEmail()+"\n"+
                "Direccion: "+this.user.getAddress()+"\n"+
                "Fecha de cumpleaños: "+this.user.getBirthdate()+"\n"+
                "Rol: "+this.user.getRole()+"\n";
        System.out.println(info);
    }

    public void logout(){
        this.logged = false;
        System.out.println("La sesion se cerro con exito.");

    }

    public static boolean userExists(String username){
        try {
            File myObjMod = new File("assets/users.txt");
            Scanner myReader = new Scanner(myObjMod);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] credenciales = data.split("#");
                if (credenciales[0].equalsIgnoreCase(username)){
                    return true;
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println(ERROR_MESSAGE);
            e.printStackTrace();
        }
        return false;
    }

    public static boolean validateUser(String username, String password){

        try {
            File myObjMod = new File("assets/users.txt");
            Scanner myReader = new Scanner(myObjMod);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] credenciales = data.split("#");
                if (credenciales[0].equalsIgnoreCase(username)&&(credenciales[1].equals(password))){
                    return true;
                };

            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println(ERROR_MESSAGE);
            e.printStackTrace();
        }
        return false;
    }

    public User userActualy(String username, String password) {
        try {
        File myObj = new File("assets/users.txt");
        Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] line = data.split("#");
                if (username.equalsIgnoreCase(line[0]) && password.equals(line[1])) {
                    this.user = new User(line[0],line[2],line[3],line[4],line[5],line[6],line[7]);
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println(ERROR_MESSAGE);
            e.printStackTrace();
        }
        return user;
    }

    public void loggedMenu(String username, String password){
        userActualy(username, password);
        boolean repeat = true;
        while (repeat) {
            System.out.print(Config.LOGGED_MENU);
            int option = Interface.getInt();

            if (option == 1) {

                Maze.loadMaze();

            } else if (option == 2) {

                Maze.showMapEmpty();

            } else if (option == 3) {

                Maze.setEntranceExit();

            } else if (option == 4) {

                Maze.searchPaths();

            } else if (option == 5) {

                showUser();
                Interface.toContinue();

            } else if (option == 6) {

                logout();
                repeat = false;

            } else if (option == 0) {
                System.out.println(Config.GOODBYE);
                repeat = false;
                System.exit(0);

            } else {
                System.out.println("El valor introducido no es correcto");
            }
        }
    }

    public boolean isLogged() {
        return logged;
    }
}
