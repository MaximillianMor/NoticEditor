package com.temporaryteam.noticeditor.io.format;

import com.temporaryteam.noticeditor.io.IO;
import static com.temporaryteam.noticeditor.io.format.JsonFields.*;
import com.temporaryteam.noticeditor.model.NoticeItem;
import com.temporaryteam.noticeditor.model.NoticeTree;
import com.temporaryteam.noticeditor.model.NoticeTreeItem;
import java.io.IOException;
import javafx.scene.control.TreeItem;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * JSON formatter
 * 
 * @author Max Balushkin
 */
public class JsonFormat implements Format {

	@Override
	public NoticeTree load(IO source) throws FormatException, IOException {
		try {
			String content = source.read();
			JSONObject json = new JSONObject(content);
			
			if (json.has(KEY_STATUSINFO)) {
				// TODO: refactorNoticeList
				//	JSONArray statusList = json.getJSONArray(KEY_STATUSINFO);
				//	if (statusList.length() > 0) {
				//		NoticeStatusList.clear();
				//	}
				//	for (int i = 0; i < statusList.length(); i++) {
				//		JSONObject obj = (JSONObject) statusList.get(i);
				//		String name = obj.getString("name");
				//		int code = obj.getInt("code");
				//		NoticeStatusList.add(name, code);
				//	}
			} else {
				// TODO: setDefaultNoticeList
			}
			
			return new NoticeTree(jsonToTree(json));
		} catch (JSONException ex) {
			throw new FormatException(ex.getMessage(), ex.getCause());
		}
	}
	
	/**
	 * Parses notice tree from JSON document
	 * 
	 * @param json JSON document
	 * @return Notice tree
	 * @throws JSONException 
	 */
	private NoticeTreeItem jsonToTree(JSONObject json) throws JSONException {
		String title = json.getString(KEY_TITLE);
		String content = json.optString(KEY_CONTENT, null);
		int status = json.optInt(KEY_STATUS, NoticeItem.STATUS_NORMAL);
		NoticeTreeItem item = new NoticeTreeItem(title, content, status);
		JSONArray childs = json.getJSONArray(KEY_CHILDREN);
		for (int i = 0; i < childs.length(); i++) {
			NoticeTreeItem child = jsonToTree(childs.getJSONObject(i));
			item.addChild(child);
		}
		return item;
	}
	
	@Override
	public void save(IO destination, NoticeTree tree) throws FormatException, IOException {
		try {
			// if (file.exists()) file.delete() ???
			JSONObject json = treeToJson(tree.getRoot());
			// TODO: status codes
			
			destination.write(json.toString());
		} catch (JSONException ex) {
			throw new FormatException(ex.getMessage(), ex.getCause());
		}
	}
	
	/**
	 * Parses json from notice item
	 * 
	 * @param item Notice item
	 * @return JSON document
	 * @throws JSONException 
	 */
	private JSONObject treeToJson(NoticeTreeItem item) throws JSONException {
		JSONObject json = new JSONObject();
		json.put(KEY_TITLE, item.getTitle());
		
		JSONArray childs = new JSONArray();
		if (item.isBranch()) {
			for (TreeItem<NoticeItem> object : item.getInternalChildren()) {
				NoticeTreeItem child = (NoticeTreeItem) object;
				JSONObject entry = treeToJson(child);
				childs.put(entry);
			}
			json.put(KEY_CHILDREN, childs);
		} else {
			json.put(KEY_STATUS, item.getStatus());
			json.put(KEY_CONTENT, item.getContent());
		}
		json.put(KEY_CHILDREN, childs);
		return json;
	}

}
