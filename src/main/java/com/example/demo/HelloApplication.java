package com.example.demo;

import com.responsive.BootstrapColumn;
import com.responsive.BootstrapPane;
import com.responsive.BootstrapRow;
import com.responsive.Breakpoint;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;


public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        int maxWidth; //max screen width
        int maxHeight; //max screen height

        BootstrapPane root = makeView();
        root.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());

        ScrollPane scrollPane = new ScrollPane(root);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        stage.setTitle("Responsive");

        stage.setScene(new Scene(scrollPane, 640, 480));

        //get screen max width and height
        maxWidth = (int) Screen.getPrimary().getBounds().getWidth();
        maxHeight = (int) Screen.getPrimary().getBounds().getHeight();

        //set minimum window with based on screen max width and height
        stage.setMinWidth(maxWidth*0.30);
        stage.setMinHeight(maxHeight*0.50);

        //execute show window
        stage.show();
    }

    private BootstrapPane makeView() {

        BootstrapPane bootstrapPane = new BootstrapPane();
        bootstrapPane.setPadding(new Insets(15));
        bootstrapPane.setVgap(25);
        bootstrapPane.setHgap(25);

        BootstrapRow row = new BootstrapRow();
        BootstrapRow row2 = new BootstrapRow();
        BootstrapRow row3 = new BootstrapRow();
        BootstrapRow row4 = new BootstrapRow();

        row.addColumn(createColumn(createTitleWidget("Kolom Judul"),1));
        row2.addColumn(createColumn(createWidget("Panel Widget 1"),1));
        row3.addColumn(createColumn(createWidget1("Panel Widget 2"),2));
        row3.addColumn(createColumn(createWidget("Panel Widget 2"),3));
        row3.addColumn(createColumn(createWidget("Panel Widget 2"),3));
        row4.addColumn(createColumn(createWidget(FXMLReader("view.fxml")), 1));
        row4.addColumn(createColumn(createWidget(FXMLReader("layout.fxml")), 1));

        bootstrapPane.addRow(row);
        bootstrapPane.addRow(row2);
        bootstrapPane.addRow(row3);
        bootstrapPane.addRow(row4);
        return bootstrapPane;
    }

    private Node FXMLReader(String fxmlname){
            try{
                AnchorPane widget = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource(fxmlname)));
                widget.isResizable();
                return widget;
            }
            catch(Exception e)    {
                e.printStackTrace();
                System.out.println(e);
            }
            return null;
        }


    private BootstrapColumn createColumn(Node widget, int numOfCol) {
        BootstrapColumn column = new BootstrapColumn(widget);
            if (numOfCol < 1||numOfCol>3) {
                throw new IllegalArgumentException("Column breakpoint error, must be 1,2 or 3");
            }else {
                switch (numOfCol) {
                    case 1:
                        column.setBreakpointColumnWidth(Breakpoint.XSMALL, 12);
                        break;
                    case 2:
                        column.setBreakpointColumnWidth(Breakpoint.XSMALL, 12);
                        column.setBreakpointColumnWidth(Breakpoint.SMALL, 6);
                        break;
                    case 3:
                        column.setBreakpointColumnWidth(Breakpoint.XSMALL, 12);
                        column.setBreakpointColumnWidth(Breakpoint.SMALL, 6);
                        column.setBreakpointColumnWidth(Breakpoint.LARGE, 3);
                        break;
                }
            }
        return column;
    }

    private Node createTitleWidget(String title) {
        //declare item inside the widget

        VBox widget = new VBox();
        CornerRadii cornerRadii = new CornerRadii(30);
        BackgroundFill bgFill = new BackgroundFill(Color.AQUAMARINE,cornerRadii,Insets.EMPTY);
        Background bg = new Background(bgFill);
        Label label = new Label(title);

        label.getStyleClass().add("app-title");
        widget.getStyleClass().add("widget");
        widget.setBackground(bg);
        widget.getChildren().add(label);

        return widget;
    }

    private Node createWidget(String title) {
        //declare item inside the widget

        VBox widget = new VBox();
        widget.getStyleClass().add("widget");
        widget.getChildren().add(new Label(title));
        widget.getChildren().add(new Separator(Orientation.HORIZONTAL));

        widget.getChildren().add(createItemSet());

        return widget;
    }

    private Node createWidget(Node node) {
        //declare item inside the widget

        VBox widget = new VBox();
        widget.getStyleClass().add("widget");
        widget.setAlignment(Pos.TOP_CENTER);
        widget.getChildren().add(node);
        widget.getChildren().add(new Separator(Orientation.HORIZONTAL));

        return widget;
    }

    private Node createWidget1(String title) {
        VBox widget = new VBox();
        widget.getStyleClass().add("widget");
        widget.getChildren().add(new Label(title));
        widget.getChildren().add(new Separator(Orientation.HORIZONTAL));

        widget.getChildren().add(createItemSet2());

        return widget;
    }

    private Node createItemSet() {
        VBox item = new VBox();
        VBox.setVgrow(item, Priority.ALWAYS);
        Label label = new Label("Hello World");
        item.getChildren().add(label);

        VBox container = new VBox();
        container.setSpacing(15);
        container.setAlignment(Pos.CENTER);
        Button helloButton = new Button("Hello");
        container.getChildren().add(helloButton);

        item.getChildren().addAll(container);

        return item;
    }

    private Node createItemSet2() {
        VBox item = new VBox();
        VBox.setVgrow(item, Priority.ALWAYS);
        Label label = new Label("Hello world2");
        item.getChildren().add(label);

        VBox container = new VBox();
        container.setSpacing(15);
        container.setMinWidth(80);
        container.setAlignment(Pos.CENTER);
        Button helloButton = new Button("Button item2");
        Label label2 = new Label(("Ini lagi"));
        container.getChildren().add(helloButton);
        container.getChildren().add(label2);

        item.getChildren().addAll(container);

        return item;
    }

    public static void main(String[] args) {
        launch();
    }
}