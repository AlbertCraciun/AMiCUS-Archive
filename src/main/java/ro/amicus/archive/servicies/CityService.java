package ro.amicus.archive.servicies;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ro.amicus.archive.dtos.CityDTO;
import ro.amicus.archive.mappers.CityMapper;
import ro.amicus.archive.repositories.CityRepository;

import java.util.List;

@Slf4j
@Service
public class CityService {

    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    public CityService(CityRepository cityRepository, CityMapper cityMapper) {
        this.cityRepository = cityRepository;
        this.cityMapper = cityMapper;
    }

    public List<CityDTO> getCities() {
        return cityMapper.cityListToCityDTOList(cityRepository.findAll());
    }

    public CityDTO getCity(String name) {
        return cityMapper.cityToCityDTO(cityRepository.findByCityName(name));
    }

    public void addCity(CityDTO cityDTO) {
        cityRepository.save(cityMapper.cityDTOToCity(cityDTO));
    }

}
