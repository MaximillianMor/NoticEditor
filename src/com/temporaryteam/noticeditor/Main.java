package com.temporaryteam.noticeditor;

import com.temporaryteam.noticeditor.controller.NoticeController;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(final Stage primaryStage) {
//		this.primaryStage = primaryStage;
		primaryStage.setTitle("NoticEditor");
		initRootLayout(primaryStage);
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
