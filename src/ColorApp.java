import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.io.*;

public class ColorApp {

    private class color_item {

        private int red_value;
        private int green_value;
        private int blue_value;
        private String color_name;


        public color_item(Color color, String name) {

            red_value = color.getRed();
            green_value = color.getGreen();
            blue_value = color.getBlue();
            color_name = name;

        }

        public int get_red() {

            return red_value;

        }

        public int get_green() {

            return green_value;

        }

        public int get_blue() {

            return blue_value;

        }

        public String get_name() {

            return color_name;

        }

        public void set_red(int red) {

            red_value = red;

        }

        public void set_green(int green) {

            green_value = green;

        }

        public void set_blue(int blue) {

            blue_value = blue;

        }

        public void set_name(String name) {

            color_name = name;

        }

    }

    private class application_model {

        private color_item selected_color;
        private DefaultListModel<color_item> loaded_colors;

        public application_model(color_item color) {

            selected_color = color;

        }

        public color_item get_color() {

            return selected_color;

        }

        public void set_color(color_item color) {

            selected_color = color;

        }


    }

    public class application_view {

        private JFrame main_window;
        private JPanel color_viewer;

        private JButton redPalette_add;
        private JButton redPalette_subtract;
        private JTextField redPalette_field;

        private JButton greenPalette_add;
        private JButton greenPalette_subtract;
        private JTextField greenPalette_field;

        private JButton bluePalette_add;
        private JButton bluePalette_subtract;
        private JTextField bluePalette_field;

        private JButton save_button;
        private JButton reset_button;

        public application_view() {

            main_window = new JFrame();
            JPanel container = new JPanel(new GridBagLayout());
            GridBagConstraints c =  new GridBagConstraints();
            GridBagConstraints d = new GridBagConstraints();
            main_window.add(container);

            color_viewer = new JPanel();
            c.fill = GridBagConstraints.HORIZONTAL;
            c.weightx = 1;
            c.weighty = 0;
            c.gridy = 0;
            c.gridx = 0;
            c.ipady = 100;
            c.insets = new Insets(0, 0,5,5);
            color_viewer.setBackground(Color.BLACK);
            container.add(color_viewer, c);
            container.setBorder(new EmptyBorder(10,10,10,10));

            JPanel color_palette = new JPanel(new GridLayout(3,4));
            c.fill = GridBagConstraints.HORIZONTAL;
            c.weightx = 1;
            c.weighty = 0;
            c.gridy = 1;
            c.gridx = 0;
            c.ipady = 30;
            c.insets = new Insets(0, 0,5,5);
            container.add(color_palette, c);

            redPalette_add = new JButton("+");
            redPalette_subtract = new JButton("-");
            redPalette_field = new JTextField();
            JLabel redPalette_label = new JLabel("Red:");
            color_palette.add(redPalette_label);
            color_palette.add(redPalette_field);
            color_palette.add(redPalette_subtract);
            color_palette.add(redPalette_add);

            greenPalette_add = new JButton("+");
            greenPalette_subtract = new JButton("-");
            greenPalette_field = new JTextField();
            JLabel greenPalette_label = new JLabel("Green:");
            color_palette.add(greenPalette_label);
            color_palette.add(greenPalette_field);
            color_palette.add(greenPalette_subtract);
            color_palette.add(greenPalette_add);

            bluePalette_add = new JButton("+");
            bluePalette_subtract = new JButton("-");
            bluePalette_field = new JTextField();
            JLabel bluePalette_label = new JLabel("Blue:");
            color_palette.add(bluePalette_label);
            color_palette.add(bluePalette_field);
            color_palette.add(bluePalette_subtract);
            color_palette.add(bluePalette_add);
            bluePalette_label.setPreferredSize(new Dimension(60,10));
            bluePalette_field.setPreferredSize(new Dimension(120, 10));
            bluePalette_add.setPreferredSize(new Dimension(50, 10));
            bluePalette_subtract.setPreferredSize(new Dimension(50, 10));



            JPanel color_options = new JPanel();
            save_button = new JButton("Save");
            reset_button = new JButton("Reset");
            c.fill = GridBagConstraints.HORIZONTAL;
            c.weightx = 1;
            c.weighty = 0;
            c.gridy = 2;
            c.gridx = 0;
            c.ipady = -9;
            c.insets = new Insets(0, 0,0,5);
            container.add(color_options, c);
            color_options.add(save_button);
            color_options.add(reset_button);



            JList color_selection = new JList();
            d.fill = GridBagConstraints.VERTICAL;
            d.weightx = 0;
            d.weighty = 0;
            d.gridheight = GridBagConstraints.REMAINDER;
            d.gridy = 0;
            d.gridx = 1;
            d.insets = new Insets(0, 0,0,0);
            String[] movies = {"Movie 1", "Movie 2", "Movie 3", "Movie 4"};
            color_selection.setListData(movies);
            color_selection.setFixedCellWidth(110);
            container.add(color_selection, d);

            main_window.setSize(330, 310);
            main_window.setTitle("Color App Java");
            main_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            main_window.setVisible(true);


        }

    }

    private class application_controller {

    }

    public ColorApp() {

        new application_view();

    }

    public static void main(String[] args)  {

        ColorApp main_application = new ColorApp();

    }


}
