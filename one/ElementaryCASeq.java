//******************************************************************************
//
// File:    ElementaryCASeq.java
// Package: ---
// Unit:    Class ElementaryCASeq
//
//******************************************************************************

/**
 * Class ElementaryCASeq is the sequential version of the first program illustrating
 * parallel computing.
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
  static byte[] rule;
  static byte[] grid;
  static byte[] nextGrid;

  private ElementaryCASeq() {}

  /**
   * Usage statement
   */
  public static void usage() {
    System.out.println( "Usage: ElementaryCASeq <rule> <gridSize> <numSteps>\n" );
    System.out.println( "   <rule> - The rule to execute ( an integer ) " );
    System.out.println( "   <gridSize> - The size of the grid ( and integer )" );
    System.out.println( "   <numSteps> - The number of steps to execute ( an integer )\n" );
  }

  /**
   * Get the individual bit of an integer.
   */
  public static int getBit( int data, int bit ) {
    int mask = 1 << bit;
    return (data & mask) == mask ? 1 : 0;
  }

  /**
   * Parse the rule into individual bits.
   */
  public static void setupRule( int ruleValue ) {
    for ( int i = 0; i < 8; i++ ) {
      rule[i] = (byte)getBit( ruleValue, i );
    }
  }

  /**
   * Main program entry point
   */
  public static void main( String[] args ) {

    if ( args.length < 3 ) {
      usage();
      return;
    }

    long start = System.currentTimeMillis();

    rule = new byte[8];
    setupRule( Integer.parseInt( args[0] ) );

    size = Integer.parseInt( args[1] );

    grid = new byte[size];
    nextGrid = new byte[size];

    steps = Integer.parseInt( args[2] );

    grid[size/2] = 1;

    for ( int step = 0; step < steps; step++ ) {

      for ( int cell = 0; cell < size; cell++ ) {

        int prev = (cell+size-1) % size;
        int next = (cell+1) % size;
        int bit = (grid[prev]*4)+(grid[cell]*2)+(grid[next]*1);

        nextGrid[cell] = rule[bit];
      }

      byte[] tmp = grid;
      grid = nextGrid;
      nextGrid = tmp;

    }

    // Count bits with value 1 in the final grid
    int counter = 0;
    for ( int cell = 0; cell < size; cell++ ) {
      if ( grid[cell] == 1 ) {
        counter++;
      }
    }

    System.out.println( counter );
    long stop = System.currentTimeMillis();
    System.out.println("Program running time = "+(stop-start)+" msec");
  }

}
