package dad.javafx.weather.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {
	
	MainController mainController;
	
	@Override
	public void start(Stage primaryStage) throws Exception {

		mainController=new MainController();
		
		Scene scene=new Scene(mainController.getView());
				
		primaryStage.setScene(scene); 
		primaryStage.setTitle("WeatherFX");
		primaryStage.getIcons().add(new Image("images/weather-icon.png"));
			
		primaryStage.setOnCloseRequest(e->{
			mainController.getWeatherTab().salvar();
		});
		
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
