package com.temporaryteam.noticeditor.io;

import com.temporaryteam.noticeditor.io.format.Format;
import com.temporaryteam.noticeditor.io.format.FormatException;
import com.temporaryteam.noticeditor.io.format.FormatService;
import com.temporaryteam.noticeditor.io.importers.FileImporter;
import com.temporaryteam.noticeditor.model.NoticeTree;
import java.io.IOException;

/**
 * Provides common operations with document
 * 
 * @author aNNiMON
 */
public final class DocumentFormat {

	/**
	 * Loads document from datasource
	 * 
	 * @param datasource Source (e.g. file)
	 * @return Loaded notice tree
	 * @throws IOException
	 */
	public static NoticeTree open(IO datasource) throws IOException {
		try {
			final String datasourceName = datasource.getDatasourceName();
			if (datasourceName.toLowerCase().endsWith(".zip")) {
				return FormatService.get("zip").load(datasource);
			}
			return FormatService.get("json").load(datasource);
		} catch (FormatException ex) {
			// return FileImorter.Tree.importFrom(file);
			throw new UnsupportedOperationException();
		}
	}

	/**
	 * Saves notice tree to datasource
	 * 
	 * @param io Destination (e.g. file)
	 * @param tree Notice tree
	 * @param format Document format
	 * @throws FormatException
	 * @throws IOException 
	 */
	public static void save(IO io, NoticeTree tree, Format format) throws FormatException, IOException {
		format.save(io, tree);
	}

}
