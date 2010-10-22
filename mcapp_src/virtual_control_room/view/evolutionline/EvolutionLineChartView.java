/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package virtual_control_room.view.evolutionline;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import samples.Sample;
import virtual_control_room.statistics.Statistic;
import virtual_control_room.statistics.StatisticsTable;

/**
 *
 * @author JA Dianes
 */
public class EvolutionLineChartView extends JPanel implements 
        virtual_control_room.view.View, ActionListener {

    JFreeChart chart;
    XYPlot xyplot;
    XYSeriesCollection datasets = new XYSeriesCollection();
    StatisticsTable statisticsTable = new StatisticsTable();
    int bufferSize;
    int windowSize;
    float displayHeight;
    float displayWidth;
    int moteID;
    JTextField variableName;
    JFrame statisticsFrame;

    public EvolutionLineChartView(String name, int moteID, boolean spline) {
        super(new BorderLayout());
        this.moteID = moteID;
        chart = ChartFactory.createXYLineChart(
            name+" Evolution Chart",      // chart title
            "time",                      // x axis label
            "value",                      // y axis label
            createDataset(name),                  // data
            PlotOrientation.VERTICAL,
            true,                     // include legend
            true,                     // tooltips
            false                     // urls
        );
        XYItemRenderer renderer;
        XYPlot plot = new XYPlot();
        if (spline) {
            renderer = new XYSplineRenderer();
            plot.setRenderer(renderer);
        } else {
            // get a reference to the plot for further customisation...
            renderer = (XYLineAndShapeRenderer) plot.getRenderer();
            ((XYLineAndShapeRenderer)renderer).setBaseShapesVisible(true);
            ((XYLineAndShapeRenderer)renderer).setBaseShapesFilled(true);
            // change the auto tick unit selection to integer units only...
            NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
            rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        }

        ChartUtilities.applyCurrentTheme(chart);

        ChartPanel panel = new ChartPanel(chart);
        this.add(panel);

        JButton buttonShowStatistics = new JButton("Show statistics");
        buttonShowStatistics.setActionCommand("SHOW_STATISTICS");
        buttonShowStatistics.addActionListener(this);

        JButton buttonNewVariable = new JButton("Add Variable");
        buttonNewVariable.setActionCommand("ADD_VARIABLE");
        buttonNewVariable.addActionListener(this);
        this.variableName = new JTextField();
        variableName.setPreferredSize(new Dimension(150,30));
       
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(Color.white);
        buttonPanel.add(buttonShowStatistics);
        buttonPanel.add(new JSeparator());
        buttonPanel.add(buttonNewVariable);
        buttonPanel.add(variableName);
        this.add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.setPreferredSize(new java.awt.Dimension(200, 50));

        this.statisticsTable = new StatisticsTable();
        this.statisticsFrame = new JFrame();
        this.statisticsFrame.setTitle("Statistics Table");
        this.statisticsFrame.setVisible(false);
        this.statisticsFrame.add(this.statisticsTable);
        this.statisticsFrame.pack();
        this.statisticsFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.statisticsTable.addVariable(name);

   }

        /**
         * Handles a click on the button by adding new (random) data.
         *
         * @param e  the action event.
         */
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("ADD_VARIABLE")) {
            String name = this.variableName.getText();
            if (name.compareTo("")!=0) {
                this.addVariable(name);
            }
        } else if (e.getActionCommand().equals("SHOW_STATISTICS")) {
            this.statisticsFrame.setVisible(true);
        }

    }

    private XYDataset createDataset(String name) {
        XYSeries dataset = new XYSeries(name);
        datasets.addSeries(dataset);
        return datasets;
    }

    public void addVariable(String variable) {
        this.statisticsTable.addVariable(variable);
        createDataset(variable);
    }

    public void showSample(Sample v) {
        double value = ((Double)v.getValue()).doubleValue();
        this.statisticsTable.addSample(v.key,value);
        datasets.getSeries(v.key).add(System.currentTimeMillis(),
                    value);
    }

    public boolean containsVariable(String s) {
        return (this.datasets.getSeries(s)!=null);
    }
}
