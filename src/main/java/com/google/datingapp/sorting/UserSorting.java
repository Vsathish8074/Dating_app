package com.google.datingapp.sorting;

import java.util.Comparator;

import com.google.datingapp.dto.MatchingUser;

public  class UserSorting implements Comparator<MatchingUser>{

	@Override
	public int compare(MatchingUser o1,MatchingUser o2) {
		if(o1.getAgediff()<o2.getAgediff()) {
			return -1;
		}else if(o1.getAgediff()>o2.getAgediff()) {
			
			return 1;
		}else if(o1.getAgediff()==o2.getAgediff()) {
			
			if(o1.getMic()<o2.getMic()) {
				return 1;
			}else if(o1.getMic()>o2.getMic()) {
				return -1;
			}
		}
		return 0;
	}

}
