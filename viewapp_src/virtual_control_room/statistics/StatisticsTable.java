/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package virtual_control_room.statistics;

import java.util.Hashtable;
import java.util.Iterator;
import javax.swing.JTable;

/**
 *
 * @author JA Dianes
 */
public class StatisticsTable extends JTable {

    Hashtable<String,Statistic> statistics = new Hashtable();
    public StatisticsTable() {
        initComponents();
        this.setValueAt("Variable",0,0);
        this.setValueAt("Average",0,1);
        this.setValueAt("Samples", 0, 2);
    }

    private void initComponents() {
        setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Variable", "Average", "Samples"
            })
        );
    }

    public void addVariable(String name) {
        this.statistics.put(name, new Statistic(name));
        updateView();
    }

    public void addSample(String variable, double value) {
        Statistic st = this.statistics.get(variable);
        st.numSamples++;
        st.average = ((st.average*(st.numSamples-1))/st.numSamples)
                    +(value/st.numSamples);
        updateView();
    }

    private void updateView() {
        Iterator<Statistic> it = this.statistics.values().iterator();
        int row = 1;
        while (it.hasNext()) {
            Statistic st = it.next();
            this.setValueAt(st.variable,row,0);
            this.setValueAt(st.average,row,1);
            this.setValueAt(st.numSamples, row, 2);
            row++;
        }
    }
}
