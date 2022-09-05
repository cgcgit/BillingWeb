package com.comasw.viewController.appAccessController;

import java.io.Serializable;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.jooq.Result;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import com.comasw.model.tables.pojos.VwUsers;
import com.comasw.model.tables.records.MtApplicationMenuRecord;
import com.comasw.ejb.miscelaneous.ApplicationMenuEJBLocal;

@Named
@SessionScoped
public class ApplicationMenuController implements Serializable {

	private static final long serialVersionUID = 1398156738340556972L;

	//protected static ResourceBundle generalProperties = ResourceBundle.getBundle("com.comasw.properties.general");
	protected static ResourceBundle urlProperties = ResourceBundle.getBundle("com.comasw.properties.urlPage");

	private MenuModel model;

	Logger logger = (Logger) LogManager.getLogger(LoginController.class);

	@EJB
	private ApplicationMenuEJBLocal ejbMenu;

	private Boolean profileAdmin;

	/**
	 * @return the profileAdmin
	 */
	public Boolean getProfileAdmin() {
		return profileAdmin;
	}

	/**
	 * @param profileAdmin the profileAdmin to set
	 */
	public void setProfileAdmin(Boolean profileAdmin) {
		this.profileAdmin = profileAdmin;
	}

	@PostConstruct
	public void init() {

		if (model == null) {
			model = new DefaultMenuModel();
			this.createMenu(null, null);
		}
	}

	/**
	 * @return the model
	 */
	public MenuModel getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(MenuModel model) {
		this.model = model;
	}

	/**
	 * Recursive method to creates the menu
	 * 
	 * @param parentMenuId parentMenuId for a item menu. Null for the first
	 *                     iteration
	 * @param subMenu      DefaultSubmenu to add the child item menu elements. Null
	 *                     for the first iteration
	 */
	public void createMenu(Integer parentMenuId, DefaultSubMenu subMenu) {
		DefaultSubMenu parentSubMenu;
		Optional<Result<MtApplicationMenuRecord>> menuList;

		VwUsers user = (VwUsers) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("applicationUser");

		logger.debug("Menu for the profile " + user.getProfileCode());

		if (parentMenuId == null) { // First iteration --> root elements

			menuList = Optional.ofNullable(ejbMenu.findMenuByProfileCode(user.getProfileCode()));

			menuList = Optional.ofNullable(ejbMenu.findMenuByLevelAndProfileCode(user.getProfileCode(), 1));
		} else { // not first iteration --> find the children
			menuList = Optional
					.ofNullable(ejbMenu.findChildrenItemMenuByProfileCode(user.getProfileCode(), parentMenuId));

		}
		if (menuList.isPresent()) {
			// data menu find
			for (MtApplicationMenuRecord p : menuList.get()) {
				if (p.getApplicationParentMenuId() == null) {
					// root element --> no functionality --> head of the main menu
					if (p.getHasChildren()) {

						// add root data to parent submenu
						parentSubMenu = DefaultSubMenu.builder().label(p.getName()).build();
						// search child menu data
						createMenu(p.getApplicationMenuId(), parentSubMenu);
						// add the parent submenu to he model
						model.getElements().add(parentSubMenu);

					}
				} else {
					// not root element

					if (p.getHasChildren()) {
						// intermediate element --> no functionality --> head of the intermediate menu
						// create an intermediate submenu and add the data
						DefaultSubMenu intermediateSubMenu = DefaultSubMenu.builder().label(p.getName()).build();
						// search child menu data
						createMenu(p.getApplicationMenuId(), intermediateSubMenu);
						// add the intermediate menu to the given subMenu
						subMenu.getElements().add(intermediateSubMenu);

					} else {
						// final element --> with functionality --> item for the submenu
						DefaultMenuItem item = DefaultMenuItem.builder().value(p.getName()).url(
								urlProperties.getString("ROOT_APP_FILE_URL") + p.getPage() + "?faces-redirect=true")
								.ajax(true).build();
						// add item to the given subMenu
						subMenu.getElements().add(item);

					}

				}
			}
		}
	}

	public String goToUserProfile() {
		return "user_profile.xhtml" + "?faces-redirect=true";
	}

	public String goToManageUser() {
		return "manage_user.xhtml" + "?faces-redirect=true";
	}

	public boolean viewEditUserProfilesOption() {
		VwUsers user = (VwUsers) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("applicationUser");
		boolean result = false;

		if (user.getProfileCode().equalsIgnoreCase("ADMIN")) {
			result = true;
		}
		return result;
	}

}
