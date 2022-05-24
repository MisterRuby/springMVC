package hello.itemservice.web.validation;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import hello.itemservice.web.validation.form.ItemSaveForm;
import hello.itemservice.web.validation.form.ItemUpdateForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@RestController
@RequestMapping("/validation/api/items")
@RequiredArgsConstructor
public class ValidationItemApiController {

    private final ItemRepository itemRepository;

    /**
     * API 의 경우에는 3가지 경우가 발생한다
     * 성공 요청 : 성공
     * 실패 요청 : JSON 객체를 생성하는 것 자체가 실패함
     *  - 핸들러 메소드 자체가 작동하지 않음
     * 검증 오류 요청 : JSON 을 객체로 만드는 것은 성공. 검증에서 실패함
     * @param saveForm
     * @param bindingResult
     * @return
     */
    @PostMapping("/add")
    public Object addItem(@RequestBody @Validated ItemSaveForm saveForm, BindingResult bindingResult) {
        log.info("API 호출!");

        validateSaveItem(saveForm, bindingResult);

        if (bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);

            // 실무에서는 필요한 데이터만 활용해서 별도의 API 스펙을 정의, 그에 맞는 객체를 만들어 반환해야한다.
            return bindingResult.getAllErrors();
        }

        Item item = saveForm.saveItem();

        Item savedItem = itemRepository.save(item);

        log.info("성공 로직 실행");
        return savedItem;
    }


    private void validateSaveItem(ItemSaveForm saveForm, BindingResult bindingResult) {
        if (saveForm.getPrice() != null && saveForm.getQuantity() != null) {
            int resultPrice = saveForm.getPrice() * saveForm.getQuantity();
            if (resultPrice < 10000) {
                bindingResult.reject("totalPriceMin", new Object[]{10000, resultPrice}, null);
            }
        }
    }

    private void validateUpdateItem(ItemUpdateForm updateForm, BindingResult bindingResult) {
        if (updateForm.getPrice() != null && updateForm.getQuantity() != null) {
            int resultPrice = updateForm.getPrice() * updateForm.getQuantity();
            if (resultPrice < 10000) {
                bindingResult.reject("totalPriceMin", new Object[]{10000, resultPrice}, null);
            }
        }
    }
}
