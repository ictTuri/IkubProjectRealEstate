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
	private static final String GET_TRADE_BY_ID = "FROM TradeEntity te WHERE te.tradeId = :id";
	
	private static final String GET_OWNERS_TRADE = "FROM TradeEntity te INNER JOIN PropertyEntity pe ON te.property = pe.propertiesId INNER JOIN UserEntity ue"+
	" ON ue.userId = pe.owner WHERE pe.owner= :user";
	private static final String GET_CLIENTS_TRADE = "FROM TradeEntity te WHERE te.client= :user";
	private static final String CHECK_USER_WITH_TRADE = "FROM TradeEntity te WHERE te.client = :client AND te.endTradeDate = null AND te.tradeType = rented";
	private static final String GET_TRADE_BY_PROPERTY = "FROM TradeEntity te WHERE te.property = :property";
	private static final String GET_TRADE_BY_CLIENTPROPERTY = "FROM TradeEntity te WHERE te.client = :client and te.property = :properties";
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
	
	public boolean checkClientInTrade(UserEntity user) {
		TypedQuery<TradeEntity> query = em.createQuery(CHECK_USER_WITH_TRADE, TradeEntity.class)
				.setParameter("client", user);
		try {
			return query.getSingleResult()!=null;
		}catch(NoResultException e) {
			return false;
		}
	}

	public TradeEntity getTradeByProperty(PropertyEntity property) {
		TypedQuery<TradeEntity> query = em.createQuery(GET_TRADE_BY_PROPERTY, TradeEntity.class).setParameter("property", property);
			try{
				return query.getResultList().get(0);
			}catch(IndexOutOfBoundsException e) {
				return null;
			}
	}

	public List<TradeEntity> tradesOfOwnersByOwner(UserEntity user) {
		return em.createQuery(GET_OWNERS_TRADE, TradeEntity.class).setParameter("user", user).getResultList();
	}

	public List<TradeEntity> tradesOfClientByClient(UserEntity user) {
		return em.createQuery(GET_CLIENTS_TRADE, TradeEntity.class).setParameter("user", user).getResultList();
	}
}
