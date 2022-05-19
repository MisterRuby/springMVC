package hello.itemservice.domain.item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * FAST : 빠른배송
 * NORMAL : 일반배송
 * SLOW : 느린배송
 */
@Getter @Setter
@AllArgsConstructor
public class DeliveryCode {

    private String code;
    private String displayName;
}
