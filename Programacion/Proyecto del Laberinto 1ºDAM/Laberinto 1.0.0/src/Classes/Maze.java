package Classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Maze {
    private static char[][] map;
    private static char[][] originalMap;
    private static String filename;
    private static boolean loaded = false;
    private static boolean entrances = false;
    private static int startI;
    private static int startJ;
    private static int endI;
    private static int endJ;
    private static ArrayList<Coordinate> path;
    private static boolean[][] visited;
    private static boolean pathFound = false;


    public static void loadMaze(){

        int reply = Interface.getInt(Config.SHOW_MAZE_MENU);
        filename = "laberinto"+reply;
        String path = Config.MAZES_PATH + "laberinto"+reply+".txt";

        if (reply >= 1 && reply <= 3){
            try {
                File file = new File(path);
                Scanner reader = new Scanner(file);
                ArrayList<String> lines = new ArrayList<>();

                while (reader.hasNextLine()){
                    lines.add(reader.nextLine());
                }
                reader.close();

                int rows = lines.size();
                int columns = lines.get(0).length();
                map = new char[rows][columns];
                originalMap = new char[rows][columns];

                for (int i = 0; i < rows; i++) {
                    map[i] = lines.get(i).toCharArray();
                    originalMap[i] = lines.get(i).toCharArray();
                }

                for (int i = 0; i < rows; i++){
                    map[i] = lines.get(i).toCharArray();
                }
                if (map.length != 0){
                    System.out.println("El laberinto se a cargado correctamente");
                    loaded = true;
                    entrances = false;
                    startI = 0;
                    startJ = 0;
                    endI = 0;
                    endJ = 0;
                }

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

        } else if (reply == 0) {
            System.out.println("Volviendo a atras");
        } else {
            System.out.println("El valor introducido no es correcto");
        }
    }

    public static void showMap(){
        if (loaded){
            for (int i = 0; i < map.length;i++){
                for (int j = 0; j < map[i].length;j++){
                    System.out.print(map[i][j]+"  ");
                }
                System.out.println();
            }
        } else {
            System.out.println("Ningun laberinto cargado");
        }
    }

    public static void showMapEmpty(){
        if (loaded){
            for (int i = 0; i < originalMap.length;i++){
                for (int j = 0; j < originalMap[i].length;j++){
                    System.out.print(originalMap[i][j]+" ");
                }
                System.out.println();
            }
        } else {
            System.out.println("Ningun laberinto cargado");
        }
    }

    public static void setEntranceExit() {

        if (entrances == true){
            System.out.println("Las casillas de entrada y salida ya han sido establecidas, carga otra vez el laberinto para poder establecer nuevas casillas de entrada/salida.");
        } else {
            if (loaded){
                System.out.println("Introduce a continuacion los valores de la casilla de entrada:");
                int startX = Interface.getInt("Introduce la cordenada X:");
                int startY = Interface.getInt("Introduce la cordenada Y:");
                if (startX >= 0 && startX < map.length) {
                    startI = startX;
                }
                if (startY >= 0 && startY < map[0].length) {
                    startJ = startY;
                }

                if (startI == startX && startJ == startY){
                    System.out.println("Introduce a continuacion los valores de la casilla de salida:");
                    int endX = Interface.getInt("Introduce la cordenada x:");
                    int endY = Interface.getInt("Introduce la cordenada Y:");
                    if (startI != endX || startJ != endY){
                        if (endX >= 0 && endX < map.length){
                            endI = endX;
                        }
                        if (endY >= 0 && endY < map[0].length) {
                            endJ = endY;

                            map[startI][startJ] = 'E';
                            map[endI][endJ] = 'S';
                            entrances = true;
                            showMap();
                        }
                        if (endI != endX && endJ != endY){
                            System.out.println("Los datos introducidos no son correctos");
                        }
                    } else {
                        System.out.println("Los valores de entrada no pueden ser los mismos que los de salida");
                    }
                } else {
                    System.out.println("Los datos introducidos no son correctos");
                }

            } else {
                System.out.println("Ningun laberinto cargado");
            }
        }
    }

    public static void searchPaths(){
        pathFound = false;

        if (loaded == true && entrances == true){
            int reply = Interface.getInt(Config.SEARCH_PATHS_MENU);
            if (reply == 1){
                resetMapState();
                findFirstPath();
            } else if (reply == 2) {
                resetMapState();
                findShortedPath();
            } else if (reply == 0){
                System.out.println("Cancelando");
            } else {
                System.out.println("El valor introducido no es correcto");
            }
        } else {
            System.out.println("El laberito no a sido cargado o las casillas de entrada/salida no han sido establecidas.");
        }
    }

    private static void resetMapState() {
        for (int i = 0; i < map.length; i++) {
            map[i] = Arrays.copyOf(originalMap[i], originalMap[i].length);
        }
        map[startI][startJ] = 'E';
        map[endI][endJ] = 'S';
        visited = new boolean[map.length][map[0].length];
        path = new ArrayList<>();
        pathFound = false;
    }


    private static void findFirstPath(){
        visited = new boolean[map.length][map[0].length];
        path = new ArrayList<>();
        pathFound = false;

        search(startI, startJ);

        if (pathFound){
            map[startI][startJ] = 'E';
            map[endI][endJ] = 'S';
            showMap();
            
            System.out.println("Pasos: "+ path.size());
            for (Coordinate c : path){
                System.out.println(c.toString());
            }
        } else {
            System.out.println("No se encontro un camino hasta la salida");
        }

    }

    private static void findShortedPath(){
        visited = new boolean[map.length][map[0].length];
        path = new ArrayList<>();
        searchShortest(startI, startJ);

        if (pathFound){
            map[startI][startJ] = 'E';
            map[endI][endJ] = 'S';
            showMap();

            System.out.println("Pasos: "+ path.size());
            for (Coordinate c : path){
                System.out.println(c.toString());
            }
        } else {
            System.out.println("No se encontro un camino hasta la salida");
        }

    }

    private static boolean search(int i, int j){
        if (i < 0 || i >= map.length || j < 0 || j >= map[0].length)
            return false;

        if (visited[i][j] || map[i][j] == '#')
            return false;

        visited[i][j] = true;

        if (i == endI && j == endJ) {
            pathFound = true;
            return true;
        }

        if (search(i + 1, j)) {
            map[i][j] = 'v';
            path.add(new Coordinate(i, j, "Abajo"));
            return true;
        }
        if (search(i, j + 1)) {
            map[i][j] = '>';
            path.add(new Coordinate(i, j, "Derecha"));
            return true;
        }
        if (search(i - 1, j)) {
            map[i][j] = '^';
            path.add(new Coordinate(i, j, "Arriba"));
            return true;
        }
        if (search(i, j - 1)) {
            map[i][j] = '<';
            path.add(new Coordinate(i, j, "Izquierda"));
            return true;
        }

        if (!path.isEmpty()){
            path.remove(path.size() - 1);
        }
        return false;
    }

    private static boolean searchShortest(int startI, int startJ) {

        visited = new boolean[map.length][map[0].length];
        path = new ArrayList<>();
        pathFound = false;


        int[][] parentI = new int[map.length][map[0].length];
        int[][] parentJ = new int[map.length][map[0].length];
        for (int i = 0; i < map.length; i++) {
            Arrays.fill(parentI[i], -1);
            Arrays.fill(parentJ[i], -1);
        }


        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startI, startJ});
        visited[startI][startJ] = true;


        int[] di = {1, 0, -1, 0};
        int[] dj = {0, 1, 0, -1};
        String[] directions = {"Abajo", "Derecha", "Arriba", "Izquierda"};
        char[] arrows = {'v', '>', '^', '<'};

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int i = current[0], j = current[1];

            if (i == endI && j == endJ) {

                LinkedList<Coordinate> shortestPath = new LinkedList<>();
                int ci = i, cj = j;

                while (parentI[ci][cj] != -1) {
                    int pi = parentI[ci][cj];
                    int pj = parentJ[ci][cj];

                    for (int d = 0; d < 4; d++) {
                        if (pi + di[d] == ci && pj + dj[d] == cj) {

                            if (map[pi][pj] != 'E' && map[pi][pj] != 'S') {
                                map[pi][pj] = arrows[d];
                            }
                            shortestPath.addFirst(new Coordinate(pi, pj, directions[d]));
                            break;
                        }
                    }
                    ci = pi;
                    cj = pj;
                }

                path.addAll(shortestPath);
                pathFound = true;
                return true;
            }

            for (int d = 0; d < 4; d++) {
                int ni = i + di[d];
                int nj = j + dj[d];

                if (ni >= 0 && ni < map.length && nj >= 0 && nj < map[0].length
                        && !visited[ni][nj] && map[ni][nj] != '#') {
                    visited[ni][nj] = true;
                    parentI[ni][nj] = i;
                    parentJ[ni][nj] = j;
                    queue.add(new int[]{ni, nj});
                }
            }
        }

        return false;
    }
}
