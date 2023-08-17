package model;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;

public class Card extends JButton {

    private String value;
    private boolean faceUp = false;
    private boolean matched = false;
    private String faceUpImageFilename = null;
    private ImageIcon faceUpIcon = null;
    private static String faceDownImageFilename = "https://deckofcardsapi.com/static/img/back.png";
    public static ImageIcon faceDownIcon = null;;

    public Card(String cardValue, String faceUpImageFilename) {
        this.value = cardValue;
        this.faceUpImageFilename = faceUpImageFilename;
        setup();
    }

    private void setup() {
        setHorizontalTextPosition(JButton.CENTER);
        setBorder(new LineBorder(Color.BLACK));
        setSize(new Dimension(72, 96));
        setPreferredSize(new Dimension(72, 96));

        // Set the image for the card face down
        if( faceDownIcon == null ) {
            faceDownIcon = createImageIcon(faceDownImageFilename);
        }

        // Set the image for the card face up
        if( this.faceUpImageFilename != null ) {
            this.faceUpIcon = createImageIcon(this.faceUpImageFilename);
        }

        setIcon(faceDownIcon);
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public void setMatched(boolean matched) {
        this.matched = matched;
    }

    public boolean isMatched() {
        return this.matched;
    }

    public boolean isFaceUp() {
        return this.faceUp;
    }

    public void setFaceUp(boolean faceUp) {
        this.faceUp = faceUp;
    }

    public void setFaceUpIcon(String filename) {
        this.faceUpIcon = createImageIcon(filename);
    }

    public ImageIcon createImageIcon(String imageFileURL) {
        ImageIcon icon = null;

        try {
//            Image image = ImageIO.read(getClass().getResource(filename));
            BufferedImage image = ImageIO.read(URI.create(imageFileURL).toURL());
            icon = new ImageIcon(image.getScaledInstance(72, 96, Image.SCALE_SMOOTH));
        } catch (IOException | IllegalArgumentException ex) {
            System.out.println("ERROR: Unable to load image from: " + imageFileURL);
            System.out.println(ex);
        }

        return icon;
    }

    public void remove() {
        setFaceUp(false);
        setMatched(true);
        setIcon(null);
        setText(null);
        setBorder(null);
        setEnabled(false);
    }

    public void draw() {
        if( !this.matched ) {
            // This card is still on the board

            if( this.faceUp ) {
                if( this.faceUpIcon == null ) {
                    setText("" + this.value);
                }
                setIcon(faceUpIcon);
            } else {
                setText("");
                setIcon(faceDownIcon);
            }
        }
    }

    public boolean isSame(Card otherCard) {
        if( this.faceUpIcon != null ) {
            return this.getValue().equalsIgnoreCase(otherCard.getValue());
        }
        return false;
    }
}
