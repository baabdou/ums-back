package projethotel.pignus.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import projethotel.pignus.entities.AppRole;
import projethotel.pignus.entities.Hotel;

public interface HotelRepository extends JpaRepository<Hotel,Long> {
}
