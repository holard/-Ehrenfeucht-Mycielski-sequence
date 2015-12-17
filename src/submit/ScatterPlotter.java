package submit;

import java.awt.RenderingHints;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.FastScatterPlot;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * Additional runnable for generating graphs via the JFreeChart library.
 * 
 */
public class ScatterPlotter extends ApplicationFrame {

	/**
	 * Constant for alphabet size to plot
	 */
	public static final int ALPHABET_SIZE = 9;
	/**
	 * Constant for the number of bits to plot
	 */
	public static final int NUM_BITS = 15000;
	
	/** The data. */
	private float[][] data;

	/**
	 * Creates a new fast scatter plot demo.
	 *
	 * @param title
	 *            the frame title.
	 */
	public ScatterPlotter(final String title, int[] inputData) {
		super(title);
		data = new float[2][inputData.length];
		populateData(inputData);
		final NumberAxis domainAxis = new NumberAxis("X");
		domainAxis.setAutoRangeIncludesZero(false);
		final NumberAxis rangeAxis = new NumberAxis("Y");
		rangeAxis.setAutoRangeIncludesZero(false);
		final FastScatterPlot plot = new FastScatterPlot(this.data, domainAxis,
				rangeAxis);
		final JFreeChart chart = new JFreeChart(
				"Match Positions for alphabet size " + ALPHABET_SIZE, plot);
		// chart.setLegend(null);

		// force aliasing of the rendered content..
		chart.getRenderingHints().put(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		final ChartPanel panel = new ChartPanel(chart, true);
		panel.setPreferredSize(new java.awt.Dimension(500, 270));
		// panel.setHorizontalZoom(true);
		// panel.setVerticalZoom(true);
		panel.setMinimumDrawHeight(10);
		panel.setMaximumDrawHeight(2000);
		panel.setMinimumDrawWidth(20);
		panel.setMaximumDrawWidth(2000);

		setContentPane(panel);

	}

	/**
	 * Populates the data array with random values.
	 */
	private void populateData(int[] data) {

		for (int i = 0; i < this.data[0].length; i++) {
			this.data[0][i] = i;
			this.data[1][i] = data[i];
		}

	}

	/**
	 * Starting point for the plotter.
	 *
	 * @param args
	 *            ignored.
	 */
	public static void main(final String[] args) {
		String seed = "0";
		Alphabet A = new ModAlphabet(ALPHABET_SIZE);

		int[] points = new int[NUM_BITS];

		RadixNumGenerator g = new RadixNumGenerator(seed, A);
		for (int i = 0; i < NUM_BITS; i++) {
			Bundle next = g.next();
			// can be set to match length (next.length), or any other property
			// depends on what you want to plot.
			points[i] = next.position;
		}

		final ScatterPlotter demo = new ScatterPlotter(
				"Match position scatter plot demo", points);
		demo.pack();
		RefineryUtilities.centerFrameOnScreen(demo);
		demo.setVisible(true);

	}

}
