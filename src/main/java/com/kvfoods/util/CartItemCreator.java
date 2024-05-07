package com.kvfoods.util;

import java.util.HashMap;
import java.util.Map;

import com.kvfood.model.CartItem;

public class CartItemCreator {
	
	public static CartItemCreator cartItemCreator=null;

	Map<Integer, CartItem> cart;
	
	private CartItemCreator() {
		cart=new HashMap<>();
	}
	
	public void addCartItem(CartItem cartItem) {
		if(cart.containsKey(cartItem.getItemId())) {
			
			CartItem cartItem2 = cart.get(cartItem.getItemId());
			int quantity=cartItem.getQuantity()+cartItem2.getQuantity();
			cartItem2.setQuantity(quantity);
			cart.put(cartItem2.getItemId(), cartItem2);
			
		}
		else {
			cart.put(cartItem.getItemId(), cartItem);
		}
	}
	
	public void updateCartItem(int itemId,int quantity) {
		cart.get(itemId).setQuantity(quantity);
	}
	
	public void deleteCartItem(int itemId) {
		cart.remove(itemId);
	}
	
	public Map getCart() {
		return cart;
	}
	
	public static CartItemCreator getCartItemCreator() {
		if(cartItemCreator==null) {
			cartItemCreator=new CartItemCreator();
		}
		return cartItemCreator;
	}
	
}
