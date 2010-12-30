/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package virtual_control_room.view.vial;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GradientPaint;
import javax.swing.JPanel;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.ThermometerPlot;
import org.jfree.data.general.DefaultValueDataset;
import samples.Sample;

/**
 *
 * @author JA Dianes
 */
public class VialView extends JPanel implements
        virtual_control_room.view.View {

    JFreeChart chart;
    ThermometerPlot thermometer;
    DefaultValueDataset dataset;
    float displayHeight;
    float displayWidth;
    int moteID;
    String variable;

    public VialView(int moteID, String name, float displayHeight, float displayWidth, double value) {

        super(new BorderLayout());
        this.moteID = moteID;
        this.variable = name;
        dataset = new DefaultValueDataset(value);
        thermometer = new ThermometerPlot(dataset);
        thermometer.setThermometerStroke(new BasicStroke(2.0f));
        thermometer.setThermometerPaint(Color.lightGray);
        thermometer.setUnits(ThermometerPlot.UNITS_CELCIUS);
        thermometer.setRange(-50.0,100.0);

        this.displayHeight = displayHeight;
        this.displayWidth = displayWidth;

                  // GradientPaint gradientPaint = new GradientPaint(0.0F, 10.0F, Color.WHITE, displayHeight, displayWidth, Color.green.darker());

                    //plot.setBackgroundPaint(gradientPaint);

        thermometer.setBackgroundPaint(new GradientPaint(0,0,Color.blue,this.displayWidth,this.displayHeight, new Color(102,0,102)));

        // You can change the mercury color below
        thermometer.setMercuryPaint(new GradientPaint(0,0,Color.blue,this.displayWidth,this.displayHeight, new Color(102,0,102)));
        thermometer.setValuePaint(Color.black); //Change the color of the number inside the bulb
        thermometer.setThermometerPaint(Color.ORANGE); //Change the outside paint of the thermometer

        /*
        You can create ranges for the thermometer, you can add more ranges by just duplicating the following line and
        incrementing the number
        */
        thermometer.setSubrange(0, -50.0, 0.0);
        thermometer.setSubrange(1, 0.1, 50.0);
        thermometer.setSubrange(2, 50.1, 100.0);
        thermometer.setSubrangePaint(0, Color.BLUE);
        thermometer.setSubrangePaint(1, Color.ORANGE);
        thermometer.setSubrangePaint(2, Color.RED);

        chart = new JFreeChart(name, thermometer);
        ChartUtilities.applyCurrentTheme(chart);

        ChartPanel panel = new ChartPanel(chart);
//        ApplicationFrame frame = new ApplicationFrame(name);
//        frame.setContentPane(panel);
//        frame.pack();
//        frame.setVisible(true);
        //panel.setVisible(true);
        this.add(panel);
    }

    public void showSample(Sample s) {
//      System.out.println("VialView.showSample(): showing sample for "+s.key);
        dataset.setValue(((Double)s.getValue()).doubleValue());
    }

}
