package se.yrgo.services.diary;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.yrgo.domain.Action;

@Service
@Transactional
public class DiaryManagementServiceMockImpl implements DiaryManagementService {
	
	private Set<Action>allActions= new HashSet<Action>();

	@Override
	public void recordAction(Action action) {
        allActions.add(action);
		// TODO Auto-generated method stub

	}

	//Hint: 
	//Create a list<Action>
	//In the for each loop going through the list use this condition: "if(action.getOwningUser().equals(requiredUser) && !action.isComplete())" to add a new action to the list. 
	public List<Action> getAllIncompleteActions(String requiredUser) {
        List<Action> incomplete = new ArrayList<>();
        for(Action action : allActions){
            if(action.getOwningUser().equals(requiredUser) && !action.isComplete()){
                incomplete.add(action);
            }
        }
		// TODO Auto-generated method stub
		return incomplete;
	}

}
