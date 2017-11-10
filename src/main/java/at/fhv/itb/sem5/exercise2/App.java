package at.fhv.itb.sem5.exercise2;

import at.fhv.itb.sem5.exercise1.a.*;
import at.fhv.itb.sem5.exercise1.b.*;
import calccentroidsfilter.CalcCentroidsFilter;
import com.sun.media.jai.widget.DisplayJAI;
import com.sun.org.apache.regexp.internal.RE;
import javafx.print.PageLayout;
import pmp.interfaces.Readable;
import pmp.interfaces.Writeable;

import javax.media.jai.JAI;
import javax.media.jai.KernelJAI;
import javax.media.jai.PlanarImage;
import javax.media.jai.operator.MedianFilterDescriptor;
import javax.media.jai.operator.MedianFilterShape;
import javax.print.attribute.standard.Media;
import javax.swing.*;
import java.awt.*;
import java.awt.image.Raster;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.ParameterBlock;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

import static com.sun.tools.doclint.Entity.image;

public class App {

    public static void main(String[] args) throws IOException {

        String fileName = "aufgabe2/loetstellen.JPG";
        String outputName = "aufgabe2/LoetstellenFiltered.png";
        String outputName2 = "aufgabe2/QualityInfo.txt";

        double[] low = new double[] { 0 };
        double[] high = new double[] { 30 };
        double[] constant = new double[] { 255 };

        float[] kernelMatrix = new float[] {1, 1, 1, 1, 1,
                1, 1, 1, 1, 1,
                1, 1, 1, 1, 1,
                1, 1, 1, 1, 1,
                1, 1, 1, 1, 1};

        MedianFilterShape medianFilterShape = MedianFilterDescriptor.MEDIAN_MASK_SQUARE;
        int medianFilterSize = 7;

        int x = 0;
        int y = 40;
        int width = 448;
        int height = 110 - y;

        Rectangle rectangle = new Rectangle(x, y, width, height);


        ImageSource source = new ImageSource("fileload",fileName);

        ROIFilter roiFilter = new ROIFilter(source, rectangle);

        ThresholdFilter thresholdFilter = new ThresholdFilter((Readable<PlanarImage>) roiFilter, low, high, constant);

        ImageViewer imageViewer = new ImageViewer((Readable<PlanarImage>) thresholdFilter);

        MedianFilter medianFilter = new MedianFilter((Readable<PlanarImage>) imageViewer, medianFilterShape, medianFilterSize);

        ImageViewer imageViewer2 = new ImageViewer((Readable<PlanarImage>) medianFilter);

        OpeningFilter openingFilter = new OpeningFilter((Readable<PlanarImage>) imageViewer2, kernelMatrix, 2);

        ImageViewer imageViewer3 = new ImageViewer((Readable<PlanarImage>) openingFilter);

        //ClosingFilter closingFilter = new ClosingFilter((Readable<PlanarImage>) imageViewer3, kernelMatrix, 2);

        //ImageViewer imageViewer4 = new ImageViewer((Readable<PlanarImage>) closingFilter);

        SaveFilter saveFilter = new SaveFilter((Readable<PlanarImage>) imageViewer3, outputName);

        CalcCentroidsFilter calcCentroidsFilter = new CalcCentroidsFilter((Readable<PlanarImage>) saveFilter, x, y);

        QualityCheckFilter qualityCheckFilter = new QualityCheckFilter(calcCentroidsFilter, 0,0, outputName2);

        qualityCheckFilter.run();
    }
}
