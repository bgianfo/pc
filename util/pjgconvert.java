//******************************************************************************
//
// File:   pjgconvert.java
// Author: Brian Gianforcaro
//
//******************************************************************************

import edu.rit.image.PJGImage;

import java.io.File;
import java.io.FileInputStream;
import java.io.BufferedInputStream;

import javax.imageio.ImageIO;

/**
 * Class pjgconvert is a program that converts from PJG files to PNG/GIF/JPEG.
 * <P>
 * Usage: java pjgconvert <I><in-file></I> <I><out-file></I>
 * <BR><I><in-file></I> = PJG input image file name
 * <BR><I><out-file></I> = PNG/GIF/JPEG output image file name
 * <P>
 *
 * @author  Brian Gianforcaro
 * @version 25-Jan-2010
 */

public class pjgconvert {

    public static void usage( ) {
        System.err.println("\nUsage: java pjgconvert <in-file> <out-file>");
        System.err.println("<in-file> = PJG input image file name");
        System.err.println("<out-file> = PNG/GIF/JPEG output image file name\n");
        System.err.println("Note: gif images seem to require more memory than png/jpeg to convert.");
        System.err.println("      Try passing -Xmx2GB (or less) to java in order to increase the max heap size");
        System.exit(1);
    }

    public static void checkInExtension( String file ) {
         String extension = file.substring( file.lastIndexOf(".")+1 );
         extension = extension.toLowerCase().trim();

         if ( !extension.equals( "pjg" ) ) {
            System.err.println("Error: in-file \"" + file + "\" is not of type pjg");
            System.exit(1);
         }
    }

    public static String checkOutExtension( String file ) {
         String extension = file.substring( file.lastIndexOf(".")+1 );
         extension = extension.toLowerCase().trim();

         if ( (!extension.equals( "gif" )) && (!extension.equals( "png" )) &&
              (!extension.equals( "jpg" )) && (!extension.equals( "jpeg" ))) {
            System.err.println("Error: out-file \"" + file + "\" is not of type png, gif, jpeg" );
            System.exit(1);
         }

         return extension;
    }

    public  static void main( String args[] ) {
        if ( args.length < 2 ) {
            usage();
        }

        // Obtain input file and check file name
        String inputFile = args[0];
        checkInExtension( inputFile );

        // Obtain output file and check file name and obtain output file type.
        String outputFile = args[1];
        String fileType = checkOutExtension( outputFile );

        try { 

            BufferedInputStream iFstream = new BufferedInputStream( new FileInputStream( inputFile ) );
            PJGImage inImage = PJGImage.readFromStream( iFstream );
            ImageIO.write( inImage.getBufferedImage(), fileType, new File( outputFile ) );
            iFstream.close();

        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }
}
