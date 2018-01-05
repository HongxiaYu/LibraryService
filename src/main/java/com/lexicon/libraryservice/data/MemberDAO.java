package com.lexicon.libraryservice.data;

	import javax.ejb.Stateless;
	import javax.inject.Inject;
	
	import javax.persistence.EntityManager;
	import javax.persistence.criteria.CriteriaBuilder;
	import javax.persistence.criteria.CriteriaQuery;
	import javax.persistence.criteria.Root;

import com.lexicon.libraryservice.model.LMember;

import java.util.List;

      @Stateless
      public class MemberDAO implements MemebersDAOInterface {

	    @Inject
	    private EntityManager em;

	 
	    public LMember findById(Long id) {
	        return em.find(LMember.class, id);
	    }
	    
	    public LMember findByEmail(String email) {
	        CriteriaBuilder cb = em.getCriteriaBuilder();
	        CriteriaQuery<LMember> criteria = cb.createQuery(LMember.class);
	        Root<LMember> member = criteria.from(LMember.class);
	        criteria.select(member).where(cb.equal(member.get("email"), email));
	        return em.createQuery(criteria).getSingleResult();
	    }

	    public List<LMember> findAllOrderedByName() {
	        CriteriaBuilder cb = em.getCriteriaBuilder();
	        CriteriaQuery<LMember> criteria = cb.createQuery(LMember.class);
	        Root<LMember> member = criteria.from(LMember.class);
	        criteria.select(member).orderBy(cb.asc(member.get("name")));
	        return em.createQuery(criteria).getResultList();
	    }
	
		@Override
		public void persistMember(LMember member) {
			em.persist(member);
		}
	    
	    @Override
	    public boolean contains(long id) {
	    	LMember member = em.find(LMember.class, id);
			return member != null;	    	
		};
	}
	
