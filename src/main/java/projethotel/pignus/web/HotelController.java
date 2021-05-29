package projethotel.pignus.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import projethotel.pignus.dao.HotelRepository;
import projethotel.pignus.entities.Hotel;
import projethotel.pignus.services.HotelService;

import java.util.List;
@RestController
public class HotelController {
    @Autowired
    private HotelService hotelService;
    @Autowired
    private HotelRepository hotelRepository;
    @PostMapping("/api/hotel")
    public Hotel saveHotel(@RequestBody HotelForm hotelForm){
        return hotelService.saveHotel(hotelForm.getHotelName(),hotelForm.getAdresse(),
                hotelForm.getNbrePlace());
    }
    @GetMapping("/api/hotels")
    public List<Hotel> getAllHotel(){
        return hotelRepository.findAll();
    }

    @GetMapping("/api/hotel/{id}")
    public Hotel getOneHotel(@PathVariable Long id){
        return hotelRepository.findById(id).get();
    }
    @RequestMapping(value="/api/hotel/{id}",method = RequestMethod.PUT)
    public Hotel updateHotel(@PathVariable Long id,@RequestBody Hotel hotel){
        hotel.setId(id);
        return hotelRepository.save(hotel);
    }
    @DeleteMapping("/api/hotel/{id}")
    public boolean suprimerHotel(@PathVariable Long id){
        hotelRepository.deleteById(id);
        return true;
    }
}
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
class HotelForm{
    private String hotelName;
    private String adresse;
    private int nbrePlace;

}
