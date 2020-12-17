import java.awt.*;
import javax.swing.*;
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

    private class application_view {

        private JFrame main_window;

        public void application_view() {

            main_window = new JFrame();
            main_window.setSize(200, 400);
            main_window.setTitle("Color App Java");
            main_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            main_window.setVisible(true);


        }

    }

    private class application_controller {

    }

    public ColorApp() {

        application_view view = new application_view();

    }

    public static void main(String[] args)  {

        ColorApp main_application = new ColorApp();

    }


}
