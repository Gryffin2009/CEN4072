package Service;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ImageOperations {

    public static BufferedImage chooseImage() throws IOException {
        JFileChooser picchooser = new JFileChooser();
        File pic = picchooser.getSelectedFile();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.images", "png", "jpg");
        picchooser.addChoosableFileFilter(filter);
        picchooser.showOpenDialog(null);
        BufferedImage img;
        img = ImageIO.read(picchooser.getSelectedFile());
        return img;
    }

    public static byte[] imageToByteArray(BufferedImage img) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(img, "jpg", baos);
        byte[] byteArray = baos.toByteArray();
        baos.close();
        return byteArray;
    }

    public static ImageIcon imageToIcon(BufferedImage img) {
        ImageIcon imageIcon = new ImageIcon(
                new ImageIcon(img).getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT));
        return imageIcon;

    }
}
