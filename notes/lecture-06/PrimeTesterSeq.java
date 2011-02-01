//******************************************************************************
//
// File:    PrimeTesterSeq.java
// Package: ---
// Unit:    Class PrimeTesterSeq
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
 * Class PrimeTesterSeq is the sequential version of the first program illustrating
 * parallel computing.
 * <P>
 * Usage: java PrimeTesterSeq <I>x1</I> [ <I>x2</I> . . . ]
 * <BR><I>x1</I> = Number to be tested for primality
 *
 * @author  Alan Kaminsky
 * @version 05-Aug-2008
 * @author  Matthew Fluet
 * @version 02-Dec-2010
 */
public class PrimeTesterSeq {
    
    // Prevent construction.
    
    private PrimeTesterSeq() { } 

    // Global variables.
    
    static int n;
    static long[] x;
    static boolean[] r;
    static long t1, t2[], t3[], t4;
    
    // Exported operations.
    
    /**
     * Main program.
     */
    public static void main (String[] args) throws Exception {
        Comm.init(args);
        t1 = System.currentTimeMillis();
        n = args.length;
        x = new long [n];
        r = new boolean [n];
        for (int i = 0; i < n; ++ i) x[i] = Long.parseLong (args[i]);
        t2 = new long [n];
        t3 = new long [n];
        for (int i = 0; i < n; ++ i) {
            t2[i] = System.currentTimeMillis();
            r[i] = isPrime (x[i]);
            t3[i] = System.currentTimeMillis();
        }
        t4 = System.currentTimeMillis();
        for (int i = 0; i < n; ++ i) {
            System.out.println ("i = "+i+" call start = "+(t2[i]-t1)+" msec");
            System.out.println ("i = "+i+" call: " + (x[i]) + " = " + (r[i]));
            System.out.println ("i = "+i+" call finish = "+(t3[i]-t1)+" msec");
        }
        System.out.println ("Program running time = "+(t4-t1)+" msec");
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
