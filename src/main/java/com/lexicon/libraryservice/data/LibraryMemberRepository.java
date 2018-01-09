package com.lexicon.libraryservice.data;

	import javax.enterprise.context.ApplicationScoped;
	import javax.inject.Inject;
	import javax.persistence.EntityManager;
	import javax.persistence.criteria.CriteriaBuilder;
	import javax.persistence.criteria.CriteriaQuery;
	import javax.persistence.criteria.Root;

	import java.util.List;

	import com.lexicon.libraryservice.model.LibraryMember;

	  @ApplicationScoped
      public class LibraryMemberRepository {
		//public class MemberRepository {

	    @Inject
	    private EntityManager em;
	    
	    public void register(LibraryMember member) {
	        em.persist(member);	        
	    }

	    public LibraryMember findById(Long id) {
	        return em.find(LibraryMember.class, id);
	    }

	    public LibraryMember findByEmail(String email) {
	        CriteriaBuilder cb = em.getCriteriaBuilder();
	        CriteriaQuery<LibraryMember> criteria = cb.createQuery(LibraryMember.class);
	        Root<LibraryMember> member = criteria.from(LibraryMember.class);
	        criteria.select(member).where(cb.equal(member.get("email"), email));
	        return em.createQuery(criteria).getSingleResult();
	    }

	    public List<LibraryMember> findAllOrderedByName() {
	        CriteriaBuilder cb = em.getCriteriaBuilder();
	        CriteriaQuery<LibraryMember> criteria = cb.createQuery(LibraryMember.class);
	        Root<LibraryMember> member = criteria.from(LibraryMember.class);
	        criteria.select(member).orderBy(cb.asc(member.get("name")));
	        return em.createQuery(criteria).getResultList();
	    }
	}
	
