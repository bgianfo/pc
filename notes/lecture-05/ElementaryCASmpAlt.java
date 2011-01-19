import edu.rit.pj.Comm;
import edu.rit.pj.IntegerForLoop;
import edu.rit.pj.ParallelRegion;
import edu.rit.pj.ParallelTeam;

public class ElementaryCASmpAlt {

    private ElementaryCASmpAlt() { }

    // Shared variables.
    static int rule;
    static int gridSize;
    static long numSteps;
    static boolean[] grid, nextGrid;

    public static void main(String[] args) throws Exception {
        Comm.init(args);

        // Start timing.
        long t1 = System.currentTimeMillis();
        
        // Parse command line arguments.
        if (args.length != 3) usage();
        rule = Integer.parseInt (args[0]);
        if (rule < 0 || rule > 255) usage();
        gridSize = Integer.parseInt (args[1]);
        if (gridSize < 1) usage();
        numSteps = Long.parseLong (args[2]);
        if (numSteps < 0) usage();

        grid = new boolean[gridSize];
        nextGrid = new boolean[gridSize];
        grid[grid.length/2] = true;
        // printGrid();

        ParallelTeam pt = new ParallelTeam();
        for (long step = 0; step < numSteps; step++) {
            pt.execute(new ParallelRegion() {
                    public void run() throws Exception {
                        execute (0, grid.length - 1, new IntegerForLoop() {
                                public void run (int first, int last) {
                                    for (int i = first; i <= last; i++) {
                                        updateGrid(i);
                                    }
                                }
                            });
                    }
                });
            boolean[] tmpGrid = grid;
            grid = nextGrid;
            nextGrid = tmpGrid;
            // printGrid();
        }

        // Stop timing.
        long t2 = System.currentTimeMillis();
        
        countGrid();
        System.out.println("Running time: " + (t2-t1) + " msec");
    }

    private static void updateGrid(int i) {
        short x = 0;
        if (grid[(i-1+grid.length)%grid.length]) { x += 4; }
        if (grid[i]) { x += 2; }
        if (grid[(i+1)%grid.length]) { x += 1; }
        if (((1 << x) & rule) == 0) {
            nextGrid[i] = false;
        } else {
            nextGrid[i] = true;
        }
    }

    private static void printGrid() {
        int r = 0;
        for (int i = 0; i < grid.length; i++) {
            if (grid[i]) {
                System.out.print("*");
            } else {
                System.out.print(" ");
            }
        }
        System.out.println();
    }

    private static void countGrid() {
        int r = 0;
        for (int i = 0; i < grid.length; i++) {
            if (grid[i]) { r++; } 
        }
        System.out.println(r);
    }

    /**
     * Print a usage message and exit.
     */
    private static void usage() {
        System.err.println ("Usage: java ElementaryCASmpAlt <rule> <gridSize> <numSteps>");
        System.err.println ("<rule> = ");
        System.err.println ("<gridSize> = ");
        System.err.println ("<numSteps> = ");
        System.exit (1);
    }

}