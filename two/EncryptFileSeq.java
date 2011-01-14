//******************************************************************************
//
// File:    EncryptFilSeq.java
//******************************************************************************

import java.io.FileOutputStream;
import java.io.FileInputStream;

import edu.rit.crypto.blockcipher.AES256Cipher;
import edu.rit.util.Hex;
import edu.rit.pj.Comm;

/**
 * Class EncryptFileSeq encryptes inputfile using the AES cipher. 
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
    static FileInputStream iFile;
    static FileOutputStream oFile;


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

        private static void pad( byte[] block, int offset ) {
        for ( int i = offset; i < block.length; i++ ) {
            block[i] = (byte)0;
        }
    }

    /**
     * EncryptFileSeq main program.
     */
    public static void main (String[] args) throws Exception {

        // Initialize my PJ's
        Comm.init( args );

        // Parse command line arguments.
        if ( args.length != 3 ) {
          usage();
        }

        // Unpack arguments
        key = Hex.toByteArray( args[KEY_ARG] );
        cipher = new AES256Cipher( key );

        iFile = new FileInputStream( args[INPUT_ARG] );
        oFile = new FileOutputStream( args[OUTPUT_ARG] );


        byte[] plainText = new byte[BLOCK_SIZE];
        byte[] cipherText = new byte[BLOCK_SIZE];

        while(true) {

            int read = iFile.read( plainText );

            if ( read != BLOCK_SIZE ) {
                if ( read != -1 ) {
                    break;
                }
                pad( plainText, read );
            }

            cipher.encrypt( plainText, cipherText );

            oFile.write( cipherText );

            if ( read != BLOCK_SIZE ) {
                break;
            }
        }

        oFile.close();
        iFile.close();
    }

}
