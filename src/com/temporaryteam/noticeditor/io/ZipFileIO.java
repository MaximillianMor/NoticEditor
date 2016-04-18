package com.temporaryteam.noticeditor.io;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.ZipParameters;

/**
 * Container for zip file datasource
 * 
 * @author Max Balushkin
 */
public class ZipFileIO extends FileIO {
	
	ZipFile zipFile;
	ZipParameters parameters;
	String operationPath;
	
	public ZipFileIO(File aFile) throws ZipException {
		super(aFile);
		zipFile = new ZipFile(aFile);
	}
	
	/**
	 * Sets parameters of zip compression
	 * @param aParameters Zip compression parameters
	 */
	public void setParameters(ZipParameters aParameters) {
		parameters = aParameters;
	}
	
	/**
	 * Sets path for read/write operations
	 * @param aOperationPath Path in zip archive
	 */
	public void setOperationPath(String aOperationPath) {
		operationPath = aOperationPath;
	}

	@Override
	public void write(String content) throws IOException {
		parameters.setFileNameInZip(operationPath);
		try (ByteArrayInputStream stream = new ByteArrayInputStream(content.getBytes("UTF-8"))) {
			zipFile.addStream(stream, parameters);
		} catch (ZipException ex) {
			throw new IOException(ex.getMessage(), ex.getCause());
		}
	}

	@Override
	public String read() throws IOException {
		try {
			FileHeader header = zipFile.getFileHeader(operationPath);
			if (header == null) return "";
			return stringFromStream(zipFile.getInputStream(header));
		} catch (ZipException ex) {
			throw new IOException(ex.getMessage(), ex.getCause());
		}
	}
	
}
