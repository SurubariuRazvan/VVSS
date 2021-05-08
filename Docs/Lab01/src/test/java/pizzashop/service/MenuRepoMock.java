package pizzashop.service;

import pizzashop.model.MenuDataModel;
import pizzashop.repository.MenuRepository;

import java.util.ArrayList;
import java.util.List;

public class MenuRepoMock extends MenuRepository {
	private List<MenuDataModel> listMenu = new ArrayList<>();
	
	@Override
	public List<MenuDataModel> getMenu() {
		return listMenu;
	}
    
    public void setListMenu(List<MenuDataModel> listMenu) {
        this.listMenu = listMenu;
    }
}
