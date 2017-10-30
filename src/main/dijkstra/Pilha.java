package main.dijkstra;

import java.util.ArrayList;

public class Pilha {
	ArrayList<Rota> ls = new ArrayList<Rota>();

	public void push(Rota r) {
		ls.add(r);
	}

	public void pop() {
		if(ls.size() == 0)
			return;
		ls.remove(ls.size() - 1);
	}

	public Rota getLast() {
		return ls.get(ls.size() - 1);
	}

	public ArrayList<Rota> getList(){
		return ls;

	}

	public String toString() {
		StringBuilder res = new StringBuilder();
		for (Rota rota : ls) {
			res.append(rota.toString() + "\n");
		}
		return res.toString();
	}
}
