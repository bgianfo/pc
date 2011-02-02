

import edu.rit.util.Range;
import edu.rit.mp.ByteBuf;
import edu.rit.mp.buf.ByteArrayBuf;

import edu.rit.pj.Comm;
import edu.rit.pj.ParallelTeam;
import edu.rit.pj.ParallelRegion;
import edu.rit.pj.ParallelSection;

/**
 * Class ElementaryCAClu is the cluster version of the Elementary Cellular Automaton 
 * <P>
 * Usage: java ElementaryCAClu <I>rule</I> <I>gridSize</I> <I>numSteps</I>
 * <BR><I>rule</I> - The rule to execute ( an integer )
 * <BR><I>gridSize</I> - The size of the grid ( and integer )
 * <BR><I>numSteps</I> - The number of steps to execute ( an integer )
 *
 * @author  Brian Gianforcaro
 * @version 25-Jan-2011
 */
public class ElementaryCAClu {

  static int gridSize;
  static int steps;
  static byte[] rule = new byte[8];

  static Comm world;
  static int worldSize;
  static int rank;

  static int GRID_START_MSG = 10;
  static int CHUNK_DONE_MSG = 20;

  private ElementaryCAClu() {}

  /**
   * Usage statement
   */
  private static void usage() {
    System.out.println("");
    System.out.println( "Usage: ElementaryCAClu <rule> <gridSize> <numSteps>\n" );
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
    gridSize  = Integer.parseInt( args[1] );
    steps     = Integer.parseInt( args[2] );

    setupRule( ruleValue );
  }

  /**
   * Get the number of cell's with value 1
   */
  private static int getCount( byte[] grid ) {
    int counter = 0;
    for ( int cell = 0; cell < gridSize; ++cell ) {
      counter += grid[cell];
    }
    return counter;
  }

  /**
   * The worker section to be run on cluster nodes. 
   */
  private static void masterSection( byte[] grid ) throws Exception {

    grid[gridSize/2] = 1;

    Range[] ranges = new Range( 0, gridSize-1 ).subranges( worldSize );

    ByteBuf[] dest = ByteBuf.sliceBuffers( grid, ranges ); 

    for ( int i = 0; i < steps; i++ ) {

      // Send out tasks

      // Make sure the reference is updated
      ByteBuf buffer = ByteBuf.buffer( grid );
      world.broadcast( 0, GRID_START_MSG, buffer );

      // Retreive resutls
      world.gather( 0, CHUNK_DONE_MSG, null, dest );


      // Copy all results to buffer
      int count = 0;
      for ( ByteBuf curBuf : dest ) { 
        for ( int j = 0; j < curBuf.length(); j++ ) {
          grid[count] = curBuf.get(j);
          count++;
        }
      }
    }
    world.broadcast( 0, GRID_START_MSG, null );
  }


  /**
   * The worker section to be run on cluster nodes. 
   */
  private static void workerSection() throws Exception {

    Range[] ranges = new Range( 0, gridSize-1).subranges( worldSize );
    int lb = ranges[rank].lb();
    int ub = ranges[rank].ub();

    byte[] chunk = new byte[ranges[rank].length()];

    while ( true ) {
      ByteBuf grid = ByteBuf.buffer();
      world.broadcast( 0, GRID_START_MSG, grid );
      if ( grid == null ) {
        break;
      }

      int iChunk = 0;

      for ( int iCell = lb; iCell <= ub; iCell++ ) {
        // Compute previous and next cell indices
        int iPrev = (iCell+gridSize-1) % gridSize;
        int iNext = (iCell+1) % gridSize;

        // Compute which bit of the rule to use
        int bit = (grid.get(iPrev)*4) + (grid.get(iCell)*2) + (grid.get(iNext)*1);

        chunk[iCell] = rule[bit];
        iChunk++;
      }

      ByteBuf chunkBuf = ByteBuf.buffer( chunk );
      world.gather( 0, CHUNK_DONE_MSG, chunkBuf, null );
    }
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
    world = Comm.world();
    worldSize = world.size();
    rank = world.rank();

    // Parse the command line arguments
    parseArgs( args );


    if ( 0 == rank ) {

      final byte[] grid = new byte[gridSize];

		  // In master process, run master section & worker section.
    	new ParallelTeam(2).execute( new ParallelRegion() {
				public void run() throws Exception {
					execute( new ParallelSection() {
						public void run() throws Exception {
							masterSection( grid );
            }
          }, new ParallelSection() {
						public void run() throws Exception {
							workerSection();
            }
          });
        }
      });

      // Count bits with value 1 in the final grid
      System.out.println( getCount( grid ) );
      System.out.println( "Running time = " +
                          (System.currentTimeMillis()-start) + " msec" );
    } else {
		  // In worker process, run only worker section.
      workerSection();
    }
  }
}
