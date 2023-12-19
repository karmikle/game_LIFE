public class Game_LIFE {
    public static void main(String[] args) throws InterruptedException {

        int [][] w = new int[20][20];
        int delay = 500;
        int countDays = 0;
        start(w);
        drawWorld(w);


        for (int n = 0; n < 200; n++) {
            updateWorld(w);
            drawWorld(w);

            Thread.sleep(delay);
            countDays++;
            System.out.println(countDays);

        }
    }

    private static void start (int [][] world)
    {
        for ( int i = 0; i < world.length; i++) {
            for (int j = 0; j < world[i].length; j++) {
                world[i][j] = Math.random() > 0.5 ? 1 : 0;
            }
        }
    }

    private static void drawWorld (int[][]world)
    {
        for (int[] el : world) {
            for (int item : el) {
                System.out.print(item == 1 ? "■  " : "□  ");
            }
            System.out.println();
        }

//        int count = 0;
//        for (int[] ints : world) {
//            for (int anInt : ints) {
//                if (anInt > 0) {
//                    count++;
//                }
//            }
//        }
//        System.out.println(count);

    }

    private static void updateWorld (int[][] world) {
        int[][] cycle = new int[world.length][world[0].length];

        for (int i = 0; i < world.length; i ++) {
            for (int j = 0; j < world[i].length; j ++) {
                int numberOfCells = countCells(world, i, j);
                if (world[i][j] == 1) {
                    cycle[i][j] = (numberOfCells == 2 || numberOfCells == 3) ? 1 : 0;
                } else {
                    cycle[i][j] = (numberOfCells == 3) ? 1 : 0;
                }
            }
        }
        System.arraycopy(cycle,0, world, 0, world.length);
    }

    private static int countCells (int[][] world, int line, int col){
        int neibCells = 0;
        if (line == 0 && col == 0)
        {
            neibCells = world[line][col+1] + world[line+1][col] + world[line+1][col+1];
        }
        else if (line == 0 && col !=world[line].length-1)
        {
            neibCells = world[line][col+1] + world[line+1][col] + world[line+1][col+1] + world[line][col-1] + world[line+1][col-1];
        }
        else if (line == 0 && col == world[line].length-1)
        {
            neibCells = world[line][col-1] + world[line+1][col-1] + world[line+1][col];
        }
        else if (line != world.length - 1 && col ==0)
        {
            neibCells = world[line-1][col] + world[line+1][col] + world[line+1][col+1] + world[line][col+1] + world[line-1][col+1];
        }
        else if (line == world.length - 1 && col == 0)
        {
            neibCells = world[line][col+1] + world[line-1][col] + world[line-1][col+1];
        }
        else if (line == world.length - 1 && col != world[line].length - 1)
        {
            neibCells = world[line][col-1] + world[line-1][col-1] + world[line-1][col] + world[line-1][col+1] + world[line][col+1];
        }
        else if (line == world.length - 1 && col == world[line].length - 1)
        {
            neibCells = world[line][col-1] + world[line-1][col-1] + world[line-1][col];
        }
        else if (col == world[line].length - 1)
        {
            neibCells = world[line+1][col] + world[line+1][col-1] + world[line][col-1] + world[line-1][col-1] + world[line-1][col];
        } else {
            neibCells = world[line][col+1] + world[line+1][col] + world[line+1][col+1] + world[line][col-1] + world[line+1][col-1] + world[line-1][col-1] + world[line-1][col] + world[line-1][col+1];
        }

        return neibCells;
    }

}