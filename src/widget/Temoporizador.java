package widget;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class Temoporizador extends GridPane {
	
	public BooleanProperty fin;
	
	@FXML
	protected Label lbl1;
	@FXML
	protected Label lbl2;
	@FXML
	protected Label lbl3;
	@FXML
	protected Label lbl4;

	public Temoporizador() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/widget/fxml/temporizador.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		fin = new SimpleBooleanProperty(false);
		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}
	
	public void tiempo(int min) {
		if (min > 0 && min < 100) {
			fin.set(false);
			lbl1.setText(Integer.toString(min / 10));
			lbl2.setText(Integer.toString(min % 10));
			AtomicInteger restante = new AtomicInteger(min * 60);
			new Timer().scheduleAtFixedRate(new TimerTask() {
				@Override
				public void run() {
					Platform.runLater(() -> {
						int res = restante.getAndDecrement();
						int minutos = res / 60;
						int segundos = res % 60;
						lbl1.setText(Integer.toString(minutos / 10));
						lbl2.setText(Integer.toString(minutos % 10));
						lbl3.setText(Integer.toString(segundos / 10));
						lbl4.setText(Integer.toString(segundos % 10));
						if (res <= 0) {
							fin.set(true);
							cancel();
						}
					});
				}
			}, 0, 1000);
			
		}
	}

	public BooleanProperty getFin() {
		return fin;
	}
	
	
}
