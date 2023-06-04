package tedorm.app.application.menu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tedorm.app.application.common.response.MessageResponse;
import tedorm.app.application.common.response.ResponseType;
import tedorm.app.application.islemGecmisi.entity.IslemGecmisi;
import tedorm.app.application.islemGecmisi.repository.IslemGecmisiRepository;
import tedorm.app.application.menu.entity.Menu;
import tedorm.app.application.menu.entity.Rating;
import tedorm.app.application.menu.repository.MenuRepository;
import tedorm.app.application.menu.repository.RatingRepository;
import tedorm.app.application.student.entity.Student;

import javax.persistence.EntityNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    private  final RatingRepository ratingRepository;
    @Autowired
    private IslemGecmisiRepository islemGecmisiRepository;

    public MessageResponse addMenu(Menu menu) {

        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(menu.getDate().toString(), inputFormatter);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("EEEE");
        String finalDay = date.format(outputFormatter);

        menu.setDay(finalDay);
        menuRepository.save(menu);
        return new MessageResponse(ResponseType.SUCCESS, "Menu has been added successfully");
    }

    public List<Menu> getAllMenus() {
        List<Menu> listMenus =  menuRepository.findAll();
        return  listMenus;
    }

    public List<Menu> getMenus() {
        LocalDate currentDate = LocalDate.now();
        List<Menu> listMenus =  menuRepository.findByDateGreaterThanEqual(currentDate);
        return  listMenus.stream()
                .sorted(Comparator.comparing(Menu::getDate)).limit(7)
                .collect(Collectors.toList());
    }

    public MessageResponse deleteMenuById(Long id){
        menuRepository.deleteById(id);
        return new MessageResponse(ResponseType.SUCCESS, "Menu has been deleted successfully");
    }

    public MessageResponse updateMenu(Long id, Menu updatedMenu) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Menu not found"));

        menu.update(updatedMenu);

        menuRepository.save(menu);

        return new MessageResponse(ResponseType.SUCCESS, "Menu has been updated successfully");
    }

    public Rating createOylama(Long yemekId, Double puan, Long studentId) {
        Optional<Rating> student = ratingRepository.findByStudentIdAndYemekId(studentId, yemekId);

        Optional<Rating> existingRatingOptional = ratingRepository.findByYemekId(yemekId);
        if(student.isPresent()){
            return null;
        }
        Rating oylama;
        if (existingRatingOptional.isPresent()) {
            oylama= existingRatingOptional.get();
            int totalCount = oylama.getCount();
            int newCount = totalCount + 1;
            double newPuan = (oylama.getPuan() + puan) / newCount;
            oylama.setCount(newCount);
            oylama.setPuan(newPuan);
            oylama.setStudentId(studentId);
        } else {
            oylama = new Rating();
            oylama.setYemekId(yemekId);
            oylama.setCount(1);
            oylama.setPuan(puan);
            oylama.setStudentId(studentId);
        }
        Menu menuOptional = menuRepository.findById(yemekId).orElseThrow();
        menuOptional.setPuan(oylama.getPuan());
        ratingRepository.save(oylama);
        IslemGecmisi islemGecmisi = new IslemGecmisi();
        islemGecmisi.setMessage("puanlandı");
        islemGecmisiRepository.save(islemGecmisi);

        return oylama;
    }

    public Integer incrementOylamaCount(Long yemekId) {
        Optional<Menu> menuOptional = menuRepository.findById(yemekId);
        if (menuOptional.isPresent()) {
            Rating oylama = ratingRepository.findByYemekId(yemekId).orElse(null);
            if (oylama != null) {
                oylama.setCount(oylama.getCount() + 1);
                ratingRepository.save(oylama);
                return oylama.getCount();
            } else {
                oylama = new Rating();
                oylama.setYemekId(yemekId);
                oylama.setCount(1);
                ratingRepository.save(oylama);
                return 1;
            }
        }
        return null;
    }


}
