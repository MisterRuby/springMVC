package ruby.itemservice.domain.item;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ItemRepositoryTest {
    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    @DisplayName("save")
    void save() {
        Item item = new Item("itemA", 10000, 10);
        Item savedItem = itemRepository.save(item);
        Item findItem = itemRepository.findById(savedItem.getId());

        assertThat(savedItem).isEqualTo(findItem);
    }

    @Test
    @DisplayName("findById")
    void findAll() {
        Item itemA = new Item("itemA", 10000, 10);
        Item itemB = new Item("itemB", 20000, 10);
        Item itemC = new Item("itemC", 30000, 10);

        itemRepository.save(itemA);
        itemRepository.save(itemB);
        itemRepository.save(itemC);

        List<Item> items = itemRepository.findAll();

        assertThat(items.size()).isEqualTo(3);
        assertThat(items).contains(itemA, itemB, itemC);
    }

    @Test
    @DisplayName("update")
    void updateItem() {
        Item itemA = new Item("itemA", 10000, 10);

        Item savedItem = itemRepository.save(itemA);
        Long itemId = savedItem.getId();
        Item updateItem = new Item("item2", 20000, 30);

        itemRepository.update(itemId, updateItem);

        Item findItem = itemRepository.findById(itemId);

        assertThat(findItem.getItemName()).isEqualTo("item2");
        assertThat(findItem.getPrice()).isEqualTo(20000);
        assertThat(findItem.getQuantity()).isEqualTo(30);

    }
}