package utils;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import bases.variables;

/**
 * @author zoohuy
 * 28 thg 12, 2023
 */

public class generateColumnChart extends JPanel {
	public ChartPanel chartPanel;
	private CategoryDataset dataset;
	private static ArrayList dataChart;
	public JFreeChart chart;
	
	public void gnrt(String title, int type, ArrayList dataChartInput) {
		dataChart = dataChartInput;
        if (type == 1) {
            dataset = createDataset1();
        } else if (type == 2) {
            dataset = createDataset2();
        }
        chart = ChartFactory.createBarChart(title, "", "", dataset, PlotOrientation.VERTICAL, true, true, false);
        chartPanel = new ChartPanel(chart);
        applyChartTheme(chart);
        CategoryPlot plot = chart.getCategoryPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        plot.setBackgroundPaint(Color.white);
        plot.setRangeGridlinePaint(Color.gray);
        plot.setOutlineVisible(false);
        renderer.setGradientPaintTransformer(null);
        renderer.setBarPainter(new StandardBarPainter());
        renderer.setSeriesPaint(0, Color.decode(variables.primaryColor));
        renderer.setSeriesPaint(1, Color.decode(variables.primaryColorDark));
    }

    private static CategoryDataset createDataset1() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        int count = ((ArrayList) dataChart.get(0)).size();
        for (int i = 0; i < count; i++) {
        	String className = (String) ((ArrayList) dataChart.get(0)).get(i);
            int stQuantity = Integer.parseInt((String) ((ArrayList) dataChart.get(1)).get(i));
            int avsQuantity = Integer.parseInt((String) ((ArrayList) dataChart.get(2)).get(i));
            dataset.addValue(stQuantity, "Student", className);
            dataset.addValue(avsQuantity, "Advisor", className);
        }
        return dataset;
    }
    
    private static CategoryDataset createDataset2() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        int count = ((ArrayList) dataChart.get(0)).size();
        for (int i = 0; i < count; i++) {
        	String className = (String) ((ArrayList) dataChart.get(0)).get(i);
            double avgPoint = Double.parseDouble((String) ((ArrayList) dataChart.get(1)).get(i));
            dataset.addValue(avgPoint, "Average point", className);
        }
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
    
    public static void exportToPng(int type, JFreeChart chartInput) {
    	String chartSavePath = "";
    	if (type == 1) {
            chartSavePath = "C:\\Users\\USER\\eclipse-workspace\\trainingCenterManagement\\src\\assets\\images\\chart-1.png";
    	} else if (type == 2) {
            chartSavePath = "C:\\Users\\USER\\eclipse-workspace\\trainingCenterManagement\\src\\assets\\images\\chart-2.png";
    	}
    	try {
			ChartUtils.saveChartAsPNG(new File(chartSavePath), chartInput, 1040, 800);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
}