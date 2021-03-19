package dad.javafx.weather.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;

public class MainController implements Initializable {

    @FXML
    private BorderPane view;

    @FXML
    private Tab tabWeather;

    @FXML
    private Tab tabHistory;

    public static MainController mainController;
    private WeatherTabController weatherTab=new WeatherTabController();
    private HistoryTabController historyTab=new HistoryTabController();
    
    public MainController() {		
    	MainController.mainController = this;
		FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/MainView.fxml"));
		loader.setController(this);
		try {
			loader.load();
		} catch (IOException e) { 
			e.printStackTrace();
		}
	} 
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tabWeather.setContent(weatherTab.getView());
		tabHistory.setContent(historyTab.getView());
	}

	public BorderPane getView() {
		return this.view;
	}

	public WeatherTabController getWeatherTab() {
		return weatherTab;
	}

	public void setWeatherTab(WeatherTabController weatherTab) {
		this.weatherTab = weatherTab;
	}

	public HistoryTabController getHistoryTab() {
		return historyTab;
	}

	public void setHistoryTab(HistoryTabController historyTab) {
		this.historyTab = historyTab;
	}
	
}
