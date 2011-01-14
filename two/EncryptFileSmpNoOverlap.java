//******************************************************************************
//
// File:    EncryptFilSmpNoOverlap.java
//******************************************************************************

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedInputStream;


import edu.rit.crypto.blockcipher.AES256CipherSmp;
import edu.rit.util.Hex;
import edu.rit.pj.Comm;

/**
 * Class EncryptFileSmpNoOverlap encryptes inputfile using the AES cipher. 
 * <P>
 * Usage: java Encrypt <I>key</I> <I>inFileName</I> <I>outFileName</I> <I>chunkSize</I>
 * <BR><I>key</I> = Encryption key (256-bit hexadecimal number)
 * <BR><I>inFileName</I> = input file name
 * <BR><I>outFileName</I> = output file name 
 * <BR><I>chunkSize</I> = number of 16-byte blocks in a chunk
 * @author  Brian Gianforcaro (bjg1955@cs.rit.edu)
 * @version 13-Sep-2010
 */

public class EncryptFileSmpNoOverlap {

    /** Argument Constants */
    static int KEY_ARG = 0;
    static int INPUT_ARG = 1;
    static int OUTPUT_ARG = 2;
    static int CHUNK_ARG = 3;

    /** Working data for enciphering */
    static AES256CipherSmp cipher;
    static byte[] key;
    static int chunkSize;
    static BufferedInputStream iFile;
    static BufferedOutputStream oFile;
    static int blockSize = 16;



    /** Prevent construction */
    private EncryptFileSmpNoOverlap() { }

    /**
     * Print a usage message and exit.
     */
    private static void usage() {
        System.err.println("Usage: java EncryptFileSmpNoOverlap <key> <inFileName> <outFileName> <chunkSize>");
        System.err.println("<key> = Encryption key (256-bit hexadecimal number)");
        System.err.println("<inFileName> = input file name");
        System.err.println("<outFileName> = output file name");
        System.err.println("<chunkSize> = number of 16-byte blocks in a chunk");
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
     * EncryptFileSmpNoOverlap main program.
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
        chunkSize = Integer.parseInt( args[CHUNK_ARG] );
        iFile = new BufferedInputStream( new FileInputStream( args[INPUT_ARG] ) ); 
        oFile = new BufferedOutputStream( new FileOutputStream( args[OUTPUT_ARG] ) );

        // Initialize the cipher and working blocks
        if ( key.length != cipher.getKeyLength() ) {
            System.out.println("Error: Key length is not long enough");
            usage();
        }
        
        byte[] plainTextChunk = new byte[blockSize*chunkSize];
        byte[] cipherTextChunk = new byte[blockSize*chunkSize];

        // Compute all rows and columns.
        new ParallelTeam().execute( new ParallelRegion() {
            public void run() throws Exception {
                while ( true ) {

                    int read = iFile.read( plainTextChunk );

                    // If we didn't read an entire block 
                    // we have to pad the rest of the data.
                    if ( read != blockSize*chunkSize ) {
                        if ( read == -1 ) {
                            break;
                        }
                        pad( plainTextChunk, read );
                    }

            
                    execute( 0, read/blockSize, new IntegerForLoop() {

                        byte[] plainText;
                        byte[] cipherTextli;
                        AES256CipherSmp cipher;

                        /** Initilalize the per thread working blocks and the cipher */
                        public void start() {
                            plainText = new byte[blockSize];
                            cipherTextli = new byte[blockSize];
                            cipher = new AES256CipherSmp( key );
                        }

                        public void run( int first, int last ) {

                            for ( int chunk = first; chunk <= last; chunk++ ) {

                                System.array.copy( plainTextChunk, chunk*blockSize,
                                                   plainText, 0, blockSize );

                                cipher.encrypt( plainText, cipherText );

                                System.array.copy( cipherText, 0,
                                                   cipherTextChunk, chunk*blockSize, blockSize );
                            }
                        }
                    });

                    oFile.write( cipherTextChunk );

                    // This marks EOF
                    if ( read != blockSize*chunkSize ) {
                        break;
                    }
                }
            }
        });

        oFile.close();
        iFile.close();

        // Stop timing.
        System.out.println((System.currentTimeMillis()-t1) + " msec");
    }
}
