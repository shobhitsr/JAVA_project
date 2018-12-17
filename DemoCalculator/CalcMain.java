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
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

/**
 *
 * @author shobhit
 */
public class CalcMain extends JFrame
{

    //private JFrame frame;
    private MenuBar menuBar;
    private Menu aboutMenu;
    private AboutDialog dialog;

    public static void main(String[] args)
    {
        // TODO code application logic here

        new Hw5();
    }
    public Hw5()
    {
        super("Calculator by Shobhit Srivastava");

        setSize(1000,600);
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(0,3));

         menuBar = new MenuBar();
        aboutMenu = new Menu("About");
        menuBar.add(aboutMenu);
        aboutMenu.add(new MenuItem("Name: Shobhit Srivastava"));
        aboutMenu.add(new MenuItem("Project: Calculator"));
        aboutMenu.addActionListener(new ActionListener()
         {
            public void actionPerformed(ActionEvent event)
            {
               if (dialog == null) // first time
               dialog = new AboutDialog(Hw5.this);
               dialog.setVisible(true); // pop up dialog
            }
         });
        setMenuBar(menuBar);

        Calculator c1 = new Calculator();
        Calculator2 c2 = new Calculator2();
        Calculator3 c3 = new Calculator3();
        add(c1.getContentPane());

        add(c2.getContentPane());

        add(c3.getContentPane());

        this.pack();
        setVisible(true);
    }
}
class AboutDialog extends JDialog
{
   public AboutDialog(JFrame owner)
   {
      super(owner, "Calculator by Shobhit Srivastava", true);

      // add HTML label to center

      add(
            new JLabel(
                  "<html>"
                          + "<h1><i>Project Calculator</i></h1>"
                          + "<hr>By Shobhit Srivastava<br>"
                          + "<i>This projects implements a simple calculator in 3 different ways</i>"
                          + "</html>"),
            BorderLayout.CENTER);

      // Ok button closes the dialog

      JButton ok = new JButton("Ok");
      ok.addActionListener(new ActionListener()
         {
            public void actionPerformed(ActionEvent event)
            {
               setVisible(false);
            }
         });

      // add Ok button to southern border

      JPanel panel = new JPanel();
      panel.add(ok);
      add(panel, BorderLayout.SOUTH);

      setSize(350, 250);
   }
}
