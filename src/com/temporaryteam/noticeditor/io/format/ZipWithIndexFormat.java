package com.temporaryteam.noticeditor.io.format;

import com.temporaryteam.noticeditor.io.FileIO;
import com.temporaryteam.noticeditor.io.IO;
import com.temporaryteam.noticeditor.io.IOUtil;
import com.temporaryteam.noticeditor.io.ZipFileIO;
import com.temporaryteam.noticeditor.model.NoticeItem;
import com.temporaryteam.noticeditor.model.NoticeStatusList;
import com.temporaryteam.noticeditor.model.NoticeTree;
import com.temporaryteam.noticeditor.model.NoticeTreeItem;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.TreeItem;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Document format that stores to zip archive with index.json
 * 
 * @author aNNiMON, Max Balushkin
 */
public class ZipWithIndexFormat implements Format {
	
	private static final String INDEX_JSON = "index.json";
	private static final String BRANCH_PREFIX = "branch_";
	private static final String NOTE_PREFIX = "note_";
	
	private Set<String> paths;
	
	@Override
	public NoticeTree load(IO aSource) throws FormatException, IOException {
		try {
			FileIO file = (FileIO) aSource;
			ZipFileIO zip = new ZipFileIO(file.getFile());

			zip.setOperationPath(INDEX_JSON);
			String indexContent = zip.read();
			if (indexContent == null || indexContent.isEmpty()) {
				throw new FormatException("Invalid file format. Can not find index.json");
			}
			JSONObject index = new JSONObject(indexContent);
			
			if (index.has(JsonFields.KEY_STATUSINFO)) {
				JSONArray statusList = index.getJSONArray(JsonFields.KEY_STATUSINFO);
				if (statusList.length() > 0) {
					NoticeStatusList.clear();
				}
				for (int i = 0; i < statusList.length(); i++) {
					JSONObject obj = (JSONObject) statusList.get(i);
					String name = obj.getString(JsonFields.KEY_STATUSNAME);
					int code = obj.getInt(JsonFields.KEY_STATUSCODE);
					NoticeStatusList.add(name, code);
				}
			} else {
				NoticeStatusList.restore();
			}
			
			NoticeTreeItem root = loadFromZip(zip, "", index);
			return new NoticeTree(root);
		} catch (ClassCastException | JSONException | ZipException ex) {
			throw new FormatException(ex.getCause());
		} 
	}

	private NoticeTreeItem loadFromZip(ZipFileIO aZip, String aPath, JSONObject aIndex) 
		throws IOException, JSONException, ZipException {
		
		final String title = aIndex.getString(JsonFields.KEY_TITLE);
		final String filename = aIndex.getString(JsonFields.KEY_FILENAME);
		final int status = aIndex.optInt(JsonFields.KEY_STATUS, NoticeItem.STATUS_NORMAL);
		final String dirPrefix = aIndex.has(JsonFields.KEY_CHILDREN) ? BRANCH_PREFIX : NOTE_PREFIX;
		
		final String newDir = aPath + dirPrefix + filename + "/";
		if (aIndex.has(JsonFields.KEY_CHILDREN)) {
			JSONArray children = aIndex.getJSONArray(JsonFields.KEY_CHILDREN);
			NoticeTreeItem branch = new NoticeTreeItem(title);
			for (int i = 0; i < children.length(); i++) {
				branch.addChild(loadFromZip(aZip, newDir, children.getJSONObject(i)));
			}
			return branch;
		} else {
			final String mdPath = newDir + filename + ".md";
			aZip.setOperationPath(mdPath);
			return new NoticeTreeItem(title, aZip.read(), status);
		}
	}
	
	@Override
	public void save(IO aDestination, NoticeTree aTree) throws FormatException, IOException {
		try {
			FileIO file = (FileIO) aDestination;

			paths = new HashSet<>();
			ZipFileIO zip = new ZipFileIO(file.getFile());
			ZipParameters parameters = new ZipParameters();
			
			parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
			parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
			parameters.setSourceExternalStream(true);
			zip.setParameters(parameters);

			JSONObject index = new JSONObject();
			index.put(JsonFields.KEY_STATUSINFO, NoticeStatusList.asObservable());
			
			saveToZip(zip, "", aTree.getRoot(), index);
			zip.setOperationPath(INDEX_JSON);
			zip.write(index.toString());
		} catch (ClassCastException | JSONException | ZipException ex) {
			throw new FormatException(ex.getCause());
		} 
	}
	
	/**
	 * Saves notice item to zip file
	 * 
	 * @param aZip Zip archive
	 * @param aPath Path in archive
	 * @param aItem Notice item
	 * @param index Index. <b>IMPORTANT! It is mutating during method execution!<b>
	 */
	private void saveToZip(ZipFileIO aZip, String aPath, NoticeTreeItem aItem, JSONObject index) 
		throws IOException, JSONException, ZipException {
		
		String title = aItem.getTitle();
		String dirPrefix = aItem.isBranch() ? BRANCH_PREFIX : NOTE_PREFIX;
		String filename = IOUtil.sanitizeFilename(title);
		
		String path = aPath + dirPrefix + filename;
		// solve collision
		if (paths.contains(path)) {
			String newFilename = filename;
			for (int i = 0; paths.contains(path); i++) {
				newFilename = String.format("%s_(%d)", filename, i);
				path = aPath + dirPrefix + newFilename;
			}
			filename = newFilename;
		}
		paths.add(path);
		
		index.put(JsonFields.KEY_TITLE, title);
		index.put(JsonFields.KEY_FILENAME, filename);
		
		if (aItem.isBranch()) {
			// ../branch_filename
			ArrayList list = new ArrayList();
			for (TreeItem<NoticeItem> object : aItem.getInternalChildren()) {
				NoticeTreeItem child = (NoticeTreeItem) object;
				JSONObject indexEntry = new JSONObject();
				saveToZip(aZip, path + "/", child, indexEntry);
				list.add(indexEntry);
			}
			index.put(JsonFields.KEY_CHILDREN, new JSONArray(list));
		} else {
			// ../note_filename/filename.md
			index.put(JsonFields.KEY_STATUS, aItem.getStatus());
			aZip.setOperationPath(path + "/" + filename +".md");
			aZip.write(aItem.getContent());
		}
	}

}
