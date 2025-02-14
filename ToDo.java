import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class ToDo extends JFrame {

    private JTextField taskField;
    private DefaultListModel<String> taskListModel;
    private JList<String> taskList;

    public ToDo() {
        setTitle("To-Do List Application");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.PINK);
        setLayout(new BorderLayout());

        taskField = new JTextField(20);
        taskField.addActionListener(new AddTaskAction());  // Allow adding task with Enter key

        // Task list model and list
        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JButton addButton = new JButton("Add Task");
        JButton removeButton = new JButton("Remove Task");

        addButton.addActionListener(new AddTaskAction());
        removeButton.addActionListener(new RemoveTaskAction());

        addButton.setEnabled(!taskField.getText().isEmpty());
        taskField.addCaretListener(e -> addButton.setEnabled(!taskField.getText().isEmpty()));

        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(Color.PINK);
        inputPanel.add(taskField);
        inputPanel.add(addButton);
        inputPanel.add(removeButton);

        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(taskList), BorderLayout.CENTER);
        
        JPanel frameBorder = new JPanel();
        frameBorder.setBackground(Color.PINK);
        frameBorder.setBorder(BorderFactory.createLineBorder(Color.RED, 5));
        add(frameBorder, BorderLayout.CENTER);
    }

    private class AddTaskAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String task = taskField.getText().trim();
            if (!task.isEmpty()) {
                taskListModel.addElement(task);
                JOptionPane.showMessageDialog(ToDo.this, "Task added: " + task);
                taskField.setText("");
            }
        }
    }

    private class RemoveTaskAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int selectedIndex = taskList.getSelectedIndex();
            if (selectedIndex != -1) {
                String removedTask = taskListModel.getElementAt(selectedIndex);
                taskListModel.remove(selectedIndex);
                JOptionPane.showMessageDialog(ToDo.this, "Task removed: " + removedTask);
            } else {
                JOptionPane.showMessageDialog(ToDo.this, "Select a task to remove.");
            }
        }
    }

    public static void main(String[] args) {
            ToDo app = new ToDo();
            app.setVisible(true);
        };
    }
