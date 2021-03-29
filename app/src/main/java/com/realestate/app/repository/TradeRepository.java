package com.realestate.app.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.realestate.app.entity.PropertyEntity;
import com.realestate.app.entity.TradeEntity;
import com.realestate.app.entity.UserEntity;

@Repository
@Transactional
public class TradeRepository {
	EntityManager em;

	public TradeRepository(EntityManager em) {
		super();
		this.em = em;
	}

	private static final String GET_ALL_TRADES = "FROM TradeEntity";
	private static final String GET_TRADE_BY_ID = "FROM TradeEntity t WHERE t.tradeId = :id";
	
	private static final String GET_TRADE_BY_CLIENTPROPERTY = "FROM TradeEntity t WHERE t.client = :client and t.property = :properties";
	private static final String PROPERTY_RENTED_OR_BOUGHT = "FROM TradeEntity te WHERE te.property = :id and te.endTradeDate = null";
	
	// RETRIEVE OPERATIONS DOWN HERE
	// TRADES
	public List<TradeEntity> getAllTrades() {
		return em.createQuery(GET_ALL_TRADES, TradeEntity.class).getResultList();
	}

	public TradeEntity getTradeById(Integer id) {
		TypedQuery<TradeEntity> query = em.createQuery(GET_TRADE_BY_ID, TradeEntity.class).setParameter("id", id);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	// INSERT OPERATIONS DOWN HERE
	public void insertTrade(TradeEntity trade) {
		em.persist(trade);
	}

	// UPDATE OPERATIONS DOWN HERE
	public TradeEntity updateTrade(TradeEntity trade) {
		return em.merge(trade);
	}

	// DELETE OPERATIONS DOWN HERE
	public void deleteTrade(TradeEntity trade) {
		em.remove(trade);
	}

	//HELPING METHODS DOWN HERE
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

	public boolean isInRentedStatus(PropertyEntity property) {
		TypedQuery<TradeEntity> query = em.createQuery(PROPERTY_RENTED_OR_BOUGHT, TradeEntity.class)
				.setParameter("id", property);
		try {
			return query.getSingleResult()!=null;
		}catch(NoResultException e) {
			return false;
		}
	}
}
