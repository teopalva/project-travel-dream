package bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import dto.BaseProductDTO;
import dto.BuyingListItemDTO;
import dto.PackageDTO;
import dto.UserDTO;

@ManagedBean(name = "SessionStorage")
@SessionScoped
public class SessionStorageBean implements Serializable {
    private static final long serialVersionUID = -339896506641421282L;

    /**
     * The package selected by the user for editing
     */
    private PackageDTO selectedPackage;
    private BaseProductDTO selectedProduct;
    private BuyingListItemDTO selectedItem;
    private String previousPage = null;
    private String departurePlace = "";
    private String arrivalPlace = ""; // To be set directly from home.jsf when filling search form
    private String hash;
    private UserDTO tmpUser;

    public PackageDTO getSelectedPackage() {
	return selectedPackage;
    }

    public void setSelectedPackage(PackageDTO selectedPackage) {
	this.selectedPackage = selectedPackage;
    }

    public BaseProductDTO getSelectedProduct() {
	return selectedProduct;
    }

    public void setSelectedProduct(BaseProductDTO selectedProduct) {
	this.selectedProduct = selectedProduct;
    }

    public BuyingListItemDTO getSelectedItem() {
	return selectedItem;
    }

    public void setSelectedItem(BuyingListItemDTO selectedItem) {
	this.selectedItem = selectedItem;
    }

    public String getPreviousPage() {
	return previousPage;
    }

    /**
     * Possible pages: "gift_user", "gift_friend", "invitation", "edit"
     */
    public void setPreviousPage(String page) {
	previousPage = page;
    }

    public String getDeparturePlace() {
	return departurePlace;
    }

    public void setDeparturePlace(String departurePlace) {
	this.departurePlace = departurePlace;
    }

    public String getArrivalPlace() {
	return arrivalPlace;
    }

    public void setArrivalPlace(String arrivalPlace) {
	this.arrivalPlace = arrivalPlace;
    }

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public UserDTO getTmpUser() {
		return tmpUser;
	}

	public void setTmpUser(UserDTO tmpUser) {
		this.tmpUser = tmpUser;
	}
}
