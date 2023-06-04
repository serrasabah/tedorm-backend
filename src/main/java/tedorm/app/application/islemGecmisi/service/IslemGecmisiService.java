package tedorm.app.application.islemGecmisi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tedorm.app.application.common.response.MessageResponse;
import tedorm.app.application.common.response.ResponseType;
import tedorm.app.application.islemGecmisi.entity.IslemGecmisi;
import tedorm.app.application.islemGecmisi.repository.IslemGecmisiRepository;
import tedorm.app.application.menu.entity.Menu;

import java.util.List;


@Service
@RequiredArgsConstructor
public class IslemGecmisiService {

    private final IslemGecmisiRepository islemGecmisiRepository;

    public MessageResponse addIslemGecmisi(IslemGecmisi islemGecmisi) {
        islemGecmisiRepository.save(islemGecmisi);
        return new MessageResponse(ResponseType.SUCCESS, "Menu has been added successfully");
    }

    public List<IslemGecmisi> getAllIslemGecmisi() {
        List<IslemGecmisi> listIslemGecmisi =  islemGecmisiRepository.findAll();
        return  listIslemGecmisi;
    }
}
