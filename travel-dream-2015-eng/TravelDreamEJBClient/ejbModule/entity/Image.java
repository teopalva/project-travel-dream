package entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the IMAGE database table.
 * 
 */
@Entity
@NamedQuery(name="Image.findAll", query="SELECT i FROM Image i")
public class Image implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Lob
	private byte[] data;

	//bi-directional many-to-one association to Package
	@OneToMany(mappedBy="image")
	private List<Package> packages;

	public Image() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte[] getData() {
		return this.data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public List<Package> getPackages() {
		return this.packages;
	}

	public void setPackages(List<Package> packages) {
		this.packages = packages;
	}

	public Package addPackage(Package _package) {
		getPackages().add(_package);
		_package.setImage(this);

		return _package;
	}

	public Package removePackage(Package _package) {
		getPackages().remove(_package);
		_package.setImage(null);

		return _package;
	}

}