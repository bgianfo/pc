//******************************************************************************
//
// File: MSAreaSmp.java
// Author: Brian Gianforcaro (bjg1955@cs.rit.edu)
//
//******************************************************************************

import edu.rit.pj.Comm;
import edu.rit.pj.ParallelTeam;
import edu.rit.pj.ParallelRegion;
import edu.rit.pj.LongForLoop;
import edu.rit.pj.LongForLoop;

import edu.rit.pj.reduction.SharedDouble;

import edu.rit.util.Random;

/**
 * Class MSAreaSmp is a sequential program that calculates the Area of the Mandelbrot Set.
 * <P>
 * Usage: java MSAreaeq <I>seed</I> <I>maxiter</I> <I>N</I>
 * <BR><I>seed</I> = random sed (a long)
 * <BR><I>maxiter</I> = maximum number of iterations (an int)
 * <BR><I>N</I> = The number of random points (a long) 
 * <P>
 * @author Brian Gianforcaro
 * @version 13-Jan-2011
 */
public class MSAreaSmp
{

    /** Command line arguments. */
    static long seed;
    static int maxiter;
    static long N;
    static SharedDouble numPoints;
    static SharedDouble numMembers;

    /** Prevent construction. */
    private MSAreaSmp(){ }

    /**
     * Print a usage message and exit.
     */
    private static void usage() {
        System.err.println("Usage: java MSAreaSmp <seed> <maxiter> <N>");
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

        numMembers = new SharedDouble();
        numPoints = new SharedDouble();

        new ParallelTeam().execute( new ParallelRegion() {
            public void run() throws Exception {
                execute( 0, N-1, new LongForLoop() {
                    public void run( long first, long last ) {

                        Random prng = Random.getInstance( seed );
                        prng.skip(first*2);

                        for ( long n = first; n <= last; n++ ) {
                            double x = (prng.nextDouble()*4)-2;
                            double y = (prng.nextDouble()*4)-2;
                            int i = 0;
                            double aold = 0.0;
                            double bold = 0.0;
                            double a = 0.0;
                            double b = 0.0;
                            double zmagsqr = 0.0;
                            while ( i < maxiter && zmagsqr <= 4.0 ) {
                                ++i;
                                a = aold*aold - bold*bold + x;
                                b = 2.0*aold*bold + y;
                                zmagsqr = a*a + b*b;
                                aold = a;
                                bold = b;
                            }

                            if ( i == maxiter ) {
                                numMembers.incrementAndGet();
                            }
                            numPoints.incrementAndGet();
                        }
                    }
                });
            }
        });

        // Sample area is -2 to 2 for x and y
        double sampleArea = 4.0*4.0;

        double area = ((numMembers.doubleValue()*sampleArea)/numPoints.doubleValue());

        System.out.println("MS area = " + area );
 

        // Stop timing.
        System.out.println((System.currentTimeMillis()-t1) + " msec");
    }

}
