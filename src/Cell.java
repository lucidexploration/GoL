
public class Cell {

    public static boolean[][] cellArray;
    public static boolean[][] updateArray;

    //create the new cell with all cells set to false/dead
    public Cell() {
        int i = 0;
        int z = 0;
        cellArray = new boolean[76][76];
        while (i < cellArray.length) {
            z = 0;
            while (z < cellArray.length) {
                cellArray[i][z] = false;
                z++;
            }
            i++;
        }
    }

    //flip the cell state. dead if alive. alive if dead.
    //this is used for the mouseclick event.
    public static void Flip(int x, int y) {
        cellArray[x][y]=!cellArray[x][y];
    }

    //set state of temp array to true
    public static void Kill(int x, int y) {
        updateArray[x][y] = false;
    }

    //set state of temp array to false
    public static void Live(int x, int y) {
        updateArray[x][y] = true;
    }

    //returns state. true for alive, false for dead.
    public static boolean isAlive(int x, int y) {
        return cellArray[x][y] == true;
    }

    //see if cells are overpopulated
    //see if cells can keep living with 2 or 3 neighbors
    //see if cells die from < 2 neigbors
    public static boolean canLive(int x, int y) {
        if (isAlive(x, y) && Neighbors(x, y) == 2
                || isAlive(x, y) && Neighbors(x, y) == 3) {
            return true;
        }
        return false;
    }

    //see if a dead cell can come to life
    public static boolean canRevive(int x, int y) {
        if (!isAlive(x, y) && Neighbors(x, y) == 3) {
            return true;
        }
        return false;
    }

    //returns the number of living neighbors a cell has
    public static int Neighbors(int x, int y) {
        int count = 0;

        if (x == 0 && y == 0) {
            if (isAlive(74, 74)) {
                count++;
            }
            if (isAlive(74, y)) {
                count++;
            }
            if (isAlive(74, y + 1)) {
                count++;
            }
            if (isAlive(x, 74)) {
                count++;
            }
            if (isAlive(x, y + 1)) {
                count++;
            }
            if (isAlive(x + 1, 74)) {
                count++;
            }
            if (isAlive(x + 1, y)) {
                count++;
            }
            if (isAlive(x + 1, y + 1)) {
                count++;
            }
            return count;
        }

        if (x == 74 && y == 74) {
            if (isAlive(x - 1, y - 1)) {
                count++;
            }
            if (isAlive(x - 1, y)) {
                count++;
            }
            if (isAlive(x - 1, 0)) {
                count++;
            }
            if (isAlive(x, y - 1)) {
                count++;
            }
            if (isAlive(x, 0)) {
                count++;
            }
            if (isAlive(0, y - 1)) {
                count++;
            }
            if (isAlive(0, y)) {
                count++;
            }
            if (isAlive(0, 0)) {
                count++;
            }
            return count;
        }

        if (x == 0) {
            if (isAlive(74, y - 1)) {
                count++;
            }
            if (isAlive(74, y)) {
                count++;
            }
            if (isAlive(74, y + 1)) {
                count++;
            }
            if (isAlive(x, y - 1)) {
                count++;
            }
            if (isAlive(x, y + 1)) {
                count++;
            }
            if (isAlive(x + 1, y - 1)) {
                count++;
            }
            if (isAlive(x + 1, y)) {
                count++;
            }
            if (isAlive(x + 1, y + 1)) {
                count++;
            }
            return count;
        }

        if (y == 0) {
            if (isAlive(x - 1, 74)) {
                count++;
            }
            if (isAlive(x - 1, y)) {
                count++;
            }
            if (isAlive(x - 1, y + 1)) {
                count++;
            }
            if (isAlive(x, 74)) {
                count++;
            }
            if (isAlive(x, y + 1)) {
                count++;
            }
            if (isAlive(x + 1, 74)) {
                count++;
            }
            if (isAlive(x + 1, y)) {
                count++;
            }
            if (isAlive(x + 1, y + 1)) {
                count++;
            }
            return count;
        }

        if (x == 74) {
            if (isAlive(x - 1, y - 1)) {
                count++;
            }
            if (isAlive(x - 1, y)) {
                count++;
            }
            if (isAlive(x - 1, y + 1)) {
                count++;
            }
            if (isAlive(x, y - 1)) {
                count++;
            }
            if (isAlive(x, y + 1)) {
                count++;
            }
            if (isAlive(0, y - 1)) {
                count++;
            }
            if (isAlive(0, y)) {
                count++;
            }
            if (isAlive(0, y + 1)) {
                count++;
            }
            return count;
        }

        if (y == 74) {
            if (isAlive(x - 1, y - 1)) {
                count++;
            }
            if (isAlive(x - 1, y)) {
                count++;
            }
            if (isAlive(x - 1, 0)) {
                count++;
            }
            if (isAlive(x, y - 1)) {
                count++;
            }
            if (isAlive(x, 0)) {
                count++;
            }
            if (isAlive(x + 1, y - 1)) {
                count++;
            }
            if (isAlive(x + 1, y)) {
                count++;
            }
            if (isAlive(x + 1, 0)) {
                count++;
            }
            return count;
        }

        if (isAlive(x - 1, y - 1)) {
            count++;
        }
        if (isAlive(x - 1, y)) {
            count++;
        }
        if (isAlive(x - 1, y + 1)) {
            count++;
        }
        if (isAlive(x, y - 1)) {
            count++;
        }
        if (isAlive(x, y + 1)) {
            count++;
        }
        if (isAlive(x + 1, y - 1)) {
            count++;
        }
        if (isAlive(x + 1, y)) {
            count++;
        }
        if (isAlive(x + 1, y + 1)) {
            count++;
        }
        return count;
    }

    //step the array forward one generation and update the changes to the
    //cellArray
    public static void Step() {
        //create the new temp array to store changes.
        updateArray = new boolean[75][75];
        //cycle through the cellArray to update the updateArray
        int i = 0;
        int z = 0;
        while (i < updateArray.length) {
            z = 0;
            while (z < updateArray.length) {
                if (canLive(i, z)) {
                    Live(i, z);
                }
                if (canRevive(i, z)) {
                    Live(i, z);
                }
                z++;
            }
            i++;
        }
        //now copy the updateArray over the cellArray
        int p = 0;
        int q = 0;
        while (p < updateArray.length) {
            q = 0;
            while (q < updateArray.length) {
                cellArray[p][q] = updateArray[p][q];
                q++;
            }
            p++;
        }
    }
}
