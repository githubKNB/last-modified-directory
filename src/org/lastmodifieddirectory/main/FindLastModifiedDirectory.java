/**
 * 
 */
package org.lastmodifieddirectory.main;

import org.lastmodifieddirectory.function.DirectorySeeker;

/**
 * Runner class for a quick check.
 * @author niranjanb
 */
public class FindLastModifiedDirectory {

	/**
	 * @param args[0] Parent directory path
	 * @param args[1] Directory name pattern
	 */
	public static void main(String[] args) {
		
		if(args.length > 0) {
			DirectorySeeker seeker = null;
			
			if(args.length == 2) {
				String parentDirectory = args[0].trim();
				String searchDirNamePattern = args[1].trim();
				System.out.println("Seek directory with name pattern");
				System.out.println("Parent directory : " + parentDirectory);
				System.out.println("Directory name pattern: " + searchDirNamePattern);
				seeker = new DirectorySeeker(parentDirectory, searchDirNamePattern);
			}
			else {				
				String parentDirectory = args[0].trim();
				System.out.println("Seek directory by path only");
				System.out.println("Parent directory : " + parentDirectory);				
				seeker = new DirectorySeeker(parentDirectory);
			}
			
			String lastModifiedDirectory = seeker.getLastModifiedDirectory();
			System.out.println("Last modified directory name is : " + lastModifiedDirectory);
		}
		else {
			System.out.println("Please pass arguments.");
			System.out.println("@param args[0] Parent directory path [REQUIRED]");
			System.out.println("@param args[1] Directory name pattern [OPTIONAL]");
		}

	}
}
