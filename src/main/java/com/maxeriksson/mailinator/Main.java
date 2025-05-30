package com.maxeriksson.mailinator;

import static com.maxeriksson.mailinator.formatter.ScanCompanyList.processCSV;
import static com.maxeriksson.mailinator.formatter.ScanCompanyList.csvReaderDemo;

/**
 * Hello world!
 *
 */
public class Main
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        csvReaderDemo();
        processCSV();
    }
}
