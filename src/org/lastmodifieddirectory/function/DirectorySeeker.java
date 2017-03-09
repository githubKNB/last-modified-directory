/**
 * 
 */
package org.lastmodifieddirectory.function;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Directory Seeker
 * Class containing seeker functionality.
 * 
 * @author niranjanb
 */
public class DirectorySeeker {

	private String parentDirPath;
	private String searchDirNamePattern;
	
	/**
	 * Used to create a directory seeker containing search directory path. 
	 */
	public DirectorySeeker(String parentDirPath){
		
		if(parentDirPath == null || parentDirPath.trim().length() == 0){
			throw new IllegalArgumentException("Parent directory path can not be empty.");
		}
		this.parentDirPath = parentDirPath;
		this.searchDirNamePattern = null;
	}
	
	/**
	 * Used to create a directory seeker that filter directories by a name pattern inside parent
	 * directory path.
	 */
	public DirectorySeeker(String parentDirectory, String searchDirNamePattern){
		
		if(parentDirectory == null || parentDirectory.trim().length() == 0){
			throw new IllegalArgumentException("Parent directory path can not be empty.");
		}
		
		this.parentDirPath = parentDirectory;
		this.searchDirNamePattern = (searchDirNamePattern != null) 
				? searchDirNamePattern.trim().toLowerCase() : null;
	}
	
	/**
	 * Returns the name of the newest directory in the parent path. 
	 */
	public String getLastModifiedDirectory(){
		String lastModifiedDirectory = this.parentDirPath; 
		File parentDirectory = new File(this.parentDirPath);
		boolean checkNamePattern = ((searchDirNamePattern != null) 
				&& searchDirNamePattern.trim().length() > 0) ? true : false;
		
		List<File> directoryList = new ArrayList<File>();
		File[] fileList = parentDirectory.listFiles();
		
		for(File file : fileList){
			if(file.isDirectory()){
				if(checkNamePattern && file.getName().toLowerCase().startsWith(searchDirNamePattern)){
					directoryList.add(file);
				}
				else{
					directoryList.add(file);
				}
			}
		}
		
		if(directoryList.size() > 0){
			lastModifiedDirectory = seekDirectory(lastModifiedDirectory, Long.MIN_VALUE, directoryList);
		}
		
		return lastModifiedDirectory;
	}
	
	/**
	 * Recursive method seeking the last modified directory
	 */
	private String seekDirectory(String lastModDirectoryName, long lastModifiedTime, List<File> directoryList){
		if(directoryList.size() != 0)
		{
			File nextDirectory = directoryList.remove(directoryList.size()-1);
			long nextLastModifiedTime = nextDirectory.lastModified();
			
			if(lastModifiedTime < nextLastModifiedTime){
				lastModifiedTime = nextLastModifiedTime;
				lastModDirectoryName = nextDirectory.getName();
			}
			
			seekDirectory(lastModDirectoryName, lastModifiedTime, directoryList);
		}
		
		return lastModDirectoryName;
	}
}
