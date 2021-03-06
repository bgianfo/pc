//******************************************************************************
//
// Author: Brian Gianforcaro (bjg1955@cs.rit.edu)
// File: ElementaryCASeq.java
//
//******************************************************************************


import edu.rit.pj.Comm;

/**
 * Class ElementaryCASeq is the sequential version of the Elementary Cellular Automaton 
 * <P>
 * Usage: java ElementaryCASeq <I>rule</I> <I>gridSize</I> <I>numSteps</I>
 * <BR><I>rule</I> - The rule to execute ( an integer )
 * <BR><I>gridSize</I> - The size of the grid ( and integer )
 * <BR><I>numSteps</I> - The number of steps to execute ( an integer )
 *
 * @author  Brian Gianforcaro
 * @version 12-Dec-2010
 */
public class ElementaryCASeq {

  static int size;
  static int steps;
  static byte[] rule = new byte[8];
  static byte[] grid;
  static byte[] nextGrid;

  private ElementaryCASeq() {}

  /**
   * Usage statement
   */
  private static void usage() {
    System.out.println("");
    System.out.println( "Usage: ElementaryCASeq <rule> <gridSize> <numSteps>\n" );
    System.out.println( "   <rule> - The rule to execute ( an integer ) " );
    System.out.println( "   <gridSize> - The size of the grid ( and integer )" );
    System.out.println( "   <numSteps> - The number of steps to execute ( an integer )\n" );
  }

  /**
   * Get the individual bit of an integer.
   */
  private static int getBit( int data, int bit ) {
    int mask = 1 << bit;
    return (data & mask) == mask ? 1 : 0;
  }

  /**
   * Parse the rule into individual bits.
   */
  private static void setupRule( int ruleValue ) {
    for ( int i = 0; i < 8; i++ ) {
      rule[i] = (byte)getBit( ruleValue, i );
    }
  }

  /**
   * Parse command line arguments and do some initializing.
   */
  private static void parseArgs( String[] args ) {

    int ruleValue;

    ruleValue = Integer.parseInt( args[0] );
    size      = Integer.parseInt( args[1] );
    steps     = Integer.parseInt( args[2] );

    setupRule( ruleValue );

    grid      = new byte[size];
    nextGrid  = new byte[size];
  }

  /**
   * Get the number of cell's with value 1
   */
  private static int getCount() {
    int counter = 0;
    for ( int cell = 0; cell < size; ++cell ) {
      counter += grid[cell];
    }
    return counter;
  }

  /**
   * Swap the current grid with the nextGrid
   */
  private static void swap() {
    byte[] old = grid;
    grid = nextGrid;
    nextGrid = old;
  }

  /**
   * Main program entry point
   */
  public static void main( String[] args ) throws Exception {

    long start = System.currentTimeMillis();

    // Check commandline arguments
    if ( args.length < 3 ) {
      usage();
      return;
    }

    // Initialize parallel infrastructure
    Comm.init( args );

    // Parse the command line arguments
    parseArgs( args );

    // Seed the grid with a single set bit.
    grid[size/2] = 1;

    for ( int step = 0; step < steps; step++ ) {

      for ( int iCell = 0; iCell < size; iCell++ ) {

        // Compute previous and next cell indices
        int iPrev = (iCell+size-1) % size;
        int iNext = (iCell+1) % size;

        // Compute which bit of the rule to use
        int bit = (grid[iPrev]*4)+(grid[iCell]*2)+(grid[iNext]*1);

        nextGrid[iCell] = rule[bit];
      }
      // Swap the current grid with the nextGrid
      swap();
    }

    // Count bits with value 1 in the final grid
    System.out.println( getCount() );
    System.out.println( "Running time = " +
                        (System.currentTimeMillis()-start) + " msec" );
  }
}
