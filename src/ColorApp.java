/*
 -> Created by Araam Zaremehrjardi
 -> Version 1.2
 -> CS 326: HW #8
 */
import java.awt.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

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

        @Override
        public String toString() {

            return color_name;

        }

    }

    private class application_model {

        private color_item selected_color;
        private color_item preview_color;
        private String color_save_file;
        private DefaultListModel loaded_colors;

        public application_model(String file) throws IOException {

            loaded_colors = new DefaultListModel();
            color_save_file = file;

            read_file(file);

            selected_color = new color_item(new Color(0,0,0), "DEFAULT");
            preview_color = new color_item(new Color(0,0,0), "DEFAULT");

        }

        public color_item get_selected_color() {

            return selected_color;

        }

        public void set_selected_color(color_item color) {

            selected_color = color;

        }

        public color_item get_preview_color() {

            return preview_color;

        }

        public void set_preview_color(color_item color) {

            preview_color = color;

        }

        public DefaultListModel get_loaded_colors() {

            return loaded_colors;

        }

        public void increment_red() {

            int red_value = preview_color.get_red();

            if (red_value < 255) {

                preview_color.set_red(red_value + 5);

            }

        }

        public void decrement_red() {

            int red_value = preview_color.get_red();

            if (red_value > 0) {

                preview_color.set_red(red_value - 5);

            }

        }

        public void increment_green() {

            int green_value = preview_color.get_green();

            if (green_value < 255) {

                preview_color.set_green(green_value + 5);

            }

        }

        public void decrement_green() {

            int green_value = preview_color.get_green();

            if (green_value > 0) {

                preview_color.set_green(green_value - 5);

            }

        }

        public void increment_blue() {

            int blue_value = preview_color.get_blue();

            if (blue_value < 255) {

                preview_color.set_blue(blue_value + 5);

            }

        }

        public void decrement_blue() {

            int blue_value = preview_color.get_blue();

            if (blue_value > 0) {

                preview_color.set_blue(blue_value - 5);

            }

        }

        public void change_selection(color_item view_selection) {

            preview_color.set_red(view_selection.get_red());
            preview_color.set_green(view_selection.get_green());
            preview_color.set_blue(view_selection.get_blue());
            preview_color.set_name(view_selection.get_name());
            selected_color.set_red(view_selection.get_red());
            selected_color.set_green(view_selection.get_green());
            selected_color.set_blue(view_selection.get_blue());
            selected_color.set_name(view_selection.get_name());

        }

        public String update_window_title() {

            if ((preview_color.get_red() == selected_color.get_red()) && (preview_color.get_green() == selected_color.get_green()) && (preview_color.get_blue() == selected_color.get_blue())) {

                return "Color Sampler";

            } else {

                return "Color Sampler*";

            }

        }

        public void update_selected_color(color_item view_selection) throws IOException {

            view_selection.set_red(preview_color.get_red());
            view_selection.set_green(preview_color.get_green());
            view_selection.set_blue(preview_color.get_blue());
            view_selection.set_name(preview_color.get_name());
            selected_color.set_red(preview_color.get_red());
            selected_color.set_green(preview_color.get_green());
            selected_color.set_blue(preview_color.get_blue());
            selected_color.set_name(preview_color.get_name());
            save_file(color_save_file);

        }

        public void reset_selected_color() {

            preview_color.set_red(selected_color.get_red());
            preview_color.set_green(selected_color.get_green());
            preview_color.set_blue(selected_color.get_blue());
            preview_color.set_name(selected_color.get_name());

        }

        private void read_file(String file) throws IOException {

            FileInputStream istream = new FileInputStream(file);
            InputStreamReader reader = new InputStreamReader(istream);
            StreamTokenizer tokens = new StreamTokenizer(reader);

            String color_name;
            int red_value;
            int green_value;
            int blue_value;

            for (int i = 0; tokens.nextToken() != tokens.TT_EOF; i++) {

                color_name = (String) tokens.sval;
                tokens.nextToken();
                red_value = (int) tokens.nval;
                tokens.nextToken();
                green_value = (int) tokens.nval;
                tokens.nextToken();
                blue_value = (int) tokens.nval;

                loaded_colors.addElement(new color_item(new Color(red_value, green_value, blue_value), color_name));

            }

            istream.close();

        }

        private void save_file(String file) throws IOException {

            FileOutputStream ostream = new FileOutputStream(file);
            PrintWriter writer = new PrintWriter(ostream);

            for (int i = 0; i < loaded_colors.getSize(); i++) {

                color_item saved_color = (color_item) loaded_colors.getElementAt(i);

                String color_name = saved_color.get_name();
                int red_value = saved_color.get_red();
                int green_value = saved_color.get_green();
                int blue_value = saved_color.get_blue();

                writer.println(color_name + " " + String.valueOf(red_value) + " " + String.valueOf(green_value) + " " + String.valueOf(blue_value) + "\n");

            }

            writer.flush();
            ostream.close();

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

        private JList color_selection;

        public application_view() {

            main_window = new JFrame();
            JPanel container = new JPanel(new GridBagLayout());

            GridBagConstraints color_viewer_constraints = new GridBagConstraints();
            color_viewer = new JPanel();

            GridBagConstraints color_palette_constraints = new GridBagConstraints();
            JPanel color_palette = new JPanel(new GridLayout(3,4));
            JLabel redPalette_label = new JLabel("Red:");
            redPalette_field = new JTextField();
            redPalette_add = new JButton("+");
            redPalette_subtract = new JButton("-");
            JLabel greenPalette_label = new JLabel("Green:");
            greenPalette_field = new JTextField();
            greenPalette_add = new JButton("+");
            greenPalette_subtract = new JButton("-");
            JLabel bluePalette_label = new JLabel("Blue:");
            bluePalette_field = new JTextField();
            bluePalette_add = new JButton("+");
            bluePalette_subtract = new JButton("-");

            GridBagConstraints color_options_constraints = new GridBagConstraints();
            JPanel color_options = new JPanel();
            save_button = new JButton("Save");
            reset_button = new JButton("Reset");

            GridBagConstraints color_selection_constraints = new GridBagConstraints();
            color_selection = new JList();

            container.setBorder(new EmptyBorder(10,10,10,10));
            main_window.add(container);

            color_viewer_constraints.fill = GridBagConstraints.HORIZONTAL;
            color_viewer_constraints.weightx = 1;
            color_viewer_constraints.weighty = 0;
            color_viewer_constraints.gridy = 0;
            color_viewer_constraints.gridx = 0;
            color_viewer_constraints.ipady = 100;
            color_viewer_constraints.insets = new Insets(0, 0,5,5);
            container.add(color_viewer, color_viewer_constraints);

            color_palette_constraints.fill = GridBagConstraints.HORIZONTAL;
            color_palette_constraints.weightx = 1;
            color_palette_constraints.weighty = 0;
            color_palette_constraints.gridy = 1;
            color_palette_constraints.gridx = 0;
            color_palette_constraints.ipady = 30;
            color_palette_constraints.insets = new Insets(0, 0,5,5);
            redPalette_field.setEditable(false);
            greenPalette_field.setEditable(false);
            bluePalette_field.setEditable(false);
            container.add(color_palette, color_palette_constraints);
            color_palette.add(redPalette_label);
            color_palette.add(redPalette_field);
            color_palette.add(redPalette_subtract);
            color_palette.add(redPalette_add);
            color_palette.add(greenPalette_label);
            color_palette.add(greenPalette_field);
            color_palette.add(greenPalette_subtract);
            color_palette.add(greenPalette_add);
            color_palette.add(bluePalette_label);
            color_palette.add(bluePalette_field);
            color_palette.add(bluePalette_subtract);
            color_palette.add(bluePalette_add);

            color_options_constraints.fill = GridBagConstraints.HORIZONTAL;
            color_options_constraints.weightx = 1;
            color_options_constraints.weighty = 0;
            color_options_constraints.gridy = 2;
            color_options_constraints.gridx = 0;
            color_options_constraints.ipady = -9;
            color_options_constraints.insets = new Insets(0, 0,0,5);
            container.add(color_options, color_options_constraints);
            color_options.add(save_button);
            color_options.add(reset_button);

            color_selection_constraints.fill = GridBagConstraints.VERTICAL;
            color_selection_constraints.weightx = 0;
            color_selection_constraints.weighty = 0;
            color_selection_constraints.gridheight = GridBagConstraints.REMAINDER;
            color_selection_constraints.gridy = 0;
            color_selection_constraints.gridx = 1;
            color_selection_constraints.insets = new Insets(0, 0,0,0);
            container.add(color_selection, color_selection_constraints);
            color_selection.setFixedCellWidth(110);

            main_window.setSize(330, 310);
            main_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            main_window.setVisible(true);
            main_window.setResizable(false);

        }

        public JFrame get_main_window() {

            return main_window;

        }

        public JPanel get_color_viewer() {

            return color_viewer;

        }

        public JButton get_redPalette_add() {

            return redPalette_add;

        }

        public JButton get_redPalette_subtract() {

            return redPalette_subtract;

        }

        public JTextField get_redPalette_field() {

            return redPalette_field;

        }

        public JButton get_greenPalette_add() {

            return greenPalette_add;

        }

        public JButton get_greenPalette_subtract() {

            return greenPalette_subtract;

        }

        public JTextField get_greenPalette_field() {

            return greenPalette_field;

        }

        public JButton get_bluePalette_add() {

            return bluePalette_add;

        }

        public JButton get_bluePalette_subtract() {

            return bluePalette_subtract;

        }

        public JTextField get_bluePalette_field() {

            return bluePalette_field;

        }

        public JButton get_save_button() {

            return save_button;

        }

        public JButton get_reset_button() {

            return reset_button;

        }

        public JList get_color_selection() {

            return color_selection;

        }

    }

    private class application_controller {

        private  application_model model;
        private application_view view;

        public application_controller(application_model model, application_view view) {

            this.model = model;
            this.view = view;
            initialize_view();

        }

        public void initialize_controller() {

            view.get_redPalette_add().addActionListener(event -> make_red_lighter());
            view.get_redPalette_subtract().addActionListener(event -> make_red_darker());
            view.get_greenPalette_add().addActionListener(event -> make_green_lighter());
            view.get_greenPalette_subtract().addActionListener(event -> make_green_darker());
            view.get_bluePalette_add().addActionListener(event -> make_blue_lighter());
            view.get_bluePalette_subtract().addActionListener(event -> make_blue_darker());
            view.get_color_selection().addListSelectionListener(event -> new_selection());
            view.get_save_button().addActionListener(event -> {

                try {

                    save_palette();

                } catch (IOException exception) {

                    System.out.println("CANT OPEN SAVE FILE" + exception);

                }
            });
            view.get_reset_button().addActionListener(event -> reset_palette());

        }

        private void initialize_view() {

            view.get_main_window().setTitle("Color Picker");
            view.get_color_viewer().setBackground(new Color(model.get_selected_color().get_red(), model.get_selected_color().get_green(), model.get_selected_color().get_blue()));
            view.get_redPalette_field().setText(String.valueOf(model.get_selected_color().get_red()));
            view.get_greenPalette_field().setText(String.valueOf(model.get_selected_color().get_green()));
            view.get_bluePalette_field().setText(String.valueOf(model.get_selected_color().get_blue()));
            view.get_color_selection().setModel(model.get_loaded_colors());

        }

        private void make_red_lighter() {

            model.increment_red();
            view.get_main_window().setTitle(model.update_window_title());
            view.get_color_viewer().setBackground(new Color(model.get_preview_color().get_red(), model.get_preview_color().get_green(), model.get_preview_color().get_blue()));
            view.get_redPalette_field().setText(String.valueOf(model.get_preview_color().get_red()));

        }

        private void make_red_darker() {

            model.decrement_red();
            view.get_main_window().setTitle(model.update_window_title());
            view.get_color_viewer().setBackground(new Color(model.get_preview_color().get_red(), model.get_preview_color().get_green(), model.get_preview_color().get_blue()));
            view.get_redPalette_field().setText(String.valueOf(model.get_preview_color().get_red()));

        }

        private void make_green_lighter() {

            model.increment_green();
            view.get_main_window().setTitle(model.update_window_title());
            view.get_color_viewer().setBackground(new Color(model.get_preview_color().get_red(), model.get_preview_color().get_green(), model.get_preview_color().get_blue()));
            view.get_greenPalette_field().setText(String.valueOf(model.get_preview_color().get_green()));

        }

        private void make_green_darker() {

            model.decrement_green();
            view.get_main_window().setTitle(model.update_window_title());
            view.get_color_viewer().setBackground(new Color(model.get_preview_color().get_red(), model.get_preview_color().get_green(), model.get_preview_color().get_blue()));
            view.get_greenPalette_field().setText(String.valueOf(model.get_preview_color().get_green()));

        }

        private void make_blue_lighter() {

            model.increment_blue();
            view.get_main_window().setTitle(model.update_window_title());
            view.get_color_viewer().setBackground(new Color(model.get_preview_color().get_red(), model.get_preview_color().get_green(), model.get_preview_color().get_blue()));
            view.get_bluePalette_field().setText(String.valueOf(model.get_preview_color().get_blue()));

        }

        private void make_blue_darker() {

            model.decrement_blue();
            view.get_main_window().setTitle(model.update_window_title());
            view.get_color_viewer().setBackground(new Color(model.get_preview_color().get_red(), model.get_preview_color().get_green(), model.get_preview_color().get_blue()));
            view.get_bluePalette_field().setText(String.valueOf(model.get_preview_color().get_blue()));

        }

        private void new_selection() {

            model.change_selection((color_item) view.get_color_selection().getSelectedValue());
            view.get_main_window().setTitle(model.update_window_title());
            view.get_color_viewer().setBackground(new Color(model.get_selected_color().get_red(), model.get_selected_color().get_green(), model.get_selected_color().get_blue()));
            view.get_redPalette_field().setText(String.valueOf(model.get_selected_color().get_red()));
            view.get_greenPalette_field().setText(String.valueOf(model.get_selected_color().get_green()));
            view.get_bluePalette_field().setText(String.valueOf(model.get_selected_color().get_blue()));

        }

        private void save_palette() throws IOException {

            model.update_selected_color((color_item) view.get_color_selection().getSelectedValue());
            view.get_main_window().setTitle("Color Sampler");

        }

        private void reset_palette() {

            model.reset_selected_color();
            view.get_main_window().setTitle("Color Sampler");
            view.get_color_viewer().setBackground(new Color(model.get_selected_color().get_red(), model.get_selected_color().get_green(), model.get_selected_color().get_blue()));
            view.get_redPalette_field().setText(String.valueOf(model.get_selected_color().get_red()));
            view.get_greenPalette_field().setText(String.valueOf(model.get_selected_color().get_green()));
            view.get_bluePalette_field().setText(String.valueOf(model.get_selected_color().get_blue()));

        }

    }

    public ColorApp() throws IOException {

        application_model Model = new application_model("colors.txt");
        application_view View = new application_view();
        application_controller Controller = new application_controller(Model, View);
        Controller.initialize_controller();

    }

    public static void main(String[] args) throws IOException {

        ColorApp application = new ColorApp();

    }

}