package hello.itemservice.web.validation.form;

import hello.itemservice.domain.item.Item;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ItemUpdateForm {

    @NotNull
    private Long id;

    @NotBlank
    private String itemName;

    @NotNull
    @Range(min = 1000, max = 1000000)
    private Integer price;

    @NotNull
    @Range(min = 0)
    private Integer quantity;

    public Item updateItem() {
        Item item = new Item();
        item.setId(this.id);
        item.setItemName(this.itemName);
        item.setPrice(this.price);
        item.setQuantity(this.quantity);

        return item;
    }
}
