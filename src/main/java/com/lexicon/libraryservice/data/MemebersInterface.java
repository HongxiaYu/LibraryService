package com.lexicon.libraryservice.data;

import java.util.List;

import com.lexicon.libraryservice.model.LMember;

public interface MemebersInterface {
   LMember findById(Long id);
        
   LMember findByEmail(String email);

   List<LMember> findAllOrderedByName();
    
	void persistMember(LMember member);
	
    boolean contains(long id);
}
