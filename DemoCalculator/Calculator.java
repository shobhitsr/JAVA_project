/*
Author:    Shobhit Srivastava

Purpose:   To undersand how GUI's work. We implement 3 seperate classes
           in the main DemoCalculator class. to perform general funtions.


Execution: Command to execute the program:

           run on NetBeans.

 */
package democalculator;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

/**
 *
 * @author shobhit
 */

/* Starter GUI program */

public class Calculator extends JFrame implements ActionListener
  {
    private JTextField xfield, yfield;
    private JLabel result;
    private JButton addButton;
    private JButton substractButton;
    private JButton multiplyButton;
    private JButton divideButton;
    private JButton clearButton;
    private JPanel xpanel;
    private JPanel xButtonPanel;

    public Calculator()
      {
        super("Calculator1");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        xpanel = new JPanel();
        xpanel.setLayout(new GridLayout(3,2));

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

        xButtonPanel = new JPanel();
        xButtonPanel.setLayout(new GridLayout(0,5));

        addButton = new JButton("Add");
        addButton.addActionListener(this);
        xButtonPanel.add(addButton,BorderLayout.NORTH);

        substractButton = new JButton("Substract");
        substractButton.addActionListener(this);
        xButtonPanel.add(substractButton);


        multiplyButton = new JButton("Multiply");
        multiplyButton.addActionListener(this);
        xButtonPanel.add(multiplyButton);


        divideButton = new JButton("Divide");
        divideButton.addActionListener(this);
        xButtonPanel.add(divideButton);


        clearButton = new JButton("Clear");
        clearButton.addActionListener(this);
        xButtonPanel.add(clearButton);

        Border bevelBorder = new BevelBorder(BevelBorder.RAISED, Color.RED, Color.RED.darker(),
        Color.RED, Color.RED.brighter());
        xpanel.setBorder(bevelBorder);
        xButtonPanel.setBorder(bevelBorder);

        add(xButtonPanel, BorderLayout.NORTH);
        pack();
        setVisible(false);
  }


  @Override
  public void actionPerformed(ActionEvent ex) {
        int x = 0;
        int y = 0;

        String xText = xfield.getText();
        String yText = yfield.getText();

        try
          {
            x = Integer.parseInt(xText);
          }
        catch (NumberFormatException e)
          {
            result.setText("x value isn't a number");
          }

        try
          {
            y = Integer.parseInt(yText);
          }
        catch (NumberFormatException e)
          {
            result.setText("y value isn't a number");
          }
        if (ex.getSource() == addButton)
        {
          result.setText(Integer.toString(x+y));
        }
        else if (ex.getSource() == substractButton)
        {
          result.setText(Integer.toString(x-y));
        }
        else if (ex.getSource() == multiplyButton)
        {
          result.setText(Integer.toString(x*y));
        }
        else if (ex.getSource() == divideButton)
        {
          if (y==0)
                  {
                      result.setText("divide by zero");
                  }
                  else
                  {
                      result.setText(Integer.toString(x/y));
                  }
        }
        else if (ex.getSource() == clearButton)
        {
          xfield.setText("0");
          yfield.setText("0");
          result.setText(Integer.toString(0));
        }

    }
  }
