package com.realestate.app.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.realestate.app.entity.IssuesEntity;
import com.realestate.app.entity.PropertyEntity;
import com.realestate.app.entity.UserEntity;
import com.realestate.app.repository.IssueRepository;

@Repository
public class IssueRepositoryImpl implements IssueRepository {
	EntityManager em;

	public IssueRepositoryImpl(EntityManager em) {
		super();
		this.em = em;
	}

	private static final String GET_ISSUE_BY_ID = "FROM IssuesEntity ie WHERE ie.issueId = :id";

	private static final String GET_ALL_ISSUES = "FROM IssuesEntity";
	private static final String EXIST_ISSUE_FOR_POPERTY = "FROM IssuesEntity ie WHERE ie.property = :property";
	private static final String GET_OWNER_RELATED_ISSUES = "SELECT ie FROM IssuesEntity ie LEFT JOIN PropertyEntity pe ON ie.property=pe.propertiesId"
			+ " INNER JOIN UserEntity ue ON pe.owner=ue.userId WHERE pe.owner = :owner";

	private static final String GET_CLIENT_RELATED_ISSUES = "FROM IssuesEntity ie WHERE ie.client = :client";
	private static final String EXIST_ISSUE_FOR_PROPERTY = "SELECT ie FROM IssuesEntity ie WHERE ie.client = :client AND ie.property = :property AND (ie.resoulutionStatus"
			+ "= 'UNCHECKED' OR ie.resoulutionStatus = 'REVIEWED' OR ie.resoulutionStatus = 'ON_PROGRESS')";

	// RETRIEVE OPERATIONS DOWN HERE
	@Override
	public List<IssuesEntity> getAllIssues() {
		return em.createQuery(GET_ALL_ISSUES, IssuesEntity.class).getResultList();
	}

	@Override
	public IssuesEntity getIssueById(Integer issueId) {
		TypedQuery<IssuesEntity> query = em.createQuery(GET_ISSUE_BY_ID, IssuesEntity.class).setParameter("id",
				issueId);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	// INSERT OPERATIONS DOWN HERE
	@Override
	public void insertIssue(IssuesEntity issue) {
		em.persist(issue);
	}

	// UPDATE OPERATIONS DOWN HERE
	@Override
	public IssuesEntity updateIssue(IssuesEntity issue) {
		return em.merge(issue);
	}

	// DELETE OPERATIONS DOWN HERE
	@Override
	public void deleteIssue(IssuesEntity issue) {
		em.remove(issue);
	}

	// HELPING METHOD DOWN HERE
	@Override
	public IssuesEntity existIssueWithProperty(PropertyEntity property) {
		TypedQuery<IssuesEntity> query = em.createQuery(EXIST_ISSUE_FOR_POPERTY, IssuesEntity.class)
				.setParameter("property", property);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public List<IssuesEntity> issuesOfOwnersByOwner(UserEntity user) {
		return em.createQuery(GET_OWNER_RELATED_ISSUES, IssuesEntity.class).setParameter("owner", user).getResultList();
	}

	@Override
	public List<IssuesEntity> issuesOfClientByClient(UserEntity user) {
		return em.createQuery(GET_CLIENT_RELATED_ISSUES, IssuesEntity.class).setParameter("client", user)
				.getResultList();
	}

	@Override
	public boolean existIssueForProperty(UserEntity user, PropertyEntity property) {
		TypedQuery<IssuesEntity> query = em.createQuery(EXIST_ISSUE_FOR_PROPERTY, IssuesEntity.class)
				.setParameter("client", user).setParameter("property", property);
		try {
			return query.getSingleResult() != null;
		} catch (NoResultException e) {
			return false;
		}
	}
}
