package app.computerShop.frame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

public class TableModel {

    private DefaultTableModel defaultTableModel;

    public TableModel(ResultSet rs) throws SQLException
    {
        ResultSetMetaData metaData = rs.getMetaData();

        // names of columns
        Vector<String> columnNames = new Vector<String>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        // data of the table
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (rs.next()) {
            Vector<Object> vector = new Vector<Object>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }

        defaultTableModel =new DefaultTableModel(data, columnNames);
    }
    public DefaultTableModel getModel()
    {
        defaultTableModel.fireTableDataChanged();
        return defaultTableModel;
    }

    public static void setVisibleRowCount(JTable table, int rows){
        int height = 0;
        for(int row=0; row<rows; row++)
            height += table.getRowHeight(row);

        table.setPreferredScrollableViewportSize(new Dimension(
                table.getPreferredScrollableViewportSize().width,
                height
        ));
    }

    public static void setColumnWidths(JTable table, int... widths)
    {
        TableColumnModel columnModel = table.getColumnModel();
        for (int i = 0; i < widths.length; i++)
        {
            if (i < columnModel.getColumnCount())
            {
                columnModel.getColumn(i).setMaxWidth(widths[i]);
            }
            else break;
        }
    }


}
