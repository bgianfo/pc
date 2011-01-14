//******************************************************************************
//
// File: MSAreaSeq.java
// Author: Brian Gianforcaro (bjg1955@cs.rit.edu)
//
//******************************************************************************

import edu.rit.pj.Comm;

/**
 * Class MSAreaSeq is a sequential program that calculates the Area of the Mandelbrot Set.
 * <P>
 * Usage: java MSAreaeq <I>seed</I> <I>maxiter</I> <I>N</I>
 * <BR><I>seed</I> = random sed (a long)
 * <BR><I>maxiter</I> = maximum number of iterations (an int)
 * <BR><I>N</I> = The number of random points (a long) 
 * <P>
 * @author Brian Gianforcaro
 * @version 13-Jan-2011
 */
public class MSAreaSeq
{

    /** Command line arguments. */
    static long seed;

    static int maxiter;

    static long N;


    /** Prevent construction. */
    private MSAreaSeq(){ }

    /**
     * Print a usage message and exit.
     */
    private static void usage() {
        System.err.println("Usage: java MSAreaSeq <seed> <maxiter> <N>");
        System.err.println("<seed> = random sed (a long)");
        System.err.println("<maxiter> = maximum number of iterations (an int)");
        System.err.println("<N> = The number of random points (a long)");
        System.exit(1);
    }


    /**
     * Mandelbrot Set main program.
     */
    public static void main( String[] args ) throws Exception {

        long t1 = System.currentTimeMillis();
        Comm.init( args );

        if ( args.length != 3 ) {
          usage();
        }

        // Unpack arguments
        seed = Long.parseLong(args[0]);
        maxiter = Integer.parseInt( args[1] );
        N = Long.parseLong(args[2]);

        // Stop timing.
        System.out.println((System.currentTimeMillis()-t1) + " msec");
    }

}
