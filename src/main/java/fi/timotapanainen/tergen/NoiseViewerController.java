package fi.timotapanainen.tergen;

import fi.timotapanainen.noise.NoiseGenerator;
import fi.timotapanainen.noise.SimplexNoisePlane;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created with IntelliJ IDEA.
 * User: tapantim
 * Date: 21.5.2013
 * Time: 20:43
 * To change this template use File | Settings | File Templates.
 */
public class NoiseViewerController implements Initializable {

    public TextField mapWidth;
    public TextField mapHeight;
    public TextField persistence;
    public TextField octaves;
    public TextField startingFrequency;
    public ComboBox algorithmCombo;
    public ToggleButton btnColor;
    public ToggleButton btnGrayscale;
    //public ToggleButton btnPixel;
    //public ToggleButton btnHexagon;
    public ToggleGroup colorGroup;
    public ToggleGroup shapeGroup;
    //public TableView colorWeightTable;
    public Slider waterCoverage;

    ObservableList<String> octavesItems = FXCollections.observableArrayList();
    List<double[][]> octaveNoiseMaps = new ArrayList<double[][]>();

    ObservableList<String> algorithmItems = FXCollections.observableArrayList("Simplex", "Perlin");

    public ScrollPane contentPane;
    public ListView generatedOctavesView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        generatedOctavesView.setItems(octavesItems);
        generatedOctavesView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        generatedOctavesView.getSelectionModel().getSelectedItems().addListener(new ListChangeListener() {
            @Override
            public void onChanged(Change change) {
                updateContentArea();
            }
        });
        colorGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle toggle, Toggle toggle2) {
                updateContentArea();
            }
        });

 /*       shapeGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle toggle, Toggle toggle2) {
                updateContentArea();
            }
        });
 */
        algorithmCombo.setItems(algorithmItems);
        algorithmCombo.getSelectionModel().selectFirst();

        waterCoverage.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                updateContentArea();
            }
        });

    }

    private void updateContentArea() {
        double[][] noise = sumNoises();
        if (noise == null)
            return;

        ImageBuilder imgBuilder = new ImageBuilder();
        imgBuilder.setValues(noise);
        imgBuilder.setWaterCoveragePercent(waterCoverage.getValue());
        if (btnColor.isSelected())
            imgBuilder.setGrayScale(false);
        ImageView imgView = new ImageView();
        imgView.setImage(imgBuilder.build());
        contentPane.setContent(imgView);
    }

    private double[][] sumNoises() {
        List<Integer> indices = generatedOctavesView.getSelectionModel().getSelectedIndices();
        if (indices.isEmpty())
            return null;
        System.out.println(indices);
        double[][] totalNoise = new double[getMapWidth()][getMapHeight()];
        for (int index : indices) {
            double[][] noise = octaveNoiseMaps.get(index);
            MathUtil.add(noise, totalNoise);
        }
        MathUtil.scaleToRange(totalNoise, 0.0, 1.0);
        return totalNoise;
    }

    public void generateOctaves(ActionEvent evt) {
        List<String> octaveNames = new ArrayList<String>();
        octaveNoiseMaps.clear();
        int octavesToGen = getOctaves();
        double startingFrequencyValue = Double.valueOf(startingFrequency.getText());
        double persistenceValue = Double.valueOf(persistence.getText());

        double curPersistence = 1.0;
        double curFrequency = startingFrequencyValue;
        int algorithmIndex = algorithmCombo.getSelectionModel().getSelectedIndex();
        String algorithmName = algorithmIndex == 0 ? "Simplex" : "Perlin";

        for (int octave = 1; octave <= octavesToGen; ++octave) {
            double[][] noise = new double[getMapWidth()][getMapHeight()];
            SimplexNoisePlane plane = new SimplexNoisePlane(curFrequency, curPersistence);
            NoiseGenerator generator = new NoiseGenerator();
            generator.generate(noise, plane);
            octaveNames.add(algorithmName + " F: " + curFrequency + ", P: " + curPersistence);
            octaveNoiseMaps.add(noise);
            // prepare values for next iteration
            curPersistence *= persistenceValue;
            curFrequency *= 2;
        }
        octavesItems.setAll(octaveNames);
        generatedOctavesView.getSelectionModel().selectAll();
    }

    private int getOctaves() {
        return Integer.valueOf(octaves.getText());
    }

    private int getMapWidth() {
        return Integer.valueOf(mapWidth.getText());
    }

    private int getMapHeight() {
        return Integer.valueOf(mapHeight.getText());
    }
}


