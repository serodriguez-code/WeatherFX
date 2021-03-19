package dad.javafx.weather.ui;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;

import com.google.gson.JsonSyntaxException;

import dad.javafx.weather.Weather;
import dad.javafx.weather.WeatherService;
import dad.javafx.weather.WeatherServiceException;
import dad.javafx.weather.utils.JSONUtils;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class WeatherTabController implements Initializable{ 
	 @FXML
	    private BorderPane view;

	    @FXML
	    private TextField locationTF;

	    @FXML
	    private Button searchButton;

	    @FXML
	    private Button saveButton;

	    @FXML
	    private Label airPressureLabel;

	    @FXML
	    private Label coordinatesLabel;

	    @FXML
	    private Label timeLabel;

	    @FXML
	    private Label temperatureLabel;

	    @FXML
	    private Label windDirectionLabel;

	    @FXML
	    private Label windSpeedLabel;

	    @FXML
	    private Label sunsetLabel;

	    @FXML
	    private Label sunriseLabel;

	    @FXML
	    private Label timeZoneLabel;

	    @FXML
	    private Label humidityLabel;

	    @FXML
	    private Label visibilityLabel;

	    @FXML
	    private Label locationLabel;

	    @FXML
	    private ImageView weatherImage;

	    @FXML
	    private Label weatherImageLabel;

	    public File currentFile=new File(System.getProperty("user.home")+"/weather.WeatherFX");
	    
	    public ObjectProperty<Weather>weather=new SimpleObjectProperty<>();
	    
	    public WeatherTabController() {
			FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/WeatherTabView.fxml"));
			loader.setController(this);
			try {
				loader.load();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void initialize(URL location, ResourceBundle resources) {
			weather.addListener((o,ov,nv)->{
				locationLabel.textProperty().set(nv.getLocation());
				coordinatesLabel.textProperty().set("(lat "+nv.getLatitude()+", lon "+nv.getLongitude());
				timeLabel.textProperty().set(nv.getTime().toString());
				timeZoneLabel.textProperty().set(nv.getTimeZone());
				sunriseLabel.textProperty().set(nv.getSunrise().toString());
				sunsetLabel.textProperty().set(nv.getSunset().toString());
				windSpeedLabel.textProperty().set(String.format("%.2f",nv.getWind().getSpeed())+" km/h");
				windDirectionLabel.textProperty().set(nv.getWind().getDirectionCompass().toString());
				temperatureLabel.textProperty().set(String.format("%.2f",nv.getTemperature())+
						"° (min "+String.format("%.2f",nv.getMinTemperature())+"°, max"+String.format("%.2f",nv.getMaxTemperature())+"°)");
				airPressureLabel.textProperty().set(nv.getAirPressure().toString());
				humidityLabel.textProperty().set(nv.getHumidity().toString());
				visibilityLabel.textProperty().set(String.format("%.2f",nv.getVisibility()));
				weatherImage.imageProperty().set(new Image(nv.getStateIconUrl().toExternalForm()));
				weatherImageLabel.textProperty().set(nv.getState().toString());
			});
			searchButton.disableProperty().bind(locationTF.textProperty().isEmpty());
			saveButton.setDisable(true);
		}


	    @FXML
	    private void onSaveButton(ActionEvent event) {

	    	salvar();
	    	
	    }
	    public void salvar() {
	   		try {
	   			JSONUtils.toJson(currentFile, HistoryTabController.weatherList.get());
			} catch (JsonSyntaxException|IOException e) {
				e.printStackTrace();
			}
	    }

	    @FXML
	    private void onSearchButton(ActionEvent event) {
	    	Stage stage=(Stage)view.getScene().getWindow();
	    	try {
				weather.set(WeatherService.getWeather(locationTF.textProperty().get()));
				locationTF.textProperty().set("");
				saveButton.setDisable(false);
				MainController.mainController.getHistoryTab().setWeather(weather.get());
			} catch (WeatherServiceException e) {
				alertError(stage, "Error", "Error retrieving weather data!", e.getMessage()).showAndWait();
			}
	    }
	    
	    public BorderPane getView() {
	    	return this.view;
	    }
	    
	    /**
	     * Returns an alert of AlertType.Error
	     * @param stage Stage
	     * @param title String
	     * @param headerText String
	     * @param contextText String
	     * @return Alert alert;
	     */
	    public Alert alertError(Stage stage, String title, String headerText, String contextText) {
	    	
	    	Alert alert=new Alert(AlertType.ERROR);
	    	alert.setTitle(title);
	    	alert.setHeaderText(headerText);
	    	alert.setContentText(contextText);
	    	alert.initModality(Modality.APPLICATION_MODAL);
	    	alert.initOwner(stage);
	    	return alert;
	    }

}
