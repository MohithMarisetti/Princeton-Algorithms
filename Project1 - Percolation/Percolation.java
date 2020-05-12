/* *****************************************************************************
 *  Name:    Mohith Marisetti
 *  NetID:   aturing
 *  Precept: P00
 *
 *  Description:  Prints 'Hello, World' to the terminal window.
 *                By tradition, this is everyone's first program.
 *                Prof. Brian Kernighan initiated this tradition in 1974.
 *
 **************************************************************************** */


import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] grid;
    private WeightedQuickUnionUF wufStructure1;
    private WeightedQuickUnionUF wufStructure2;

    private int openSitesCount;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {

        if (n <= 0) {
            throw new IllegalArgumentException();
        }

        // Percolation grid
        grid = new boolean[n][n];
        openSitesCount = 0;


        // WeightQuickUnionUF data structure
        wufStructure1 = new WeightedQuickUnionUF(n * n + 2);

        wufStructure2 = new WeightedQuickUnionUF(n * n + 1);


    }

    private void isValidLocation(int row, int col) {

        if (row <= 0 || row >= grid[0].length + 1) {
//            throw new IllegalArgumentException("row index must be between 1 and " + grid[0].length);
            throw new IllegalArgumentException("Row and Col = " + row + "," + col);
        }
        if (col <= 0 || col >= grid[0].length + 1) {
//            throw new IllegalArgumentException("column index must be between 1 and " + grid[0].length);
            throw new IllegalArgumentException("Row and Col = " + row + "," + col);
        }
    }


    private int[] getAdjacentOpenNodes(int row, int col) {
        int[] adjacentOpenNodes = new int[4];

        int i = 0;
        try {
            if (isOpen(row - 1, col)) {
                adjacentOpenNodes[i] = getCorrespondingNodeValue(row - 1, col);
//                System.out.println((row - 1) + " , " + col);
                i += 1;
            }
        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
        }


        try {
            if (isOpen(row + 1, col)) {
                adjacentOpenNodes[i] = getCorrespondingNodeValue(row + 1, col);
//                System.out.println((row + 1) + " , " + col);
                i += 1;
            }
        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
        }
        try {
            if (isOpen(row, col - 1)) {
                adjacentOpenNodes[i] = getCorrespondingNodeValue(row, col - 1);
//                System.out.println(row + " , " + (col - 1));
                i += 1;
            }
        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
        }
        try {
            if (isOpen(row, col + 1)) {
                adjacentOpenNodes[i] = getCorrespondingNodeValue(row, col + 1);
//                System.out.println(row + " , " + (col + 1));
                i += 1;
            }
        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
        }

        // For every element in First Row, perform union(virtualTop, eachElementInRow1) by adding that node to adjacentOpenNodes array
        if (row - 1 == 0) {
            adjacentOpenNodes[i] = -1;
            i += 1;
            // System.out.println(0);
        }


        // For every element in Last Row, perform union(virtualBottom, eachElementInRowN) by adding that node to adjacentOpenNodes array
        if (row - 1 == grid[0].length - 1) {
            adjacentOpenNodes[i] = grid[0].length * grid[0].length + 1;
            i += 1;
            // System.out.println("n*n + 1");
        }


        return adjacentOpenNodes;


    }

    private int getCorrespondingNodeValue(int row, int col) {

        return (row - 1) * grid[0].length + col;


    }


    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        isValidLocation(row, col);
        try {
            if (!isOpen(row, col)) {
                grid[row - 1][col - 1] = true;  // Opening a previously blocked site
                openSitesCount += 1;        // Counting the number of opensites

                // System.out.println("The adjacent nodes for " + row + "," + col);

                // After opening the site perform union with its adjacent open nodes
                int[] adjacentOpenNodes = getAdjacentOpenNodes(row, col);   // Call to getAdjacentOpenNodes()


                for (int i = 0; i < adjacentOpenNodes.length; i++) {

                    if (adjacentOpenNodes[i] == 0) {
                        break;
                    }

                    if (adjacentOpenNodes[i] == -1) {
                        adjacentOpenNodes[i] = 0;
                    }
                    //System.out.println("performing union between " + adjacentOpenNodes[i] + " , " + getCorrespondingNodeValue(row, col));

                    wufStructure1.union(adjacentOpenNodes[i], getCorrespondingNodeValue(row, col));

                    if (adjacentOpenNodes[i] != grid[0].length * grid[0].length + 1) {
                        wufStructure2.union(adjacentOpenNodes[i], getCorrespondingNodeValue(row, col));
                    }


                }
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            throw new IllegalArgumentException();
        }


    }


    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        isValidLocation(row, col);

        try {
            return grid[row - 1][col - 1] == true;

        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            throw new IllegalArgumentException();
        }


    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {


        isValidLocation(row, col);

        // Check if element row,col is connected to virtualTop or not. // connected(grid[row-1][col-1], virtualTop)
        return wufStructure2.connected(getCorrespondingNodeValue(row, col), 0);


    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSitesCount;
    }

    // does the system percolate?
    public boolean percolates() {

        int n = grid[0].length;


        // Check if element virtualBottom is connected to virtualTop or not. // connected(virtualBottom, virtualTop)
        return wufStructure1.connected(n * n + 1, 0);
    }

    // test client (optional)
    public static void main(String[] args) {

    /*
        File myObj = new File("./inputs/input10.txt");
        try {
            Scanner myReader = new Scanner(myObj);


            int n = Integer.parseInt(myReader.nextLine());

            Percolation p = new Percolation(n);
            System.out.println("Created a percolation object with n = " + n);
            System.out.println("Read the following data from the file ");
            for (int i = 1; i <= 56; i++) {
                String data = myReader.nextLine().trim();

                String[] arrOfStr = data.split("\\s+");
                String s1 = arrOfStr[0].trim();
                String s2 = arrOfStr[1].trim();
                System.out.print("(" + s1 + "," + s2 + ")");
                p.open(Integer.parseInt(s1), Integer.parseInt(s2));

            }

            System.out.println();
            System.out.println(p.percolates());

            System.out.println(p.isFull(9, 1));

            myReader.close();
        } catch (FileNotFoundException e) {

        }

     */
    }
}
