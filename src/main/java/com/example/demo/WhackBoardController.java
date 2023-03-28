package com.example.demo;

import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WhackBoardController implements Initializable {

    @FXML
    private ImageView img00;

    @FXML
    private ImageView img01;

    @FXML
    private ImageView img02;

    @FXML
    private ImageView img10;

    @FXML
    private ImageView img11;

    @FXML
    private ImageView img12;

    @FXML
    private ImageView img20;

    @FXML
    private ImageView img21;

    @FXML
    private ImageView img22;

    @FXML
    private Text strScore;

    @FXML
    private Text scoreNum;

    @FXML
    private ImageView imgWhack;

    @FXML
    private Text win;

    final String regexIDUp = "id=Up";

    int userScore = 0;

    @FXML
    void MoleUpOrDown(int moleMove, ImageView img) {
        try {
            InputStream streamMoleU = new FileInputStream("src\\main\\resources\\com\\example\\demo\\MoleUp.png");
            Image moleUp = new Image(streamMoleU);

            InputStream streamMoleD = new FileInputStream("src\\main\\resources\\com\\example\\demo\\MoleDown.png");
            Image moleDown = new Image(streamMoleD);


            if (moleMove == 0) {
                img.setId("Down");
                img.setImage(moleDown);
            }
            if (moleMove == 1) {
                img.setId("Up");
                img.setImage(moleUp);
            }
            if (moleMove == 2) {
                img.setId("Down");
                img.setImage(moleDown);
            }
            if (moleMove == 3) {
                img.setId("Down");
                img.setImage(moleDown);
            }
//            Random rand = new Random();
//            int intRandom = rand.nextInt(3);
//            System.out.println("Random num: " + intRandom);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int randNum() {

        return ThreadLocalRandom.current().nextInt(0, 4);
    }

    public boolean randTF() {

        return ThreadLocalRandom.current().nextBoolean();
    }

    private Matcher MatchyMatchy(String strLine, String strRegex) {
        Pattern p = null;
        Matcher m = null;
        p = Pattern.compile(strRegex);
        m = p.matcher(strLine);
        return m;
    }

    @FXML
    void MoleClicked(MouseEvent event) {
        // get the id of the mole
        EventTarget target = event.getTarget();
        String strTarget = target.toString();

        //see if it's up or down
        Matcher m = null;
        m = MatchyMatchy(strTarget, regexIDUp);
        if (m.find()) {
            boolean escaped = randTF();
            //if didn't escape add points
            if (!escaped) {
                userScore++;
                scoreNum.setText(String.valueOf(userScore));
                imgWhack.setVisible(true);
                imgWhack.setMouseTransparent(false);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Timer t = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                try {
                    MoleUpOrDown(randNum(), img00);
                    MoleUpOrDown(randNum(), img01);
                    MoleUpOrDown(randNum(), img02);
                    MoleUpOrDown(randNum(), img10);
                    MoleUpOrDown(randNum(), img11);
                    MoleUpOrDown(randNum(), img12);
                    MoleUpOrDown(randNum(), img20);
                    MoleUpOrDown(randNum(), img21);
                    MoleUpOrDown(randNum(), img22);
                    imgWhack.setVisible(false);
                    imgWhack.setMouseTransparent(true);
                    if (userScore == 15) {
                        userWin(t);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                };
            }
        };
        t.schedule(task, new Date(), 500);
    }

    public void userWin(Timer t) {
        t.cancel();
        imgWhack.setMouseTransparent(true);
        imgWhack.setVisible(false);
        img00.setMouseTransparent(true);
        img00.setVisible(false);
        img01.setMouseTransparent(true);
        img01.setVisible(false);
        img02.setMouseTransparent(true);
        img02.setVisible(false);
        img10.setMouseTransparent(true);
        img10.setVisible(false);
        img11.setMouseTransparent(true);
        img11.setVisible(false);
        img12.setMouseTransparent(true);
        img12.setVisible(false);
        img20.setMouseTransparent(true);
        img20.setVisible(false);
        img21.setMouseTransparent(true);
        img21.setVisible(false);
        img22.setMouseTransparent(true);
        img22.setVisible(false);
        win.setVisible(true);
    }
}