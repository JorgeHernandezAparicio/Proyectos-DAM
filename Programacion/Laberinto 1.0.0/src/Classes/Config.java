package Classes;

public class Config {
    public static final String Version = "0.2.0";
    public static final String FILE_PATH = "./assets/users";
    public static final String MAZES_PATH = "./assets/mazes/";
    public static final String USERS_FILE = "users.txt";
    public static final String WELCOME =
            "========================================\n" +
                    "        BIENVENIDO AL PROGRAMA          \n" +
                    "========================================\n" +
                    "Este programa te permitirá trabajar con un laberinto y gestionar usuarios.\n" +
                    "Por favor, selecciona una opción del menú para continuar.\n";
    public static final String GOODBYE =
            "========================================\n" +
                    "         FIN DEL PROGRAMA               \n" +
                    "========================================\n" +
                    "Gracias por usar nuestro programa. ¡Hasta pronto!\n";
    public static final String UNLOGGED_MENU = "Menu:\n" +
            "1 Iniciar sesión\n" +
            "2 Registro\n" +
            "0 Salir\n" +
            "Selecciona una opcion: ";
    public static final String LOGGED_MENU = "-------------------------------------\n" +
            "Menu:\n" +
            "1 Cargar laberinto\n" +
            "2 Ver laberinto actual\n" +
            "3 Establecer casillas de entrada y salida\n" +
            "4 Buscar caminos\n" +
            "5 Ver usuario actual\n" +
            "6 Cerrar sesión\n" +
            "0 Salir\n" +
            "Selecciona una opcion: ";
    public static final String SHOW_MAZE_MENU = "-------------------------------------\n" +
            "Menu laberintos:\n" +
            "1 Laberinto1\n" +
            "2 Laberinto2\n" +
            "3 Laberinto3\n" +
            "0 Cancelar\n" +
            "Selecciona una opcion: ";

    public static final String SEARCH_PATHS_MENU = "-------------------------------------\n" +
            "Selecciona un camino:\n" +
            "1 El primer camino posible\n" +
            "2 El camino mas corto\n" +
            "0 Cancelar\n" +
            "Selecciona una opcion: ";

}
