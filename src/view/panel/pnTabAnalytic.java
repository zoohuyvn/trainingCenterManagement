package view.panel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import bases.variables;
import controller.pnAnalyticListener;
import dao.analyticDao;
import utils.exportAnalyticReport;
import utils.generateColumnChart;
import utils.generatePieChart;

/**
 * @author zoohuy
 * 28 thg 12, 2023
 */

public class pnTabAnalytic extends JPanel {
	// Init pnBtn
	JPanel pnBtn;
	JButton exportBtn, refreshBtn;
	
	// Init pnTotal
	JPanel pnTotal, pnTotalRevenue,
	pnTotalStudent, pnTotalAdvisor, pnTotalClass;
	double totalRefvenue;
	NumberFormat nf;
	String totalRevenueStr;
	
	// Init pnChart
	JPanel pnChart;
	private static generateColumnChart chart1;
	private static generateColumnChart chart2;
	ArrayList<?> dataChart1, dataChart2, dataChart3;
	String titleChart1 = "Number of student, advisor each class";
	String titleChart2 = "Average score each class";
	String titleChart3 = "Score percentage by rate";
	
	public pnTabAnalytic() {
		this.setLayout(new BorderLayout());
		ActionListener acl = new pnAnalyticListener(this);
		
		// pnBtn
		pnBtn = new JPanel(new FlowLayout(SwingConstants.RIGHT));
		pnBtn.setBackground(Color.white);
		exportBtn = new JButton(" Export report");
		exportBtn.setBackground(Color.white);
		exportBtn.setFocusable(false);
		exportBtn.setIcon(new ImageIcon(pnTabAnalytic.class.getResource("/assets/icons/out.png")));
		exportBtn.setBorder(BorderFactory.createEmptyBorder(10, 18, 10, 18));
		refreshBtn = new JButton(" Refresh");
		refreshBtn.setBackground(Color.white);
		refreshBtn.setFocusable(false);
		refreshBtn.setIcon(new ImageIcon(pnTabAnalytic.class.getResource("/assets/icons/refresh.png")));
		refreshBtn.setBorder(BorderFactory.createEmptyBorder(10, 18, 10, 18));
		pnBtn.add(exportBtn);
		pnBtn.add(refreshBtn);
		exportBtn.addActionListener(acl);
		refreshBtn.addActionListener(acl);
		
		// pnTotal
		pnTotal = new JPanel(new GridLayout(1, 4));
		pnTotal.setBackground(Color.white);
		pnTotal.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
		pnTotalRevenue = new JPanel();
		pnTotalRevenue.setBorder(BorderFactory.createLineBorder(Color.white, 10));
		pnTotalRevenue.setBackground(Color.decode(variables.primaryColorLight));
		pnTotalStudent = new JPanel();
		pnTotalStudent.setBorder(BorderFactory.createLineBorder(Color.white, 10));
		pnTotalStudent.setBackground(Color.decode(variables.primaryColorLight));
		pnTotalAdvisor = new JPanel();
		pnTotalAdvisor.setBorder(BorderFactory.createLineBorder(Color.white, 10));
		pnTotalAdvisor.setBackground(Color.decode(variables.primaryColorLight));
		pnTotalClass = new JPanel();
		pnTotalClass.setBorder(BorderFactory.createLineBorder(Color.white, 10));
		pnTotalClass.setBackground(Color.decode(variables.primaryColorLight));
		pnTotal.add(pnTotalRevenue);
		pnTotal.add(pnTotalStudent);
		pnTotal.add(pnTotalAdvisor);
		pnTotal.add(pnTotalClass);
		
		// pnChart
		pnChart = new JPanel(new GridLayout(1, 3));
		pnChart.setPreferredSize(new Dimension(0, 480));
		pnChart.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		pnChart.setBackground(Color.white);
		chart1 = new generateColumnChart();
		chart2 = new generateColumnChart();
		
		// Render data numeric and chart
		renderData();
		pnChart.add(chart1.chartPanel);
		pnChart.add(chart2.chartPanel);
		pnChart.add(generatePieChart.chartPanel);
		
		// Add component
		this.add(pnBtn, BorderLayout.NORTH);
		this.add(pnTotal, BorderLayout.CENTER);
		this.add(pnChart, BorderLayout.SOUTH);
	}
	
	public void renderData() {
		// Data numeric
		totalRefvenue = analyticDao.getTotalRevenue();
		nf = NumberFormat.getCurrencyInstance();
		totalRevenueStr = nf.format(totalRefvenue);
		pnTotalRevenue.add(new JLabel("<html><p style='text-align: center; font-size: 20px; margin-top: 11px'>Total revenue<br>"
				+ "<b style='color: "+variables.primaryColor+"'>"+totalRevenueStr+"</b><p></html>"));
		pnTotalStudent.add(new JLabel("<html><p style='text-align: center; font-size: 20px; margin-top: 11px'>Total student<br>"
				+ "<b style='color: "+variables.primaryColor+"'>"+analyticDao.getTotalStudent()+"</b><p></html>"));
		pnTotalAdvisor.add(new JLabel("<html><p style='text-align: center; font-size: 20px; margin-top: 11px'>Total advisor<br>"
				+ "<b style='color: "+variables.primaryColor+"'>"+analyticDao.getTotalAdvisor()+"</b><p></html>"));
		pnTotalClass.add(new JLabel("<html><p style='text-align: center; font-size: 20px; margin-top: 11px'>Total class<br>"
				+ "<b style='color: "+variables.primaryColor+"'>"+analyticDao.getTotalClass()+"</b><p></html>"));
		// Chart
		dataChart1 = analyticDao.getStAvsOfClass();
		dataChart2 = analyticDao.getAvgPointOfClass();
		dataChart3 = analyticDao.getScoreRate();
		chart1.gnrt(titleChart1, 1, dataChart1);
		chart2.gnrt(titleChart2, 2, dataChart2);
		generatePieChart.gnrt(titleChart3, dataChart3);
	}
	
	public void refreshData() {
		// Data numeric
		pnTotalRevenue.removeAll();
		pnTotalStudent.removeAll();
		pnTotalAdvisor.removeAll();
		pnTotalClass.removeAll();
		// Chart
		pnChart.removeAll();
		// Re-render data
		renderData();
		// Data numeric
		pnTotalRevenue.revalidate();
	    pnTotalStudent.revalidate();
	    pnTotalAdvisor.revalidate();
	    pnTotalClass.revalidate();
		// Chart
		pnChart.revalidate();
		pnChart.add(chart1.chartPanel);
		pnChart.add(chart2.chartPanel);
		pnChart.add(generatePieChart.chartPanel);
	}
	
	public static void export() {
		// Export chart png
		generateColumnChart.exportToPng(1, chart1.chart);
		generateColumnChart.exportToPng(2, chart2.chart);
		generatePieChart.exportToPng(3);
		// Export pdf
		exportAnalyticReport.export();
	}
	
}