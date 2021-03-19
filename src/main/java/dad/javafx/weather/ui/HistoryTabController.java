package dad.javafx.weather.ui;

import java.io.IOException;
import java.net.URL;
import java.time.ZonedDateTime;
import java.util.ResourceBundle;

import dad.javafx.weather.Weather;
import dad.javafx.weather.WeatherState;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

public class HistoryTabController implements Initializable{
	@FXML
    private VBox view;

    @FXML
    private TableView<Weather> tableHistory;

    @FXML
    private TableColumn<Weather, String> loactionColumn;

    @FXML
    private TableColumn<Weather, ZonedDateTime> timeColumn;

    @FXML
    private TableColumn<Weather, WeatherState> weatherColumn;

    @FXML
    private Button clearButton;

    public static ListProperty<Weather>weatherList=new SimpleListProperty<>(FXCollections.observableArrayList());
    private ObjectProperty<Weather>weather=new SimpleObjectProperty<>();
    
    public HistoryTabController() {
		FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/HistoryTabView.fxml"));
		loader.setController(this);
		try {
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		tableHistory.itemsProperty().bind(weatherList);
		
		loactionColumn.setCellValueFactory(v->new SimpleStringProperty(v.getValue().getLocation()));
		timeColumn.setCellValueFactory(v->new SimpleObjectProperty<ZonedDateTime>(v.getValue().getTime()));
		weatherColumn.setCellValueFactory(v->new SimpleObjectProperty<WeatherState>(v.getValue().getState()));

		weather.addListener((o,ov,nv)->{
			weatherList.add(nv);
		});
		clearButton.disableProperty().bind(Bindings.when(weatherList.sizeProperty().isEqualTo(0)).then(true).otherwise(false));
		
	}
	
    @FXML
    private void onClearButton(ActionEvent event) {

    	weatherList.clear();
    }
    
    public VBox getView() {
    	return this.view;
    }

	public final ObjectProperty<Weather> weatherProperty() {
		return this.weather;
	}
	

	public final Weather getWeather() {
		return this.weatherProperty().get();
	}
	

	public final void setWeather(final Weather weather) {
		this.weatherProperty().set(weather);
	}
	
	
}
