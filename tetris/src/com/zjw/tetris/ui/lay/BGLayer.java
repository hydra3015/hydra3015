package com.zjw.tetris.ui.lay;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class BGLayer extends Layer {

    private BufferedImage[] bgs = null;
    private int currentBG;

    public int getCurrentBG() {
        return currentBG;
    }

    public void setCurrentBG(int currentBG) {
        if (currentBG >= bgs.length)
            this.currentBG = bgs.length - 1;
        else if (currentBG < 0)
            this.currentBG = 0;
        else
            this.currentBG = currentBG;
    }

    public BGLayer() {
        init();
    }

    private void init() {
        int imageNum = 10;

        bgs = new BufferedImage[imageNum];
        for (int i = 0; i < bgs.length; i++) {
            String imageName = "bg-" + ((i + 1) < 10 ? ("0" + (i + 1)) : (i + 1)) + ".png";
            URL resource = Thread.currentThread().getContextClassLoader().getResource("bg/" + imageName);
			try {
				bgs[i] = ImageIO.read(resource);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(bgs[gameDto.getGread() - 1], 0, 0, 1280, 720, 0, 0, 1280, 720, null);

    }

    public static void main(String[] args) {
        URL bg = Thread.currentThread().getContextClassLoader().getResource("bg");
        File file = new File("file:/D:/JAVA/workspace/ideaprojects/firstproject/out/artifacts/tetris_jar/tetris.jar!/bg");
        System.out.println(file.exists());
    }


}
