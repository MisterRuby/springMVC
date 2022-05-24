package hello.itemservice.web.validation.form;

import hello.itemservice.domain.item.Item;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class ItemSaveForm {

    @NotBlank
    private String itemName;

    @NotNull
    @Range(min = 1000, max = 1000000)
    private Integer price;

    @NotNull
    @Range(min = 0, max = 9999)
    private Integer quantity;

    public Item saveItem() {
        Item item = new Item();
        item.setItemName(this.itemName);
        item.setPrice(this.price);
        item.setQuantity(this.quantity);

        return item;
    }
}
