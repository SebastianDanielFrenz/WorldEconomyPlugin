package io.github.SebastianDanielFrenz.WorldEconomyPlugin.database;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class SQLWorker implements Runnable {

	private List<String> queries = new LinkedList<String>();
	private long current_query_index = -1;
	private long last_query_index = -1;
	public int wait_nanos = 0;
	public int wait_millis = 100;

	public long queueSQL(String query) {
		queries.add(query);
		last_query_index++;
		return last_query_index;
	}
	
	public ResultSet fetchResults(long queueID) {
		
	}

	@Override
	public void run() {
		while (true) {
			while (queries.size() != 0) {

			}
		}
	}

}
