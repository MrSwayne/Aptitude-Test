package ie.swayne.test.quiz;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class QuestionSet extends ArrayList<TestQuestion> {

	
	public void addAll(QuestionSet set) {
		for(int i = 0;i < set.size();i++)
			this.add(set.get(i));
	}
}
