package com.temporaryteam.noticeditor.view;

import com.temporaryteam.noticeditor.model.NoticeTreeItem;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class EditNoticeTreeCell extends TreeCell<String> {

	private TextField noticeName;

	@Override
	public void startEdit() {
		super.startEdit();
		if (noticeName == null) {
			createTextField();
		}
		setText(null);
		setGraphic(noticeName);
		noticeName.selectAll();
	}

	@Override
	public void cancelEdit() {
		super.cancelEdit();
		setText(getItem());
		setGraphic(getIcon());
	}

	@Override
	public void commitEdit(String str) {
		super.commitEdit(str);
		getNoticeTreeItem().setTitle(str);
	}

	@Override
	public void updateItem(String item, boolean empty) {
		super.updateItem(item, empty);
		if (empty) {
			setText(null);
			setGraphic(null);
		} else {
			if (isEditing()) {
				if (noticeName != null) {
					noticeName.setText(getString());
				}
				setText(null);
				setGraphic(noticeName);
			} else {
				setText(getString());
				setGraphic(getIcon());
			}
		}
	}

	private NoticeTreeItem getNoticeTreeItem() {
		return (NoticeTreeItem<String>) getTreeItem();
	}

	private void createTextField() {
		noticeName = new TextField(getString());
		noticeName.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent t) {
				if (t.getCode() == KeyCode.ENTER) {
					commitEdit(noticeName.getText());
				} else if (t.getCode() == KeyCode.ESCAPE) {
					cancelEdit();
				}
			}
		});
	}

	private Node getIcon() {
		if (getNoticeTreeItem().isBranch()) {
			return null;
		} else {
			switch (getNoticeTreeItem().getStatus()) {
				case NoticeTreeItem.STATUS_IMPORTANT:
					return new Circle(5, Color.YELLOW);
				default:
					return new Circle(5, Color.AQUAMARINE);
			}
		}
	}

	/**
	 *
	 * @return selected item or empty string
	 */
	private String getString() {
		return ((getItem() == null) ? "" : getItem());
	}

}
