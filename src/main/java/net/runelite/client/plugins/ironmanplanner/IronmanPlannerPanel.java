package net.runelite.client.plugins.ironmanplanner;

import net.runelite.client.ui.PluginPanel;

import javax.swing.*;
import java.awt.*;

public class IronmanPlannerPanel extends PluginPanel
{
    private final TaskManager taskManager;
    private final JPanel taskListPanel = new JPanel();

    public IronmanPlannerPanel(TaskManager taskManager)
    {
        this.taskManager = taskManager;

        setLayout(new BorderLayout());

        JLabel title = new JLabel("Ironman Planner");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 16));
        add(title, BorderLayout.NORTH);

        taskListPanel.setLayout(new BoxLayout(taskListPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(taskListPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane, BorderLayout.CENTER);

        loadTasks();
    }

    private void loadTasks()
    {
        taskListPanel.removeAll();

        for (Task task : taskManager.getTasks())
        {
            JPanel row = new JPanel(new BorderLayout());
            row.setBorder(BorderFactory.createEmptyBorder(2, 4, 2, 4));

            JCheckBox checkBox = new JCheckBox();
            checkBox.setSelected(task.isCompleted());
            checkBox.addActionListener(e -> taskManager.toggleTask(task));

            JTextArea label = new JTextArea(task.getName());
            label.setWrapStyleWord(true);
            label.setLineWrap(true);
            label.setEditable(false);
            label.setOpaque(false);
            label.setFont(new Font("SansSerif", Font.PLAIN, 12));
            label.setBorder(null);
            label.setAlignmentY(Component.TOP_ALIGNMENT);

            row.add(checkBox, BorderLayout.WEST);
            row.add(label, BorderLayout.CENTER);

            taskListPanel.add(row);
        }

        taskListPanel.revalidate();
        taskListPanel.repaint();
    }
}
