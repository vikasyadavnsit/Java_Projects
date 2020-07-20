package com.battleramp.concurrency.utility;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;

public class ForkJoinRecursiveTask extends RecursiveTask<List<Integer>> {

	private static final long serialVersionUID = 1L;
	private static final int THRESHOLD = 10_000;
	private long workLoad = 0;
	List<Integer> list;

	public ForkJoinRecursiveTask(long workLoad, List<Integer> list) {
		this.workLoad = workLoad;
		this.list = list;
	}

	@Override
	protected List<Integer> compute() {
		if (this.workLoad > THRESHOLD) {

//			List<ForkJoinRecursiveTask> subtasks = new ArrayList<ForkJoinRecursiveTask>();
//			subtasks.addAll(createSubtasks());
//			for (ForkJoinRecursiveTask subtask : subtasks)
//				subtask.fork();
//
//			List<Integer> result = new ArrayList<>();
//			for (ForkJoinRecursiveTask subtask : subtasks)
//				result.addAll(subtask.join());

			return ForkJoinTask.invokeAll(createSubtasks()).stream().map(x -> x.join()).flatMap(x -> x.stream())
					.collect(Collectors.toList());
		} else {
			return process(list);
		}
	}

	private List<ForkJoinRecursiveTask> createSubtasks() {
		List<ForkJoinRecursiveTask> subtasks = new ArrayList<>();
		subtasks.add(new ForkJoinRecursiveTask(workLoad / 2, list.subList(0, (int) workLoad / 2)));
		subtasks.add(new ForkJoinRecursiveTask(workLoad / 2, list.subList((int) workLoad / 2, list.size())));
		return subtasks;
	}

	private List<Integer> process(List<Integer> subList) {
		return subList.stream().filter(x -> x >= 99_999).filter(x -> x % 99_999 == 0).collect(Collectors.toList());
	}
}
