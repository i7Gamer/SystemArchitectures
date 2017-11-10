package at.fhv.itb.sem5.exercise2;

import calccentroidsfilter.CalcCentroidsFilter;
import calccentroidsfilter.Coordinate;
import pmp.interfaces.Readable;

import javax.media.jai.PlanarImage;
import javax.media.jai.operator.MedianFilterDescriptor;
import javax.media.jai.operator.MedianFilterShape;
import java.awt.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class App {

    private static String fileName;
    private static String outputName;
    private static String outputName2;
    private static double[] low;
    private static double[] high;
    private static double[] constant;
    private static List<Coordinate> expected;
    private static float[] kernelMatrix;
    private static MedianFilterShape medianFilterShape;
    private static int medianFilterSize;
    private static int toleranceX;
    private static int toleranceY;
    private static Rectangle rectangle;
    private static int x = 0;
    private static int y = 40;
    private static int width = 448;
    private static int height = 110 - y;

    public static void main(String[] args) throws IOException {
        initData();

        Scanner s = new Scanner(System.in);

        System.out.println("1 - push | 2 - pull | anything else to exit");

        String input = s.next();

        if ("1".equals(input)) {
            doPush();
        } else if ("2".equals(input)) {
            doPull();
        }
        s.close();
        System.out.println("finished");
    }


    public static void initData() {
        fileName = "aufgabe2/loetstellen.JPG";
        outputName = "aufgabe2/LoetstellenFiltered.png";
        outputName2 = "aufgabe2/QualityInfo.txt";

        low = new double[]{0};
        high = new double[]{30};
        constant = new double[]{255};

        expected = Arrays.asList(new Coordinate(73, 77),
                new Coordinate(110, 80),
                new Coordinate(202, 80),
                new Coordinate(265, 79),
                new Coordinate(330, 81),
                new Coordinate(396, 81));


        kernelMatrix = new float[]{1, 1, 1, 1, 1,
                1, 1, 1, 1, 1,
                1, 1, 1, 1, 1,
                1, 1, 1, 1, 1,
                1, 1, 1, 1, 1};

        medianFilterShape = MedianFilterDescriptor.MEDIAN_MASK_SQUARE;
        medianFilterSize = 7;

        toleranceX = 2;
        toleranceY = 2;

        x = 0;
        y = 40;
        width = 448;
        height = 110 - y;

        rectangle = new Rectangle(x, y, width, height);
    }

    private static void doPush() {
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

        CalcCentroidsFilter calcCentroidsFilter = new CalcCentroidsFilter(saveFilter, x, y);

        QualityCheckFilter qualityCheckFilter = new QualityCheckFilter(calcCentroidsFilter, outputName2, expected, toleranceX, toleranceY);

        qualityCheckFilter.run();
    }

    private static void doPull() {
        QualityCheckFilter qualityCheckFilter =
                new QualityCheckFilter(
                        new CalcCentroidsFilter(new SaveFilter((Readable<PlanarImage>)
                                new ImageViewer((Readable<PlanarImage>)
                                        new OpeningFilter((Readable<PlanarImage>)
                                                new ImageViewer((Readable<PlanarImage>)
                                                        new MedianFilter((Readable<PlanarImage>)
                                                                new ImageViewer((Readable<PlanarImage>)
                                                                        new ThresholdFilter((Readable<PlanarImage>)
                                                                                new ROIFilter(new ImageSource("fileload", fileName), rectangle), low, high, constant)), medianFilterShape, medianFilterSize)), kernelMatrix, 2)), outputName), x, y), outputName2, expected, toleranceX, toleranceY);

        qualityCheckFilter.run();
    }
}
