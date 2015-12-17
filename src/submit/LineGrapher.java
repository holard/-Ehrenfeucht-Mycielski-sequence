package submit;

import java.awt.Color;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * Runnable line Graphing utility. 
 *
 */
public class LineGrapher extends ApplicationFrame {

	/**
	 * Constant for alphabet size to plot
	 */
	public static final int ALPHABET_SIZE = 2;
	/**
	 * Constant for the number of bits to plot
	 */
	public static final int NUM_BITS = 15000;
	
	
    /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     */
    public LineGrapher(final String title, int[] data) {

        super(title);

        final XYDataset dataset = createDataset(data);
        final JFreeChart chart = createChart(dataset);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);

    }
    
    /**
     * Creates the dataset.
     * 
     * @return the dataset.
     */
    private XYDataset createDataset(int[] points) {
        
        final XYSeries series1 = new XYSeries("");
        for (int i = 0; i < points.length; i++)
        {
        	series1.add(i,points[i]);
        }
        final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
        return dataset;
    }
    
    /**
     * Creates a chart.
     * 
     * @param dataset  the data for the chart.
     * 
     * @return a chart.
     */
    private JFreeChart createChart(final XYDataset dataset) {
        
        // create the chart...
        final JFreeChart chart = ChartFactory.createXYLineChart(
            "Match Lengths for alphabet size " + ALPHABET_SIZE, // chart title
            "Changes",                      // x axis label
            "Match Length",                      // y axis label
            dataset,                  // data
            PlotOrientation.VERTICAL,
            true,                     // include legend
            true,                     // tooltips
            false                     // urls
        );

        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
        chart.setBackgroundPaint(Color.white);

        // get a reference to the plot for further customisation...
        final XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.lightGray);
    //    plot.setAxisOffset(new Spacer(Spacer.ABSOLUTE, 5.0, 5.0, 5.0, 5.0));
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        
        final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(0, true);
        renderer.setSeriesShapesVisible(0, false);
        plot.setRenderer(renderer);

        // change the auto tick unit selection to integer units only...
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        // OPTIONAL CUSTOMISATION COMPLETED.
                
        return chart;
        
    }

    /**
     * Starting point for the line grapher. This example graphs match lengths
     * for alphabet size defined by the constant ALPHABET_SIZE.
     *
     * @param args  ignored.
     */
    public static void main(final String[] args) {

    	// set up
    	String seed = "0";
		Alphabet A = new ModAlphabet(ALPHABET_SIZE);

		int[] points = new int[NUM_BITS];
		int num = 0;
		int prev = -1;

		RadixNumGenerator g = new RadixNumGenerator(seed, A);
		
		// track changes, and load them into the array.
		for (int i = 0; i < NUM_BITS; i++) {
			Bundle next = g.next();
			int matchLength = next.length;
			if (prev == -1)
			{
				points[num] = matchLength;
				num += 1;
			} else if (matchLength != prev)
			{
				points[num] = matchLength;
				num += 1;
			}
			prev = matchLength;
			
		}
		
		// copy changes into a fitting array.
		int[] data = new int[num];
		
		for (int j = 0; j < num; j++)
		{
			data[j] = points[j];
		}
        final LineGrapher demo = new LineGrapher("Match Length", data);
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }
}
