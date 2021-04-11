package com.realestate.app.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.realestate.app.entity.PropertyEntity;
import com.realestate.app.entity.TradeEntity;
import com.realestate.app.entity.UserEntity;
import com.realestate.app.repository.TradeRepository;

@Repository
@Transactional
public class TradeRepositoryImpl implements TradeRepository{
	EntityManager em;

	public TradeRepositoryImpl(EntityManager em) {
		super();
		this.em = em;
	}

	private static final String GET_ALL_TRADES = "FROM TradeEntity";
	
	private static final String GET_OWNERS_TRADE = "SELECT te FROM TradeEntity te LEFT JOIN PropertyEntity pe ON te.property = pe.propertiesId INNER JOIN UserEntity ue"+
	" ON ue.userId = pe.owner WHERE pe.owner= :user";
	private static final String GET_CLIENTS_TRADE = "FROM TradeEntity te WHERE te.client= :user";
	private static final String CHECK_USER_WITH_TRADE = "FROM TradeEntity te WHERE te.client = :client AND te.endTradeDate = null AND te.tradeType = RENTED";
	private static final String GET_TRADE_BY_PROPERTY = "FROM TradeEntity te WHERE te.property = :property";
	private static final String GET_TRADE_BY_CLIENTPROPERTY = "FROM TradeEntity te WHERE te.client = :client and te.property = :properties";
	private static final String PROPERTY_RENTED_OR_BOUGHT = "FROM TradeEntity te WHERE te.property = :id and te.endTradeDate = null";
	
	// RETRIEVE OPERATIONS DOWN HERE
	@Override
	public List<TradeEntity> getAllTrades() {
		return em.createQuery(GET_ALL_TRADES, TradeEntity.class).getResultList();
	}

	@Override
	public TradeEntity getTradeById(Integer tradeId) {
		try {
			return em.find(TradeEntity.class, tradeId);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	// INSERT OPERATIONS DOWN HERE
	@Override
	public void insertTrade(TradeEntity trade) {
		em.persist(trade);
	}

	// UPDATE OPERATIONS DOWN HERE
	@Override
	public TradeEntity updateTrade(TradeEntity trade) {
		return em.merge(trade);
	}

	// DELETE OPERATIONS DOWN HERE
	@Override
	public void deleteTrade(TradeEntity trade) {
		em.remove(trade);
	}

	//HELPING METHODS DOWN HERE
	@Override
	public boolean existTrade(UserEntity client, PropertyEntity property) {
		TypedQuery<TradeEntity> query = em.createQuery(GET_TRADE_BY_CLIENTPROPERTY,TradeEntity.class)
				.setParameter("client", client)
				.setParameter("properties", property);
		try {
			return query.getSingleResult()!=null;
		}catch(NoResultException e) {
			return false;
		}
	}

	@Override
	public boolean isInRentedStatus(PropertyEntity property) {
		TypedQuery<TradeEntity> query = em.createQuery(PROPERTY_RENTED_OR_BOUGHT, TradeEntity.class)
				.setParameter("id", property);
		try {
			return query.getSingleResult()!=null;
		}catch(NoResultException e) {
			return false;
		}
	}
	
	@Override
	public boolean checkClientInTrade(UserEntity user) {
		TypedQuery<TradeEntity> query = em.createQuery(CHECK_USER_WITH_TRADE, TradeEntity.class)
				.setParameter("client", user);
		try {
			return query.getSingleResult()!=null;
		}catch(NoResultException e) {
			return false;
		}
	}

	@Override
	public TradeEntity getTradeByProperty(PropertyEntity property) {
		TypedQuery<TradeEntity> query = em.createQuery(GET_TRADE_BY_PROPERTY, TradeEntity.class).setParameter("property", property);
			try{
				return query.getResultList().get(0);
			}catch(IndexOutOfBoundsException e) {
				return null;
			}
	}

	@Override
	public List<TradeEntity> tradesOfOwnersByOwner(UserEntity user) {
		return em.createQuery(GET_OWNERS_TRADE, TradeEntity.class).setParameter("user", user).getResultList();
	}

	@Override
	public List<TradeEntity> tradesOfClientByClient(UserEntity user) {
		return em.createQuery(GET_CLIENTS_TRADE, TradeEntity.class).setParameter("user", user).getResultList();
	}
}
