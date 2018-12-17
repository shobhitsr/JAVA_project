/*
Author:    Shobhit Srivastava

Purpose:   To undersand how GUI's work. We implement 3 seperate classes
           in the main DemoCalculator class. to perform general funtions.


Execution: Command to execute the program:

           run on NetBeans.

 */
package democalculator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

/**
 *
 * @author shobhit
 */
public class Calculator3 extends JFrame implements ActionListener
{
    private JTextField xfield, yfield;
    private JLabel result;
    private String[] operations = {"Add","Substract","Multiply","Divide","Clear"};
    private JComboBox dropDownList;
    private JPanel xpanel;
    private JTextField tf;
    private JCheckBox makeFloating;
    private JRadioButton rb1;
    private JRadioButton rb2;
    private ButtonGroup bg;
    private int res;
    private float flo;

    public Calculator3()
    {
        super("Calculator3");
        Border bevelBorder = new BevelBorder(BevelBorder.RAISED, Color.CYAN, Color.CYAN.darker(),
        Color.CYAN, Color.CYAN.brighter());
        dropDownList = new JComboBox(operations);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        dropDownList.setSelectedIndex(4);
        dropDownList.addActionListener(this);
        dropDownList.setBorder(bevelBorder);
        add(dropDownList,BorderLayout.NORTH);

        rb1 = new JRadioButton("Integer");
        rb2 = new JRadioButton("Float");
        bg = new ButtonGroup();
        bg.add(rb1);
        bg.add(rb2);
        rb1.addActionListener(this);
        rb1.setSelected(true);
        rb2.addActionListener(this);

        this.add(rb1,BorderLayout.WEST);
        this.add(rb2,BorderLayout.EAST);


        xpanel = new JPanel();
        xpanel.setLayout(new GridLayout(3,2));

        xpanel.setBorder(bevelBorder);
        xpanel.add(new JLabel("x:"));
        xfield = new JTextField("0", 5);
        xpanel.add(xfield);


        xpanel.add(new JLabel("y:"));
        yfield = new JTextField("0", 5);
        xpanel.add(yfield);

        xpanel.add(new JLabel("Result = "));
        result = new JLabel("0");
        Font font = new Font("SansSerif", Font.BOLD,14);
        result.setFont(font);
        result.setForeground(Color.red);
        xpanel.add(result);
        add(xpanel, BorderLayout.SOUTH);
        pack();
        setVisible(false);
  }


  @Override
  public void actionPerformed(ActionEvent ex)
  {
        int x = 0;
        int y = 0;
        float s = 0;
        float t =0;

        String xText = xfield.getText();
        String yText = yfield.getText();

        try
          {
            x = Integer.parseInt(xText);
            s = Float.parseFloat(xText);
          }
        catch (NumberFormatException e)
          {
            result.setText("x value isn't a number");
          }

        try
          {
            y = Integer.parseInt(yText);
            t = Float.parseFloat(yText);
          }
        catch (NumberFormatException e)
          {
            result.setText("y value isn't a number");
          }

        if(rb1.isSelected()){
            if (ex.getSource() == dropDownList) {
          JComboBox cb = (JComboBox)ex.getSource();
          String msg = (String)cb.getSelectedItem();
            switch(msg)
              {
                case "Add":
                    res = x + y;
                    result.setText(Integer.toString(res));
                  break;
                case "Substract":
                    res = x - y;
                    result.setText(Integer.toString(res));
                  break;
                case "Multiply":
                    res = x * y;
                    result.setText(Integer.toString(res));
                  break;
                case "Divide":
                    if (y==0)
                    {
                        result.setText("divide by zero");
                    }
                    else
                    {
                        res = x / y;
                        result.setText(Integer.toString(res));
                    };
                  break;
                case "Clear": xfield.setText("0");
                              yfield.setText("0");
                              res = 0;
                              result.setText(Integer.toString(res));
                  break;
                }

            }
        }
          else if(rb2.isSelected())
            {
                if (ex.getSource() == dropDownList) {
          JComboBox cb = (JComboBox)ex.getSource();
          String msg = (String)cb.getSelectedItem();
                  switch(msg)
                  {
                    case "Add":
                        flo = s + t;
                        result.setText(Float.toString(flo));
                      break;
                    case "Substract":
                        flo = s - t;
                        result.setText(Float.toString(flo));
                      break;
                    case "Multiply":
                        flo = s * t;
                        result.setText(Float.toString(flo));
                      break;
                    case "Divide":
                        if (y==0)
                        {
                            result.setText("divide by zero");
                        }
                        else
                        {
                            flo = s / t;
                            result.setText(Float.toString(flo));
                        };
                      break;
                    case "Clear": xfield.setText("0");
                                  yfield.setText("0");
                                  flo = 0;
                                  result.setText(Float.toString(flo));
                      break;
                }
            }
        }
    }
}
