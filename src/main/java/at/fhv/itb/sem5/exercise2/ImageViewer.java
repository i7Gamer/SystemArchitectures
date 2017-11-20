package at.fhv.itb.sem5.exercise2;

import at.fhv.itb.sem5.lib.pmp.filter.ForwardingFilter;
import at.fhv.itb.sem5.lib.pmp.interfaces.Readable;
import at.fhv.itb.sem5.lib.pmp.interfaces.Writeable;
import com.sun.media.jai.widget.DisplayJAI;

import javax.media.jai.PlanarImage;
import javax.swing.*;
import java.awt.*;
import java.security.InvalidParameterException;

public class ImageViewer extends ForwardingFilter<PlanarImage>{
    public ImageViewer(Readable<PlanarImage> input, Writeable<PlanarImage> output) throws InvalidParameterException {
        super(input, output);
    }

    public ImageViewer(Readable<PlanarImage> input) throws InvalidParameterException {
        super(input);
    }

    public ImageViewer(Writeable<PlanarImage> output) throws InvalidParameterException {
        super(output);
    }

    @Override
    protected boolean forward(PlanarImage entity) {
        JFrame frame = new JFrame();
        frame.setTitle("DisplayJAI: lena512.jpg");

    // Get the JFrame� ContentPane.
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new BorderLayout());

        DisplayJAI dj = new DisplayJAI(entity);
        String imageInfo = "Dimensions: "+entity.getWidth()+"x"+entity.getHeight()+ " Bands:"+entity.getNumBands();

        contentPane.add(new JScrollPane(dj),BorderLayout.CENTER);

    // Add a text label with the image information.
        contentPane.add(new JLabel(imageInfo),BorderLayout.SOUTH);

    // Set the closing operation so the application is finished.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(entity.getWidth()+26,entity.getHeight()+46); // adjust the frame size.
        frame.setVisible(true); // show the frame.

        return true;
    }
}
