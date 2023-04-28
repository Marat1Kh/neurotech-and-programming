import java.awt.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.function.Function2D;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.GradientPaintTransformType;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.StandardGradientPaintTransformer;
import org.jfree.ui.TextAnchor;

public class Graph {
    public static void main(String[] args) {
        // Create a dataset for the original function
        XYSeries originalSeries = new XYSeries("Original Function");
        for (double x = -10.0; x <= 10.0; x += 0.1) {
            originalSeries.add(x, Math.sin(x));
        }
        XYSeriesCollection originalDataset = new XYSeriesCollection(originalSeries);

        // Create a dataset for the interpolated function
        XYSeries interpolatedSeries = new XYSeries("Interpolated Function");
        List<Double> x_axis = new ArrayList<>();
        List<Double> y_axis = new ArrayList<>();
        x_axis.add(-5.0); x_axis.add(-2.0); x_axis.add(1.0); x_axis.add(4.0); x_axis.add(7.0);
        y_axis.add(0.9589243); y_axis.add(0.9092974); y_axis.add(0.8414710); y_axis.add(0.7568025); y_axis.add(0.6569866);
        for (double x = -10.0; x <= 10.0; x += 0.1) {
            interpolatedSeries.add(x, Result.interpolate_by_lagrange(x_axis, y_axis, x));
        }
        XYSeriesCollection interpolatedDataset = new XYSeriesCollection(interpolatedSeries);

        // Create the chart
        JFreeChart chart = ChartFactory.createXYLineChart("Function Interpolation", "X", "Y", null, PlotOrientation.VERTICAL, true, true, false);
        chart.setBackgroundPaint(new GradientPaint(0, 0, Color.white, 1000, 0, Color.blue));
        XYPlot plot = chart.getXYPlot();

        // Add the original function to the chart
        plot.setDataset(0, originalDataset);
        XYLineAndShapeRenderer renderer0 = new XYLineAndShapeRenderer();
        renderer0.setSeriesPaint(0, Color.red);
        plot.setRenderer(0, renderer0);

        // Add the interpolated function to the chart
        plot.setDataset(1, interpolatedDataset);
        XYLineAndShapeRenderer renderer1 = new XYLineAndShapeRenderer();
        renderer1.setSeriesPaint(0, Color.yellow);
        renderer1.setSeriesStroke(0, new BasicStroke(2.0f));
        plot.setRenderer(1, renderer1);

        // Add a legend to the chart
        chart.getLegend().setPosition(RectangleEdge.RIGHT);

        // Customize the plot
         plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.white);
        plot.setRangeGridlinePaint(Color.gray);
        plot.setDomainGridlinePaint(Color.gray);

        // Add the data to the chart
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries series = new XYSeries("Interpolated function");
        for (double i = x_axis.get(0); i <= x_axis.get(x_axis.size()-1); i += 0.1) {
            series.add(i, Result.interpolate_by_lagrange(x_axis, y_axis, i));
        }
        dataset.addSeries(series);

        // Add the original data to the chart
        originalSeries = new XYSeries("Original function");
        for (int i = 0; i < x_axis.size(); i++) {
            originalSeries.add(x_axis.get(i), y_axis.get(i));
        }
        dataset.addSeries(originalSeries);

        // Create the renderer
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.blue);
        renderer.setSeriesPaint(1, Color.red);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));
        renderer.setSeriesStroke(1, new BasicStroke(2.0f));

        // Add the renderer to the plot
        plot.setRenderer(renderer);

        // Display the chart
        ChartPanel chartPanel = new ChartPanel(chart);
        JFrame frame = new JFrame("Interpolated function");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(chartPanel);
        frame.pack();
        frame.setVisible(true);
    }
}