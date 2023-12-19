public class game_Life {
    public static void main(String[] args) throws InterruptedException {

        int [][] w = new int[20][20]; //matrix size
        int delay = 500; //delay for refresh
        int countDays = 0;
        start(w);
        drawWorld(w);


        for (int n = 0; n < 200; n++) { //200 - number of cycles
            updateWorld(w);
            drawWorld(w);

            Thread.sleep(delay);
            countDays++;
            System.out.println(countDays);

        }
    }

    // assigning random 0 - 1, => then 0 or 1 if greater than 0.5
    private static void start (int [][] world)
    {
        for (int i = 0; i < world.length; i++) {
            for (int j = 0; j < world[i].length; j++) {
                world[i][j] = Math.random() > 0.5 ? 1 : 0;
            }
        }
    }


    // replacing 0 and 1 on squares (from windows chart instrument)
    private static void drawWorld (int[][]world)
    {
        for (int[] el : world) {
            for (int item : el) {
                System.out.print(item == 1 ? "■  " : "□  ");
            }
            System.out.println();
        }

    }

    //Creating temporary matrix , size like original
    private static void updateWorld (int[][] world) {
        int[][] cycle = new int[world.length][world[0].length];

        for (int i = 0; i < world.length; i ++) {
            for (int j = 0; j < world[i].length; j ++) {

                // using method countCell in order to find the future destiny of the cell
                int numberOfCells = countCells(world, i, j);

                // using game rules
                if (world[i][j] == 1) {
                    cycle[i][j] = (numberOfCells == 2 || numberOfCells == 3) ? 1 : 0;
                } else {
                    cycle[i][j] = (numberOfCells == 3) ? 1 : 0;
                }
            }
        }

        // finally replacing previous cycle with new one
        System.arraycopy(cycle,0, world, 0, world.length);
    }

    // method to find out how many adjacent cells are using 9 cases of positioning
    private static int countCells (int[][] world, int line, int col){
        int neibCells = 0;
        if (line == 0 && col == 0)
        {
            neibCells = world[line][col + 1] + world[line + 1][col] + world[line + 1][col + 1];
        }
        else if (line == 0 && col != world[line].length - 1)
        {
            neibCells = world[line][col + 1] + world[line + 1][col] + world[line + 1][col + 1] + world[line][col - 1] + world[line + 1][col - 1];
        }
        else if (line == 0 && col == world[line].length - 1)
        {
            neibCells = world[line][col - 1] + world[line + 1][col - 1] + world[line + 1][col];
        }
        else if (line != world.length - 1 && col == 0)
        {
            neibCells = world[line - 1][col] + world[line + 1][col] + world[line + 1][col + 1] + world[line][col + 1] + world[line - 1][col + 1];
        }
        else if (line == world.length - 1 && col == 0)
        {
            neibCells = world[line][col + 1] + world[line - 1][col] + world[line - 1][col + 1];
        }
        else if (line == world.length - 1 && col != world[line].length - 1)
        {
            neibCells = world[line][col - 1] + world[line - 1][col - 1] + world[line - 1][col] + world[line - 1][col + 1] + world[line][col + 1];
        }
        else if (line == world.length - 1 && col == world[line].length - 1)
        {
            neibCells = world[line][col - 1] + world[line - 1][col - 1] + world[line - 1][col];
        }
        else if (col == world[line].length - 1)
        {
            neibCells = world[line + 1][col] + world[line +1][col - 1] + world[line][col - 1] + world[line - 1][col - 1] + world[line - 1][col];
        } else {
            neibCells = world[line][col + 1] + world[line + 1][col] + world[line + 1][col + 1] + world[line][col - 1] + world[line + 1][col - 1] + world[line - 1][col - 1] + world[line - 1][col] + world[line - 1][col + 1];
        }

        return neibCells;
    }

}