package com.oop.services;

import com.oop.dao.ICartDao;
import com.oop.entities.Cart;
import com.oop.exceptions.CartNotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService implements ICartService {
    @Autowired
    ICartDao cartDao;
    
    @Override
    public Cart getByUserId(long userId) {
        Cart cart = cartDao.findByUserId(userId);
        if (cart == null) throw new CartNotFoundException();
        return cart;
    }
    
    @Override
    public boolean existsByUserId(long userId){
        return cartDao.existsByUserId(userId);
    }

    @Override
    public List<Cart> getAllCarts() {
        Iterable<Cart> cartEntities = cartDao.findAll();
        List<Cart> carts = (List<Cart>)cartEntities;
        return carts;
    }

    @Override
    public boolean existsById(long id) {
        Optional<Cart> cartEntity = cartDao.findById(id);
        if (cartEntity == null) return false;
        return true;
    }

    @Override
    public Cart save(Cart cart) {
        if (cart != null){
            return cartDao.save(cart);
        }
        return null;
    }

    @Override
    public Cart update(Cart cart) {
       Cart dbCart = cartDao.findById(cart.getId()).orElse(null);
       if (dbCart == null) throw new CartNotFoundException();
       return cartDao.save(cart);
    }
    
    @Override
    public void delete(Cart cart) {
       if (!cartDao.existsById(cart.getId())) throw new CartNotFoundException();
       cartDao.delete(cart);
    }
    
    @Override
    public void deleteById(long id) {
       if (!cartDao.existsById(id)) throw new CartNotFoundException();
       cartDao.deleteById(id);
    }
    
}
