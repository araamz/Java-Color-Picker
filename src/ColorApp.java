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

        public application_view() {

            main_window = new JFrame();
            JPanel container = new JPanel(new GridBagLayout());
            GridBagConstraints c =  new GridBagConstraints();
            main_window.add(container);

            color_viewer = new JPanel();
            c.fill = GridBagConstraints.HORIZONTAL;
            c.weightx = 1;
            c.weighty = 0;
            c.gridy = 0;
            c.gridx = 0;
            c.ipady = 150;
            c.insets = new Insets(0, 0,5,5);
            color_viewer.setBackground(Color.BLACK);
            container.add(color_viewer, c);
            container.setBorder(new EmptyBorder(10,10,10,10));

            JPanel color_palette = new JPanel();
            c.fill = GridBagConstraints.HORIZONTAL;
            c.weightx = 1;
            c.weighty = 0;
            c.gridy = 1;
            c.gridx = 0;
            c.ipady = 150;
            c.insets = new Insets(0, 0,5,5);
            color_palette.setBackground(Color.RED);
            container.add(color_palette, c);

            JPanel color_options = new JPanel();
            c.fill = GridBagConstraints.HORIZONTAL;
            c.weightx = 1;
            c.weighty = 0;
            c.gridy = 2;
            c.gridx = 0;
            c.ipady = 150;
            c.insets = new Insets(0, 0,0,5);
            color_options.setBackground(Color.GREEN);
            container.add(color_options, c);

            JList color_selection = new JList();
            c.fill = GridBagConstraints.VERTICAL;
            c.weightx = 0;
            c.weighty = 0;
            c.gridheight = GridBagConstraints.REMAINDER;
            c.gridy = 0;
            c.gridx = 1;
            c.ipady = 150;
            c.insets = new Insets(0, 0,0,0);
            String[] movies = {"Movie 1", "Movie 2", "Movie 3", "Movie 4"};
            color_selection.setListData(movies);
            color_selection.setFixedCellWidth(110);
            container.add(color_selection, c);


            main_window.setSize(200, 530);
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
