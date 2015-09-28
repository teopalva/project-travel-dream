package coreEJB;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dto.BuyingListItemDTO;
import dto.PackageDTO;
import dto.UserDTO;
import entity.BuyingListItem;
import entity.BuyingListPK;
import entity.Invitation;
import entity.Package;
import entity.User;
import exceptions.FieldNotPresentException;
import exceptions.NotValidBuyingListException;
import exceptions.NotValidPackageException;
import exceptions.NotValidUserException;

/**
 * Session Bean implementation class BuyingListItemEJB
 */
@Stateful
public class BuyingListItemEJB implements BuyingListItemEJBLocal {
	
	@PersistenceContext
	EntityManager em;
	
	@EJB
	PackageEJB packageEJB;
	
	BuyingListItemDTO tmpBuyingListItem = null;

    public BuyingListItemEJB() {
    }
    
    /**
     * Required packageDTO, userDTO inside buyingListItemDTO (you must specify also the date)
     */
    public void saveBuyingListItem(BuyingListItemDTO buyingListItemDTO) throws NotValidBuyingListException {
    	if(packageEJB.isValidForTDC(buyingListItemDTO.get_package()) == false) {
    		//The package is not valid
    		System.err.println("The Package can't be bought because it is not valid");
    		throw new NotValidBuyingListException();
    	}
    	
    	PackageDTO packageDTO = buyingListItemDTO.get_package();
    	UserDTO userDTO = buyingListItemDTO.getUser();
    	if(userDTO == null || packageDTO == null)
    		throw new NotValidBuyingListException();
    	
    	//Extract from DB
    	Package _package = em.find(Package.class, packageDTO.getId());
    	User user = em.find(User.class, userDTO.getMail());
    	if(user == null)
    		throw new NotValidBuyingListException();
    	
    	if(_package == null) {
    		try {
				packageEJB.savePackage(packageDTO);
				_package = em.find(Package.class, packageDTO.getId());
			} catch (NotValidPackageException e) {
				e.printStackTrace();
				throw new NotValidBuyingListException();
			}
    	}
    	
    	BuyingListItem item = null;
    	try {
    		item = (BuyingListItem)em.createNativeQuery("SELECT * FROM BUYING_LIST_ITEM WHERE PackageId='"+_package.getId()+"' AND UserIdBuyingList='"+user.getMail()+"'", BuyingListItem.class).getResultList().get(0);
    	} catch(IndexOutOfBoundsException e) {}	//No problem
    	
    	if(item == null) {
    		//Build the item from scratch
    		item = new BuyingListItem();
    		item.setUser(user);
    		item.set_package(_package);
    		item.setDate(buyingListItemDTO.getDate());
    		item.setPaid(buyingListItemDTO.isPaid());
    		item.setGifted(buyingListItemDTO.isGifted());
    		em.persist(item);
    	}
    	else {
    		item.setPaid(buyingListItemDTO.isPaid());
    		item.setGifted(buyingListItemDTO.isGifted());
    		em.merge(item);
    	}
    	
    	//Delete all invitation on this package
    	List<Invitation> invitations = em.createNativeQuery("SELECT * FROM INVITATION WHERE PackageId='"+_package.getId()+"'", Invitation.class).getResultList();
    	for(Invitation i : invitations) {
    		em.remove(i);
    	}
    	em.flush();
    }
    
	public void removeBuyingListItem(BuyingListItemDTO buyingListItemDTO) throws NotValidBuyingListException {
		PackageDTO packageDTO = buyingListItemDTO.get_package();
    	UserDTO userDTO = buyingListItemDTO.getUser();
    	if(userDTO == null || packageDTO == null)
    		throw new NotValidBuyingListException();
    	
    	//Extract from DB
    	Package _package = em.find(Package.class, packageDTO.getId());
    	User user = em.find(User.class, userDTO.getMail());
    	if(user == null || _package == null)
    		throw new NotValidBuyingListException();
    	
    	BuyingListItem item = null;
    	try {
    		item = (BuyingListItem)em.createNativeQuery("SELECT * FROM BUYING_LIST_ITEM WHERE PackageId='"+_package.getId()+"' AND UserIdBuyingList='"+user.getMail()+"'", BuyingListItem.class).getResultList().get(0);
    	} catch(IndexOutOfBoundsException e) {
    		throw new NotValidBuyingListException();
    	}
    	em.remove(item);
	}
	
	public List<BuyingListItemDTO> getAllBuyingListItem() {
		List<BuyingListItemDTO> list = new ArrayList<BuyingListItemDTO>();

		List<BuyingListItem> items = new ArrayList<BuyingListItem>();
		items = em.createNativeQuery("SELECT * FROM BUYING_LIST_ITEM", BuyingListItem.class).getResultList();

		for(BuyingListItem item : items) {
			try {
				list.add(new BuyingListItemDTO(item));
			} catch (FieldNotPresentException e) {
				e.printStackTrace();
			}
		}

		return list;
	}
	
	public List<BuyingListItemDTO> getBuyingListItem(UserDTO userDTO) throws NotValidUserException {
		List<BuyingListItemDTO> list = new ArrayList<BuyingListItemDTO>();
    	
		if(userDTO == null || userDTO.getMail() == null)
			throw new NotValidUserException();
		
    	//Extract from DB
    	User user = em.find(User.class, userDTO.getMail());
    	if(user == null)
    		throw new NotValidUserException();
    	
    	List<BuyingListItem> items = new ArrayList<BuyingListItem>();
    	items = em.createNativeQuery("SELECT * FROM BUYING_LIST_ITEM WHERE UserIdBuyingList='"+user.getMail()+"'", BuyingListItem.class).getResultList();
    	
    	for(BuyingListItem item : items) {
    		try {
				list.add(new BuyingListItemDTO(item));
			} catch (FieldNotPresentException e) {
				e.printStackTrace();
			}
    	}
    	
    	return list;
	}
	
	public void setPaid(BuyingListItemDTO itemDTO) throws NotValidBuyingListException{
		//itemDTO.setPaid(true);
		//saveBuyingListItem(itemDTO);
		try {
			em.createNativeQuery("UPDATE BUYING_LIST_ITEM SET Paid='1' WHERE PackageId='"+itemDTO.get_package().getId()+"'").executeUpdate();
			em.flush();
			em.getEntityManagerFactory().getCache().evictAll();
			em.flush();
		} catch (Exception e) {
			e.printStackTrace();
			throw new NotValidBuyingListException();
		}
	}

	public BuyingListItemDTO getTmpBuyingListItem() {
		return tmpBuyingListItem;
	}

	public void setTmpBuyingListItem(BuyingListItemDTO tmpBuyingListItem) {
		this.tmpBuyingListItem = tmpBuyingListItem;
	}
	
	

}
