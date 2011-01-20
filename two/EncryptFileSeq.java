//******************************************************************************
//
// Author: Brian Gianforcaro (bjg1955@cs.rit.edu)
// File:   EncryptFilSeq.java
//
//******************************************************************************

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedInputStream;


import edu.rit.crypto.blockcipher.AES256Cipher;
import edu.rit.util.Hex;
import edu.rit.pj.Comm;

/**
 * Class EncryptFileSeq encrypts a input file using the AES cipher. 
 * <P>
 * Usage: java Encrypt <I>key</I> <I>inFileName</I> <I>outFileName</I>
 * <BR><I>key</I> = Encryption key (256-bit hexadecimal number)
 * <BR><I>inFileName</I> = input file name
 * <BR><I>outFileName</I> = output file name 
 *
 * @author  Brian Gianforcaro (bjg1955@cs.rit.edu)
 * @version 13-Sep-2010
 */

public class EncryptFileSeq {

    /** Argument Constants */
    static int KEY_ARG = 0;
    static int INPUT_ARG = 1;
    static int OUTPUT_ARG = 2;
    static int BLOCK_SIZE = 16;

    /** Working data for enciphering */
    static AES256Cipher cipher;
    static byte[] key;
    static BufferedInputStream iFile;
    static BufferedOutputStream oFile;


    /** Prevent construction */
    private EncryptFileSeq() { }

    /**
     * Print a usage message and exit.
     */
    private static void usage() {
        System.err.println("Usage: java EncryptFileSeq <key> <inFileName> <outFileName>");
        System.err.println("<key> = Encryption key (256-bit hexadecimal number)");
        System.err.println("<inFileName> = input file name");
        System.err.println("<outFileName> = output file name");
        System.exit(0);
    }

    /**
     * Properly pad the end of a data block with zero's
     * @param block - the data block to pad
     * @param offset - the offset in the block to start the pad.
     */
    private static void pad( byte[] block, int offset ) {
        for ( int i = offset; i < block.length; i++ ) {
            block[i] = (byte)0;
        }
    }

    /**
     * EncryptFileSeq main program.
     */
    public static void main (String[] args) throws Exception {

        // Start timer
        long t1 = System.currentTimeMillis();

        // Initialize my PJ's
        Comm.init( args );

        // Parse command line arguments.
        if ( args.length != 3 ) {
          usage();
        }

        // Unpack arguments
        key = Hex.toByteArray( args[KEY_ARG] );
        iFile = new BufferedInputStream( new FileInputStream( args[INPUT_ARG] ) ); 
        oFile = new BufferedOutputStream( new FileOutputStream( args[OUTPUT_ARG] ) );

        // Initialize the cipher and working blocks
        cipher = new AES256Cipher( key );
        byte[] plainText = new byte[BLOCK_SIZE];
        byte[] cipherText = new byte[BLOCK_SIZE];

        // Read and encrypt the entire file
        while ( true ) {

            int read = iFile.read( plainText );

            // If we didn't read an entire block 
            // we have to pad the rest of the data.
            if ( read != BLOCK_SIZE ) {
                if ( read == -1 ) {
                    break;
                }
                pad( plainText, read );
            }

            cipher.encrypt( plainText, cipherText );

            oFile.write( cipherText );

            // This marks EOF
            if ( read != BLOCK_SIZE ) {
                break;
            }
        }

        oFile.close();
        iFile.close();

        // Stop timing.
        System.out.println((System.currentTimeMillis()-t1) + " msec");
    }

}
