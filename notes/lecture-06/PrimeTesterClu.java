//******************************************************************************
//
// File:    PrimeTesterClu.java
// Package: ---
// Unit:    Class PrimeTesterClu
//
// This Java source file is copyright (C) 2008 by Alan Kaminsky. All rights
// reserved. For further information, contact the author, Alan Kaminsky, at
// ark@cs.rit.edu.
//
// This Java source file is part of the Parallel Java Library ("PJ"). PJ is free
// software; you can redistribute it and/or modify it under the terms of the GNU
// General Public License as published by the Free Software Foundation; either
// version 3 of the License, or (at your option) any later version.
//
// PJ is distributed in the hope that it will be useful, but WITHOUT ANY
// WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
// A PARTICULAR PURPOSE. See the GNU General Public License for more details.
//
// A copy of the GNU General Public License is provided in the file gpl.txt. You
// may also obtain a copy of the GNU General Public License on the World Wide
// Web at http://www.gnu.org/licenses/gpl.html.
//
//******************************************************************************

import edu.rit.pj.Comm;

/**
 * Class PrimeTesterClu is the sequential version of the first program illustrating
 * parallel computing.
 * <P>
 * Usage: java PrimeTesterClu <I>x1</I> [ <I>x2</I> . . . ]
 * <BR><I>x1</I> = Number to be tested for primality
 *
 * @author  Alan Kaminsky
 * @version 05-Aug-2008
 * @author  Matthew Fluet
 * @version 02-Dec-2010
 */
public class PrimeTesterClu {
    
    // Prevent construction.
    
    private PrimeTesterClu() { } 

    // Global variables.

    static Comm world;
    static int size;
    static int rank;
    static long x;
    static boolean r;
    static long t1, t2, t3;
    
    // Exported operations.
    
    /**
     * Main program.
     */
    public static void main (String[] args) throws Exception {
        t1 = System.currentTimeMillis();
        Comm.init(args);
        world = Comm.world();
        size = world.size();
        rank = world.rank();
        x = Long.parseLong (args[rank]);
        t2 = System.currentTimeMillis();
        r = isPrime (x);
        t3 = System.currentTimeMillis();
        
        System.out.println ("rank = "+rank+" call start = "+(t2-t1)+" msec");
        System.out.println ("rank = "+rank+" call: " + (x) + " = " + (r));
        System.out.println ("rank = "+rank+" call finish = "+(t3-t1)+" msec");
    }

    // Hidden operations.

    /**
     * Subroutine for one computation. Returns true if x is prime, false
     * otherwise.
     */
    private static boolean isPrime (long x) {
        if (x % 2 == 0) return false;
        long p = 3;
        long psqr = p*p;
        while (psqr <= x) {
            if (x % p == 0) return false;
            p += 2;
            psqr = p*p;
        }
        return true;
    }
    
}
