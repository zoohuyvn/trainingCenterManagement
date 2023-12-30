package utils;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import bases.variables;

/**
 * @author zoohuy
 * 28 thg 12, 2023
 */

public class generatePieChart extends JPanel {
	public static ChartPanel chartPanel;
	private static ArrayList dataChart;
	static JFreeChart chart;
	
	public static void gnrt(String title, ArrayList dataChartInput) {
		dataChart = dataChartInput;
    	PieDataset dataset = createDataset();
        chart = ChartFactory.createPieChart(title, dataset, true, true, false);
        StandardPieSectionLabelGenerator labelGenerator = 
        new StandardPieSectionLabelGenerator("{0} ({2})", new DecimalFormat("0"), new DecimalFormat("0%"));
        chartPanel = new ChartPanel(chart);
        applyChartTheme(chart);
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setLabelGenerator(labelGenerator);
        plot.setBackgroundPaint(Color.white);
        plot.setShadowPaint(null);
        plot.setOutlineVisible(false);
    	plot.setLabelShadowPaint(null);
    	plot.setLabelBackgroundPaint(null);
    	plot.setLabelOutlinePaint(Color.decode("#D1D1D1"));
        plot.setSectionPaint("Poor", Color.decode("#CBE2FC"));
        plot.setSectionPaint("Weak", Color.decode("#9DC9FC"));
        plot.setSectionPaint("Average", Color.decode("#76B4FA"));
        plot.setSectionPaint("Pretty", Color.decode("#51A0FA"));
        plot.setSectionPaint("Good", Color.decode(variables.primaryColor));
    }

    private static PieDataset createDataset() {
    	DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Poor", (Number) dataChart.get(0));
        dataset.setValue("Weak", (Number) dataChart.get(1));
        dataset.setValue("Average", (Number) dataChart.get(2));
        dataset.setValue("Pretty", (Number) dataChart.get(3));
        dataset.setValue("Good", (Number) dataChart.get(4));
        return dataset;
    }
    
    public static void applyChartTheme(JFreeChart chart) {
        final StandardChartTheme chartTheme = (StandardChartTheme)org.jfree.chart.StandardChartTheme.createJFreeTheme();
        final Font extraLargeFont = new Font("Roboto", Font.PLAIN, 19);
        final Font largeFont = new Font("Roboto", Font.PLAIN, 17);
        final Font regularFont = new Font("Roboto", Font.PLAIN, 16);
        final Font smallFont = new Font("Roboto", Font.PLAIN, 15);
        chartTheme.setExtraLargeFont(extraLargeFont);
        chartTheme.setLargeFont(largeFont);
        chartTheme.setRegularFont(regularFont);
        chartTheme.setSmallFont(smallFont);
        chartTheme.apply(chart);
    }
    
    public static void exportToPng(int type) {
    	String chartSavePath = "";
    	if (type == 3) {
            chartSavePath = "C:\\Users\\USER\\eclipse-workspace\\trainingCenterManagement\\src\\assets\\images\\chart-3.png";
    	}
    	try {
			ChartUtils.saveChartAsPNG(new File(chartSavePath), chart, 1040, 800);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
}