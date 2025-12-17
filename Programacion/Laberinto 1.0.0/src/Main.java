import Classes.Config;
import Classes.Session;

public class Main {
    public static Session sessionActually = new Session();

    public static void main(String[] args){
        System.out.println(Config.WELCOME);
        Session.unloggedMenu(sessionActually);
    }

}