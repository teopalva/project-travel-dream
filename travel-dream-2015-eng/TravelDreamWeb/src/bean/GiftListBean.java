package bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import coreEJB.AuthenticationEJBLocal;
import coreEJB.GiftListItemEJBLocal;
import coreEJB.UserEJBLocal;
import dto.GiftListItemDTO;
import dto.PackageDTO;
import dto.UserDTO;
import exceptions.NotAuthenticatedException;
import exceptions.NotPresentUserException;
import exceptions.NotValidUserException;
import exceptions.PackageNotValidException;

@ManagedBean(name = "GiftList")
@ViewScoped
public class GiftListBean {
	private UserDTO user;
	private String friendMail = "";

	@EJB
	private GiftListItemEJBLocal giftListEJB;

	@EJB
	private AuthenticationEJBLocal authEJB;

	@EJB
	private UserEJBLocal userEJB;

	@ManagedProperty("#{SessionStorage}")
	private SessionStorageBean sessionStorage;

	@PostConstruct
	public void init() {
		try {
			user = authEJB.getAuthenticatedUser();
		} catch (NotAuthenticatedException e) {
			// No problem: user area
		}
	}

	public SessionStorageBean getSessionStorage() {
		return sessionStorage;
	}

	public void setSessionStorage(SessionStorageBean sessionStorage) {
		this.sessionStorage = sessionStorage;
	}

	public String getFriendMail() {
		return friendMail;
	}

	public void setFriendMail(String friendMail) {
		this.friendMail = friendMail;
	}

	/**
	 * @return the user's or friend's gift list
	 * @throws NotValidUserException
	 */
	public List<GiftListItemDTO> retrieveGiftList() throws NotValidUserException {

		if (friendMail.equals(""))
			return (giftListEJB.getGiftListItem(user));

		else {

			try {
				UserDTO friendUser = new UserDTO(friendMail, null, null, null, null);
				return (giftListEJB.getGiftListItem(friendUser));
			} catch (NotValidUserException e) {
				friendMail = "";
				FacesContext.getCurrentInstance().addMessage("alertMail",
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Utente non trovato", "La mail che hai inserito non corrisponde a nessun utente"));

				System.err.print("NotValidUserException");
				e.printStackTrace();
				return (giftListEJB.getGiftListItem(user));

			}
		}

	}


	/**
	 * 
	 * @return the name of the friend searched by mail
	 * @throws NotAuthenticatedException
	 * @throws NotPresentUserException
	 */
	public String retrieveTitle() {
		if (friendMail.equals("")) {
			return "My Gift List";
		} else {
			return friendMail + "'s Gift List";
		}

	}

	/**
	 * 
	 * @param p the selected PackageDTO
	 * @return the checkout page URL
	 * @throws NotValidUserException
	 */
	public String showCheckout(PackageDTO p) throws NotValidUserException {
		sessionStorage.setSelectedPackage(p);
		UserDTO friend = null;
		try {
		    friend = userEJB.getUser(friendMail);
		} catch (NotPresentUserException e) {
		    //no problem
		}
		if (friendMail.equals(user.getMail()) && friend!=null) {
			sessionStorage.setPreviousPage("gift_user");
			sessionStorage.setTmpUser(null);
		} else {
			sessionStorage.setPreviousPage("gift_friend");
			sessionStorage.setTmpUser(friend);
		}
		return "/user/checkout?faces-redirect=true";
	}

}
