import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;

class ImageSelection implements Transferable {
    private Image image;
    private static ImageSelection obj = null;

    private ImageSelection(Image image) {
        this.image = image;
    }

    public static ImageSelection Initimagesl(File file) {
        if (obj == null) {
            obj = new ImageSelection(new ImageIcon(file.getAbsolutePath()).getImage());
        }
        return obj;
    }

    public void ImageToClipboard() {
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(obj, null);
    }

    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return new DataFlavor[]{DataFlavor.imageFlavor};
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return DataFlavor.imageFlavor.equals(flavor);
    }

    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException {
        if (!DataFlavor.imageFlavor.equals(flavor)) {
            throw new UnsupportedFlavorException(flavor);
        }
        return image;
    }
}
