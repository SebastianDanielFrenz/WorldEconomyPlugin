package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.server;

import javax.swing.JFrame;
import javax.swing.JProgressBar;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading.SQLHandlerThread;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading.tasking.TaskProcessor;

import javax.swing.JLabel;

public class PerformanceGUI extends JFrame {

	Thread bg_fetcher;

	JProgressBar[] main_bars;

	public PerformanceGUI() {
		getContentPane().setLayout(null);

		JProgressBar SQLThreadLoad = new JProgressBar();
		SQLThreadLoad.setBounds(97, 11, 77, 14);
		SQLThreadLoad.setMaximum(100);
		getContentPane().add(SQLThreadLoad);

		JLabel lblSqlThread = new JLabel("SQL Thread");
		lblSqlThread.setBounds(10, 11, 77, 14);
		getContentPane().add(lblSqlThread);

		JLabel lblBackgroundThreads = new JLabel("Background Threads");
		lblBackgroundThreads.setBounds(10, 36, 164, 14);
		getContentPane().add(lblBackgroundThreads);

		JLabel lbl;
		JProgressBar progressBar;

		main_bars = new JProgressBar[TaskProcessor.getThreads()];

		for (int i = 0; i < TaskProcessor.getThreads(); i++) {
			lbl = new JLabel("Thread #" + i);
			lbl.setBounds(10, 61 + 25 * i, 77, 14);
			getContentPane().add(lbl);

			progressBar = new JProgressBar();
			progressBar.setBounds(97, 61 + 25 * i, 67, 14);
			progressBar.setMaximum(100);
			getContentPane().add(progressBar);

			main_bars[i] = progressBar;
		}

		JLabel lblThread = new JLabel("Thread #1");
		lblThread.setBounds(10, 61, 77, 14);
		getContentPane().add(lblThread);

		JLabel label = new JLabel("Thread #2");
		label.setBounds(10, 86, 77, 14);
		getContentPane().add(label);

		JProgressBar progressBar_2 = new JProgressBar();
		progressBar_2.setBounds(97, 86, 67, 14);
		getContentPane().add(progressBar_2);

		setSize(800, 600);
		setVisible(true);

		bg_fetcher = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(1000 / 120);
					} catch (InterruptedException e) {
						break;
					}
					SQLHandlerThread.requestData(Thread.currentThread());
					SQLThreadLoad.setValue(
							(int) (SQLHandlerThread.getWorkingTime() / (SQLHandlerThread.getIdleTime() + SQLHandlerThread.getWorkingTime() * 100)));

					SQLHandlerThread.continueWork();

					int[] loads = TaskProcessor.getStatus().getLoads();

					for (int i = 0; i < TaskProcessor.getThreads(); i++) {
						main_bars[i].setValue(loads[i]);
					}

					repaint();
				}
			}
		});
		bg_fetcher.start();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		System.out.println("disposing GUI");
		bg_fetcher.interrupt();

		super.dispose();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -1328502816137425725L;
}
