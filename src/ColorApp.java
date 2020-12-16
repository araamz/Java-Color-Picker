/*
* Araam Zaremehrjardi
* 12/07/2020
* CS 326
* HW #8
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;
import java.util.concurrent.Flow;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.io.*;

public class ColorApp extends JFrame {

    public class color_item {

        public String color_name;
        public Color color_value;

        public color_item(String color_name, int r_value, int g_value, int b_value) {

            this.color_name = color_name;
            this.color_value = new Color(r_value, g_value, b_value);

        }

    }

    protected JList color_selection_list;
    private DefaultListModel color_names = new DefaultListModel();
    private color_item[] color_data = new color_item[11];
    private int r_value = 0;
    private int g_value = 0;
    private int b_value = 0;
    private boolean inital_run_flag = true;

    public static void main(String[] args) throws IOException {

        new ColorApp("Color Sampler");

    }

    private class WindowDestroyer extends WindowAdapter {

        public void windowClosing(WindowEvent e) {

            try {
                offload_colors();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            System.exit(0);

        }

    }

    public void load_colors() throws IOException {

        FileInputStream istream = new FileInputStream("colors.txt");
        InputStreamReader reader = new InputStreamReader(istream);
        StreamTokenizer tokens = new StreamTokenizer(reader);

        String color_name;
        int r_value;
        int g_value;
        int b_value;

         for (int i = 0; i < 11; i++) {

             tokens.nextToken();
             color_name = (String) tokens.sval;
             tokens.nextToken();
             r_value = (int) tokens.nval;
             tokens.nextToken();
             g_value = (int) tokens.nval;
             tokens.nextToken();
             b_value = (int) tokens.nval;

             this.color_names.addElement(color_name);
             this.color_data[i] = new color_item(color_name, r_value, g_value, b_value);

         }

         istream.close();

    }

    public void offload_colors() throws IOException {

        FileOutputStream ostream = new FileOutputStream("colors.txt");
        PrintWriter writer = new PrintWriter(ostream);

        String color_name = "NA";
        int r_value = 0;
        int g_value = 0;
        int b_value = 0;

        for (int i = 0; i < 11; i++) {

            color_name = this.color_data[i].color_name;
            r_value = this.color_data[i].color_value.getRed();
            g_value = this.color_data[i].color_value.getGreen();
            b_value = this.color_data[i].color_value.getBlue();

            writer.println(color_name + " " + String.valueOf(r_value) + " " + String.valueOf(g_value) + " " + String.valueOf(b_value) + "\n");

        }

        writer.flush();
        ostream.close();

    }

    public ColorApp (String title) throws IOException {

        super(title);
        load_colors();
        this.setSize(340, 300);
        addWindowListener(new WindowDestroyer());
        this.setResizable(false);

        JPanel container = new JPanel();
        JPanel left_side_container = new JPanel(new GridLayout(3,1));
        JPanel right_side_container = new JPanel();
        container.add(left_side_container);
        container.add(right_side_container);
        this.add(container);

        JPanel color_viewer_container = new JPanel();
        color_viewer_container.setOpaque(true);
        color_viewer_container.setBackground(new Color(r_value, g_value, b_value));
        left_side_container.add(color_viewer_container);

        JPanel color_palette_container = new JPanel(new GridLayout(3,1));
        left_side_container.add(color_palette_container);

        JPanel color_palette_red_panel = new JPanel();
        JLabel color_palette_red_label = new JLabel("Red: ");
        JTextField color_palette_red_field = new JTextField(r_value);
        JButton color_palette_red_add = new JButton("+");
        JButton color_palette_red_subtract = new JButton("-");
        color_palette_red_panel.add(color_palette_red_label);
        color_palette_red_panel.add(color_palette_red_field);
        color_palette_red_panel.add(color_palette_red_add);
        color_palette_red_panel.add(color_palette_red_subtract);
        color_palette_container.add(color_palette_red_panel);
        color_palette_red_label.setPreferredSize(new Dimension(50, 20));
        color_palette_red_field.setPreferredSize(new Dimension(80, 20));
        color_palette_red_add.setPreferredSize(new Dimension(40, 20));
        color_palette_red_subtract.setPreferredSize(new Dimension(40, 20));
        color_palette_red_field.setEditable(false);
        color_palette_red_add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (r_value < 255) {

                    r_value = r_value + 5;

                }
                if (inital_run_flag == true) {

                    if (r_value != 0) {

                        setTitle("Color Sampler*");

                    }
                    else {

                        setTitle("Color Sampler");

                    }

                } else {

                    if (r_value != color_data[color_selection_list.getSelectedIndex()].color_value.getRed()) {

                        setTitle("Color Sampler*");

                    } else {

                        setTitle("Color Sampler");

                    }

                }
                color_palette_red_field.setText(String.valueOf(r_value));
                color_viewer_container.setBackground(new Color(r_value, g_value, b_value));
            }
        });
        color_palette_red_subtract.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (r_value > 0) {

                    r_value = r_value - 5;

                }
                if (inital_run_flag == true) {

                    if (r_value != 0) {

                        setTitle("Color Sampler*");

                    }
                    else {

                        setTitle("Color Sampler");

                    }

                } else {

                    if (r_value != color_data[color_selection_list.getSelectedIndex()].color_value.getRed()) {

                        setTitle("Color Sampler*");

                    } else {

                        setTitle("Color Sampler");

                    }

                }
                color_palette_red_field.setText(String.valueOf(r_value));
                color_viewer_container.setBackground(new Color(r_value, g_value, b_value));
            }
        });

        JPanel color_palette_green_panel = new JPanel();
        JLabel color_palette_green_label = new JLabel("Green: ");
        JTextField color_palette_green_field = new JTextField(g_value);
        JButton color_palette_green_add = new JButton("+");
        JButton color_palette_green_subtract = new JButton("-");
        color_palette_green_panel.add(color_palette_green_label);
        color_palette_green_panel.add(color_palette_green_field);
        color_palette_green_panel.add(color_palette_green_add);
        color_palette_green_panel.add(color_palette_green_subtract);
        color_palette_container.add(color_palette_green_panel);
        color_palette_green_label.setPreferredSize(new Dimension(50, 20));
        color_palette_green_field.setPreferredSize(new Dimension(80, 20));
        color_palette_green_add.setPreferredSize(new Dimension(40, 20));
        color_palette_green_subtract.setPreferredSize(new Dimension(40, 20));
        color_palette_green_field.setEditable(false);
        color_palette_green_add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (g_value < 255) {

                    g_value = g_value + 5;

                }
                if (inital_run_flag == true) {

                    if (g_value != 0) {

                        setTitle("Color Sampler*");

                    }
                    else {

                        setTitle("Color Sampler");

                    }

                } else {

                    if (g_value != color_data[color_selection_list.getSelectedIndex()].color_value.getGreen()) {

                        setTitle("Color Sampler*");

                    } else {

                        setTitle("Color Sampler");

                    }

                }
                color_palette_green_field.setText(String.valueOf(g_value));
                color_viewer_container.setBackground(new Color(r_value, g_value, b_value));
            }
        });
        color_palette_green_subtract.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (g_value > 0) {

                    g_value = g_value - 5;

                }
                if (inital_run_flag == true) {

                    if (g_value != 0) {

                        setTitle("Color Sampler*");

                    }
                    else {

                        setTitle("Color Sampler");

                    }

                } else {

                    if (g_value != color_data[color_selection_list.getSelectedIndex()].color_value.getGreen()) {

                        setTitle("Color Sampler*");

                    } else {

                        setTitle("Color Sampler");

                    }

                }
                color_palette_green_field.setText(String.valueOf(g_value));
                color_viewer_container.setBackground(new Color(r_value, g_value, b_value));
            }
        });


        JPanel color_palette_blue_panel = new JPanel();
        JLabel color_palette_blue_label = new JLabel("Blue: ");
        JTextField color_palette_blue_field = new JTextField(b_value);
        JButton color_palette_blue_add = new JButton("+");
        JButton color_palette_blue_subtract = new JButton("-");
        color_palette_blue_panel.add(color_palette_blue_label);
        color_palette_blue_panel.add(color_palette_blue_field);
        color_palette_blue_panel.add(color_palette_blue_add);
        color_palette_blue_panel.add(color_palette_blue_subtract);
        color_palette_container.add(color_palette_blue_panel);
        color_palette_blue_label.setPreferredSize(new Dimension(50, 20));
        color_palette_blue_field.setPreferredSize(new Dimension(80, 20));
        color_palette_blue_add.setPreferredSize(new Dimension(40, 20));
        color_palette_blue_subtract.setPreferredSize(new Dimension(40, 20));
        color_palette_blue_field.setEditable(false);
        color_palette_blue_add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (b_value < 255) {

                    b_value = b_value + 5;

                }
                if (inital_run_flag == true) {

                    if (b_value != 0) {

                        setTitle("Color Sampler*");

                    }
                    else {

                        setTitle("Color Sampler");

                    }

                } else {

                    if (b_value != color_data[color_selection_list.getSelectedIndex()].color_value.getBlue()) {

                        setTitle("Color Sampler*");

                    } else {

                        setTitle("Color Sampler");

                    }

                }
                color_palette_blue_field.setText(String.valueOf(b_value));
                color_viewer_container.setBackground(new Color(r_value, g_value, b_value));
            }
        });
        color_palette_blue_subtract.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (b_value > 0) {

                    b_value = b_value - 5;

                }
                if (inital_run_flag == true) {

                    if (b_value != 0) {

                        setTitle("Color Sampler*");

                    }
                    else {

                        setTitle("Color Sampler");

                    }

                } else {

                    if (b_value != color_data[color_selection_list.getSelectedIndex()].color_value.getBlue()) {

                        setTitle("Color Sampler*");

                    } else {

                        setTitle("Color Sampler");

                    }

                }
                color_palette_blue_field.setText(String.valueOf(b_value));
                color_viewer_container.setBackground(new Color(r_value, g_value, b_value));
            }
        });

        JPanel options_container = new JPanel();
        left_side_container.add(options_container);


        JButton options_save_button = new JButton("Save");
        JButton options_reset_button = new JButton("Reset");
        options_save_button.setPreferredSize(new Dimension(70, 20));
        options_reset_button.setPreferredSize(new Dimension(90, 20));
        options_container.add(options_save_button);
        options_container.add(options_reset_button);
        options_save_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (inital_run_flag == false) {
                    color_data[color_selection_list.getSelectedIndex()].color_value = new Color(r_value, g_value, b_value);

                    setTitle("Color Sampler");
                }

            }
        });
        options_reset_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (inital_run_flag == false) {

                    r_value = color_data[color_selection_list.getSelectedIndex()].color_value.getRed();
                    g_value = color_data[color_selection_list.getSelectedIndex()].color_value.getGreen();
                    b_value = color_data[color_selection_list.getSelectedIndex()].color_value.getBlue();
                    color_palette_red_field.setText(String.valueOf(r_value));
                    color_palette_green_field.setText(String.valueOf(g_value));
                    color_palette_blue_field.setText(String.valueOf(b_value));

                    color_viewer_container.setBackground(new Color(r_value, g_value, b_value));

                    setTitle("Color Sampler");

                }

            }
        });

        color_selection_list = new JList(color_names);
        right_side_container.add(color_selection_list);
        this.setVisible(true);

        color_selection_list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                if (!e.getValueIsAdjusting()) {

                    inital_run_flag = false;

                    r_value = color_data[color_selection_list.getSelectedIndex()].color_value.getRed();
                    g_value = color_data[color_selection_list.getSelectedIndex()].color_value.getGreen();
                    b_value = color_data[color_selection_list.getSelectedIndex()].color_value.getBlue();

                    color_palette_red_field.setText(String.valueOf(r_value));
                    color_palette_green_field.setText(String.valueOf(g_value));
                    color_palette_blue_field.setText(String.valueOf(b_value));
                    color_viewer_container.setBackground(new Color(r_value, g_value, b_value));

                }

            }
        });

    }

}
