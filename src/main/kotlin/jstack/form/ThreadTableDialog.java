package jstack.form;

import com.intellij.ui.JBSplitter;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.table.JBTable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.lang.management.ThreadInfo;
import java.util.Map;

public class ThreadTableDialog extends JDialog {

	private JPanel contentPane = new JPanel();
	private JButton buttonOK = new JButton();
	private JButton buttonCancel = new JButton();

	public ThreadTableDialog(Map<Long, ThreadInfo> data) {
		setContentPane(contentPane);
		setModal(true);
		getRootPane().setDefaultButton(buttonOK);

		buttonOK.addActionListener(e -> onOK());

		buttonCancel.addActionListener(e -> onCancel());

		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				onCancel();
			}
		});

		contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

		Box box = Box.createVerticalBox();
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setPreferredSize(new Dimension(500, 650));
		data.entrySet().forEach(entry -> {
			JPanel panel = new JPanel();
			panel.add(new JLabel("Thread #: " + entry.getKey().toString()));
			panel.add(createTable(entry.getValue()));
			box.add(panel);
			box.add(Box.createVerticalStrut(10));
		});
		scrollPane.add(box);
		contentPane.add(scrollPane);
	}

	private JTable createTable(ThreadInfo info) {
		JBTable table = new JBTable(new DefaultTableModel(new Object[]{"Param", "Value"}, 0));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.addRow(new Object[]{"Thread name", info.getThreadName()});
		model.addRow(new Object[]{"Blocked count", info.getBlockedCount()});
		model.addRow(new Object[]{"Lock name", info.getLockName()});
		model.addRow(new Object[]{"Waited time", info.getWaitedTime()});
		return table;
	}


	private void onOK() {
		dispose();
	}

	private void onCancel() {
		dispose();
	}
}
