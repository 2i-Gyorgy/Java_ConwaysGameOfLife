package hu.conways.life.game;

import hu.conways.life.game.GameLogic.Logic;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Color backgroundColour = new Color(100, 100, 50);
        Color deadCellColour = new Color(10, 40, 70);
        Color aliveCellColour = new Color(230, 255, 230);

        JFrame frame = new JFrame();
        frame.setBounds(10, 10, 768, 768);
//        frame.setUndecorated(true);
        frame.setBackground(backgroundColour);

        Logic logic = new Logic();
        logic.plantSeed();

        int i = 0;
        while(i < 130000) {
            JPanel pn = new JPanel() {
                @Override
                public void paint(Graphics g) {
                    for (int y = 0; y < 128; y++) {
                        for (int x = 0; x < 128; x++) {
                            g.setColor(deadCellColour);
                            g.fillRect(x * 6, y * 6, 5, 5);
                        }
                    }
                    try {
                        for (Map.Entry<Byte, Set<Byte>> entry : logic.gameFieldCalc().entrySet()) {
                            Byte x = entry.getKey();
                            Set<Byte> yCoordinates = entry.getValue();
                            for (byte y : yCoordinates) {
                                g.setColor(aliveCellColour);
                                g.fillRect((x + 64) * 6, (y + 64) * 6, 5, 5);
                            }
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            };
            frame.add(pn);
            frame.setDefaultCloseOperation(3);
            frame.setVisible(true);

            i++;
            TimeUnit.MILLISECONDS.sleep(128);
        }

    }
}