package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    /**
     * 머지 사용 update
     * @param item
     */
    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    /**
     * 변경 감지 사용 update (이거 사용)
     * @param itemId
     * @param name
     * @param price
     * @param stockQuantity
     */
    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity) {
        // 머지는 모든 필드를 강제로 update해서 위험함
        // 변경감지로 사용
        Item findItem = itemRepository.findOne(itemId);
        findItem.setPrice(price);
        findItem.setName(name);
        findItem.setStockQuantity(stockQuantity);
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }
}
