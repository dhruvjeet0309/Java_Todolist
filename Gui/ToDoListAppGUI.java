import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class Task {
    private String name;
    private boolean isCompleted;

    public Task(String name) {
        this.name = name;
        this.isCompleted = false;
    }

    public void markAsCompleted() {
        this.isCompleted = true;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    @Override
    public String toString() {
        return (isCompleted ? "[Done] " : "[Pending] ") + name;
    }
}

public class ToDoListAppGUI extends JFrame {
    private ArrayList<Task> tasks;
    private DefaultListModel<String> taskListModel;
    private JList<String> taskList;

    public ToDoListAppGUI() {
        // Initialize the task list
        tasks = new ArrayList<>();
        taskListModel = new DefaultListModel<>();

        // Set up the JFrame
        setTitle("To-Do List Application");
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create the task display area
        taskList = new JList<>(taskListModel);
        JScrollPane scrollPane = new JScrollPane(taskList);
        add(scrollPane, BorderLayout.CENTER);

        // Change background color of the task list
        taskList.setBackground(Color.WHITE); // Set background for task list

        // Create input field and buttons
        JTextField taskInputField = new JTextField(15);
        JButton addButton = new JButton("Add Task");
        JButton removeButton = new JButton("Remove Task");
        JButton markCompleteButton = new JButton("Mark as Completed");

        // Change background colors of buttons and input field
        taskInputField.setBackground(Color.WHITE); // Set background for input field
        addButton.setBackground(Color.GREEN); // Set background for add button
        removeButton.setBackground(Color.RED); // Set background for remove button
        markCompleteButton.setBackground(Color.YELLOW); // Set background for complete button

        // Set up the control panel and change its background
        JPanel controlPanel = new JPanel();
        controlPanel.setBackground(Color.CYAN); // Set background for control panel
        controlPanel.add(taskInputField);
        controlPanel.add(addButton);
        controlPanel.add(removeButton);
        controlPanel.add(markCompleteButton);
        add(controlPanel, BorderLayout.SOUTH);

        // Add ActionListener for adding tasks
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String taskName = taskInputField.getText();
                if (!taskName.isEmpty()) {
                    Task newTask = new Task(taskName);
                    tasks.add(newTask);
                    taskListModel.addElement(newTask.toString());
                    taskInputField.setText("");
                }
            }
        });

        // Add ActionListener for removing tasks
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = taskList.getSelectedIndex();
                if (selectedIndex != -1) {
                    tasks.remove(selectedIndex);
                    taskListModel.remove(selectedIndex);
                }
            }
        });

        // Add ActionListener for marking tasks as completed
        markCompleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = taskList.getSelectedIndex();
                if (selectedIndex != -1) {
                    Task task = tasks.get(selectedIndex);
                    task.markAsCompleted();
                    taskListModel.set(selectedIndex, task.toString());
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ToDoListAppGUI app = new ToDoListAppGUI();
            app.setVisible(true);
        });
    }
}
