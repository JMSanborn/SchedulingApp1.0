package Main;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.util.Timer;
import java.util.TimerTask;
import Controller.mainController;
import Model.ReaderThread;

public class main extends Application {

    private final PipedInputStream pipeIn = new PipedInputStream();
    private final PipedInputStream pipeIn2 = new PipedInputStream();
    Thread errorThrower;
    private Thread reader;
    private Thread reader2;
    boolean quit;
    private TextArea txtArea;

    /** Begins Application */

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/View/main.fxml"));
        primaryStage.setTitle("CoffeePot");
        primaryStage.setScene(new Scene(root, 1000, 500));
        primaryStage.show();

        txtArea = mainController.staticTxtArea;

            //Thread execution for reading output stream
        executeReaderThreads();

            //Thread closing on stag close event
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {

                closeThread();
                Platform.exit();
                System.exit(0);
            }
        });
    }

    //method to handle thread closing on stage closing
    synchronized void closeThread()
    {
        System.out.println("Message: Stage is closed.");
        this.quit = true;
        notifyAll();
        try { this.reader.join(1000L); this.pipeIn.close(); } catch (Exception e) {
        }try { this.reader2.join(1000L); this.pipeIn2.close(); } catch (Exception e) {
    }System.exit(0);
    }


    /** Main function call */
    public static void main(String[] args) {
        launch(args);
    }
    public void executeReaderThreads() {
        try {
            PipedOutputStream pout = new PipedOutputStream(this.pipeIn);
            System.setOut(new PrintStream(pout, true));
        } catch (IOException io) {
        } catch (SecurityException se) {
        }

        try {
            PipedOutputStream pout2 = new PipedOutputStream(this.pipeIn2);
            System.setErr(new PrintStream(pout2, true));
        } catch (IOException io) {
        } catch (SecurityException se) {
        }

        ReaderThread obj = new ReaderThread(pipeIn, pipeIn2, errorThrower, reader, reader2, quit, txtArea);
    }
}
