/**
 * 
 */
package org.lastmodifieddirectory.function;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author niranjanb
 *
 */
public class DirectorySeeker {

	private String parentDirectory;
	private String directoryNamePattern;
	
	public DirectorySeeker(String parentDirectory){
		
		if(parentDirectory == null || parentDirectory.trim().length() == 0){
			throw new IllegalArgumentException("Parent directory path can not be empty.");
		}
		this.parentDirectory = parentDirectory;
		this.directoryNamePattern = null;
	}
	
	public DirectorySeeker(String parentDirectory, String directoryNamePattern){
		
		if(parentDirectory == null || parentDirectory.trim().length() == 0){
			throw new IllegalArgumentException("Parent directory path can not be empty.");
		}
		
		this.parentDirectory = parentDirectory;
		this.directoryNamePattern = (directoryNamePattern != null) 
				? directoryNamePattern.trim().toLowerCase() : null;
	}
	
	public String getLastModifiedDirectory(){
		String lastModifiedDirectory = this.parentDirectory; 
		File parentDirectory = new File(this.parentDirectory);
		boolean checkNamePattern = ((directoryNamePattern != null) 
				&& directoryNamePattern.trim().length() > 0) ? true : false;
		
		List<File> directoryList = new ArrayList<File>();
		File[] fileList = parentDirectory.listFiles();
		
		for(File file : fileList){
			if(file.isDirectory()){
				if(checkNamePattern && file.getName().toLowerCase().startsWith(directoryNamePattern)){
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
