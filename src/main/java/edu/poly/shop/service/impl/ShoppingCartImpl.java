package edu.poly.shop.service.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;


import edu.poly.shop.domain.Product;
import edu.poly.shop.model.CartItemDto;
import edu.poly.shop.service.ProductService;
import edu.poly.shop.service.ShoppingCartService;



@SessionScope
@Service
public class ShoppingCartImpl implements ShoppingCartService{
	@Autowired
	private ProductService productService;
	
	Map<Integer, CartItemDto> map = new HashMap<>();
	@Override
	public CartItemDto add(Integer id) {
		CartItemDto itemed = map.get(id);
		if(itemed != null) {
			itemed.setQuantity(itemed.getQuantity() + 1);
		}
		
		Long sl = Long.parseLong(id.toString());
		Optional<Product> pro = productService.findById(sl);
		 
		CartItemDto cart = new CartItemDto();
		cart.setProductId(id);
		cart.setName(pro.get().getName());
		cart.setUnitPrice(pro.get().getUnitPrice());
		cart.setQuantity(1);
		
		map.put(id, cart);
		return null;
	}

	@Override
	public void remove(Integer id) {
		map.remove(id);
		
	}

	@Override
	public CartItemDto update(Integer id, int qty) {
		CartItemDto itemm = map.get(id);
		itemm.setQuantity(qty);
		if(qty <= 0) {
			map.remove(id);
		}
		return null;
	}

	@Override
	public void clear() {
		map.clear();
		
	}

	@Override
	public Collection<CartItemDto> getItems() {
		// TODO Auto-generated method stub
		return map.values();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return map.values().size();
	}

	@Override
	public double getAmount() {
		// TODO Auto-generated method stub
		return map.values().stream().mapToDouble(item->item.getQuantity() * item.getUnitPrice()).sum();
	}

}
