package edu.osu.cse5234.business;

import edu.osu.cse5234.business.view.Inventory;
import edu.osu.cse5234.business.view.InventoryService;
import edu.osu.cse5234.business.view.Item;


import java.util.Collection;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 * Session Bean implementation class InventoryServiceBean
 */
@Stateless
@Remote(InventoryService.class)
public class InventoryServiceBean implements InventoryService {


    /**
     * Default constructor. 
     */
    public InventoryServiceBean() {
        // TODO Auto-generated constructor stub
    }
    
    @PersistenceContext
    EntityManager entityManager;
    
    private static String MY_QUERY = "Select i from Item i";

	@Override
	public Inventory getAvailableInventory() {
		
		/*Item snowballBlast = new Item();
		snowballBlast.setName("Snowball Blast");
		snowballBlast.setPrice("4.99");
		snowballBlast.setStock(2);
		
		Item granolaSurprise = new Item();
		granolaSurprise.setName("Granola Surprise");
		granolaSurprise.setPrice("1.99");
		granolaSurprise.setStock(2);
		
		Item chocolateOverload = new Item();
		chocolateOverload.setName("Chocolate Overload");
		chocolateOverload.setPrice("9.99");
		chocolateOverload.setStock(2);
		
		Item theBigOne = new Item();
		theBigOne.setName("The Big One");
		theBigOne.setPrice("15.99");
		theBigOne.setStock(2);
		
		Item goldenEgg = new Item();
		goldenEgg.setName("Golden Egg");
		goldenEgg.setPrice("59.99");
		goldenEgg.setStock(2);*/
		
		Inventory inv = new Inventory();		
		List<Item> items = entityManager.createQuery(MY_QUERY, Item.class).getResultList();
		inv.setList(items);
		
		
		return inv;
	}

	@Override
	public boolean validateQuantity(Collection<Item> items) {
		// TODO temporary infinite inventory
		boolean cond=true;
		Inventory inv = getAvailableInventory();
		List<Item> itemList = inv.getList();
	/*	for (Item i : items) {
			String q = i.getQuantity();
			int quantity = Integer.parseInt(q);
			//TODO figure out how best to make sure that the quantity of items
			// ordered is less than or equal to what we have in the inventory
		}*/
		

		try {
			int k=0;
			
		for (Item item : items) {
		//	if(item.getQuantity().equals(""))
				//	item.setQuantity("0");
			//System.out.println("Stock for "+item.getName()+" is "+item.getStock());
			if((item.getQuantity())>itemList.get(k).getStock())
			{
				cond=false;
			}
		k++;
		}}
		
		catch(NullPointerException e)
		{
			
		}
		
		return cond;
	}

	@Override
	public boolean updateInventory(Collection<Item> items) {
		// TODO temporary infinite inventory
		try {
			Inventory inv = getAvailableInventory();
			List<Item> itemList = inv.getList();
			int k1=0;
for (Item item : items) {
			int k=item.getQuantity();
			if(k<=itemList.get(k1).getStock())
			{
				k=itemList.get(k1).getStock()-k;
				itemList.get(k1).setStock(k);
			}
		k1++;}
}
catch(NullPointerException e)
{
	
}
		return true;
	}

}
