/**
 * 
 */
package org.lastmodifieddirectory.main;

import org.lastmodifieddirectory.function.DirectorySeeker;

/**
 * @author niranjanb
 *
 */
public class FindLastModifiedDirectory {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		if(args.length > 0)
		{
			String parentDirectory = args[0].trim();
			
			DirectorySeeker seeker = new DirectorySeeker(parentDirectory);	
			
			String lastModifiedDirectory = seeker.getLastModifiedDirectory();
			
			System.out.println("Last modified directory name is : " + lastModifiedDirectory);
		}
		else
		{
			System.out.println("Please pass Parent Directory path");
		}

	}
}
