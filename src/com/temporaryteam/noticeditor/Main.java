package com.temporaryteam.noticeditor;

import com.temporaryteam.noticeditor.controller.NoticeController;
import com.temporaryteam.noticeditor.view.selector.DirectorySelectorDialog;
import com.temporaryteam.noticeditor.view.selector.FileLoaderDialog;
import com.temporaryteam.noticeditor.view.selector.FileSaverDialog;
import com.temporaryteam.noticeditor.view.selector.SelectorDialogService;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

public class Main extends Application {

	@Override
	public void start(final Stage primaryStage) {
		primaryStage.setTitle("NoticEditor");
		registerSelectors(primaryStage);
		initRootLayout(primaryStage);
	}
	
	private void registerSelectors(final Window primaryStage) {
		SelectorDialogService.register(new DirectorySelectorDialog(primaryStage));
		SelectorDialogService.register(new FileLoaderDialog(primaryStage));
		SelectorDialogService.register(new FileSaverDialog(primaryStage));
		
//		SelectorDialogService.setDirectorySelector(new DirectorySelectorDialog(primaryStage));
//		SelectorDialogService.fileLoader(new FileLoaderDialog(primaryStage));
//		SelectorDialogService.fileSaver(new FileSaverDialog(primaryStage));
	}
	
	/**
	 * Initializes root layout
	 * @param primaryStage
	 */
	public void initRootLayout(final Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Main.fxml"),
					ResourceBundle.getBundle("resources.i18n.Language", Locale.getDefault()));
			Scene scene = new Scene(loader.load());
			primaryStage.setScene(scene);
			NoticeController controller = (NoticeController) loader.getController();
			controller.setPrimaryStage(primaryStage);
			primaryStage.setOnCloseRequest(controller::onExit);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
